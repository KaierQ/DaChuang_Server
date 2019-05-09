package cn.edu.sicnu.cs.service.check_attendance.impl;

import cn.edu.sicnu.cs.dao.check_attendance.AttendanceDao;
import cn.edu.sicnu.cs.dao.check_attendance.AttendanceDetailDao;
import cn.edu.sicnu.cs.pojo.Attendance;
import cn.edu.sicnu.cs.pojo.AttendanceDetail;
import cn.edu.sicnu.cs.pojo.EmployeeTodayDetail;
import cn.edu.sicnu.cs.service.check_attendance.AttendanceDetailService;
import cn.edu.sicnu.cs.utils.AttendanceInstances;
import cn.edu.sicnu.cs.utils.CheckInMsg;
import cn.edu.sicnu.cs.utils.CheckOutMsg;
import cn.edu.sicnu.cs.utils.TimeOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author kaier
 * @date 2019-04-22 20:04
 */
@Service("attendanceDetailService")
public class AttendanceDetailServiceImpl implements AttendanceDetailService {
    @Autowired
    @Qualifier("attendanceDetailDao")
    private AttendanceDetailDao attendanceDetailDao;

    @Autowired
    @Qualifier("attendanceDao")
    private AttendanceDao attendanceDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return attendanceDetailDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AttendanceDetail record) {
        return attendanceDetailDao.insert(record);
    }

    @Override
    public AttendanceDetail selectByPrimaryKey(Integer id) {
        return attendanceDetailDao.selectByPrimaryKey(id);
    }

    @Override
    public List<AttendanceDetail> selectAll() {
        return attendanceDetailDao.selectAll();
    }

    @Override
    public int updateByPrimaryKey(AttendanceDetail record) {
        return attendanceDetailDao.updateByPrimaryKey(record);
    }

    @Override
    public int updateLeftTimeByEid(Integer eid) {
        return attendanceDetailDao.updateLeftTimeByEid(eid);
    }

    @Override
    public List<AttendanceDetail> selectRecentAttendanceDatail(Integer eid, int findType) {
        List<AttendanceDetail> details = null;
        Date date = getDateByDays(findType);
        details=attendanceDetailDao.selectRecentAttendanceDatail(eid,date);
        return details;
    }

    /**
     * 获取距离当前日期为days的日期值
     * @param findType
     * @return
     */
    private Date getDateByDays(int findType){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        switch(findType) {
            //查询最近一周
            case AttendanceInstances.RecentOneWeek:
                c.add(Calendar.DATE,-AttendanceInstances.daysOfWeek);
                break;
            //查询最近一个月
            case AttendanceInstances.RecentOneMonth:
                c.add(Calendar.MONTH,-1);
                break;
            //查询最近一年
            case AttendanceInstances.RecentOneYear:
                c.add(Calendar.YEAR,-1);
                break;
            default:break;
        }
        return c.getTime();
    }

    @Override
    public CheckInMsg checkIn(AttendanceDetail attendanceDetail) {
        //新增今日打卡信息
        int ret = attendanceDetailDao.insert(attendanceDetail);
        if(ret<0){
            //新增失败抛异常
            return CheckInMsg.CHECK_IN_FAIL;
        }
        Attendance attendance = attendanceDao.selectByEid(attendanceDetail.geteId());

        //判断迟到情况
        Date arriveTime = attendanceDetail.getArriveTime();
        //工作日加1
        attendance.setWorkdays(attendance.getWorkdays()+1);
        attendance.setMonthWorkdays(attendance.getWorkdays()+1);
        if(!isLate(arriveTime)){
            //如果未迟到
            attendance.setNolateDays(attendance.getNolateDays()+1);
            attendance.setMonthNolateDays(attendance.getMonthNolateDays()+1);
        }else{
            //如果迟到
            attendance.setLateDays(attendance.getLateDays()+1);
            attendance.setMonthLateDays(attendance.getMonthLateDays()+1);
        }

        //更新考勤统计信息
        ret = attendanceDao.updateAttendance(attendance);
        //更新失败抛异常
        if (ret<0) {return CheckInMsg.CHECK_IN_FAIL;}

        return CheckInMsg.CHECK_IN_SUCCESS;
    }

    /**
     * 判断是否迟到
     * @param date
     * @return true表示迟到,false反之
     */
    @Override
    public boolean isLate(Date date){
        Calendar calendar =  Calendar.getInstance();
        calendar.setTime(date);
        //获取小时和分钟
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        //超过时间表示迟到
        if(hour>=TimeOfWork.TIME_START_WORK && minute>0){
            return true;
        }
        return false;
    }

    @Override
    public CheckOutMsg checkOut(Integer eid) {
        //获取员工当天的考勤详细信息
        AttendanceDetail attendanceDetail = attendanceDetailDao.selectTodayByEid(eid);
        if(attendanceDetail==null){
            return CheckOutMsg.CHECK_OUT_FAIL;
        }
        Date date = new Date();
        attendanceDetail.setLeftTime(date);

        //获取当前员工统计信息
        Attendance attendance = attendanceDao.selectByEid(eid);
        if(attendance==null){
            return CheckOutMsg.CHECK_OUT_FAIL;
        }
        if(!isEarlyLeft(date)){
            //如果没有早退
            attendance.setLatedLeftdays(attendance.getLatedLeftdays()+1);
            attendance.setMonthLatedLeftdays(attendance.getMonthLatedLeftdays()+1);
        }else{
            //如果有早退
            attendance.setEarlyLeftdays(attendance.getEarlyLeftdays()+1);
            attendance.setMonthEarlyLeftdays(attendance.getMonthEarlyLeftdays()+1);
        }
        //更新考勤统计信息
        int ret = attendanceDao.updateAttendance(attendance);
        if (ret<0){
            return CheckOutMsg.CHECK_OUT_FAIL;
        }

        return CheckOutMsg.CHECK_OUT_SUCCESS;
    }

    @Override
    public CheckOutMsg checkOut(AttendanceDetail attendanceDetail) {
        //获取员工当天的考勤详细信息
        int ret = attendanceDetailDao.updateByEidAndCreateDate(attendanceDetail);
        if (ret < 0){
            return CheckOutMsg.CHECK_OUT_FAIL;
        }
        //获取当前员工统计信息
        Attendance attendance = attendanceDao.selectByEid(attendanceDetail.geteId());

        if(attendance==null){
            return CheckOutMsg.CHECK_OUT_FAIL;
        }

        if(!isEarlyLeft(attendanceDetail.getLeftTime())){
            //如果没有早退
            attendance.setLatedLeftdays(attendance.getLatedLeftdays()+1);
            attendance.setMonthLatedLeftdays(attendance.getMonthLatedLeftdays()+1);
        }else{
            //如果有早退
            attendance.setEarlyLeftdays(attendance.getEarlyLeftdays()+1);
            attendance.setMonthEarlyLeftdays(attendance.getMonthEarlyLeftdays()+1);
        }
        //更新考勤统计信息
        int retValue = attendanceDao.updateAttendance(attendance);
        if (retValue<0){
            return CheckOutMsg.CHECK_OUT_FAIL;
        }

        return CheckOutMsg.CHECK_OUT_SUCCESS;
    }

    /**
     * 判断是否早退
     * @param date
     * @return true就是早退 , false就是未早退
     */
    @Override
    public boolean isEarlyLeft(Date date){
        Calendar calendar =  Calendar.getInstance();
        calendar.setTime(date);
        //获取小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if(hour<TimeOfWork.TIME_STOP_WORK){
            //如果有早退
            return true;
        }
        return false;
    }


    @Override
    public List<AttendanceDetail> selectAllByEid(Integer eid) {
        List<AttendanceDetail> allByEid = attendanceDetailDao.selectAllByEid(eid);
        return allByEid;
    }

    @Override
    public List<EmployeeTodayDetail> selectAllTodayDetail() {
        List<EmployeeTodayDetail> employeeTodayDetails = attendanceDetailDao.selectAllTodayDetail();
        return employeeTodayDetails;
    }

    @Override
    public List<AttendanceDetail> selectAllToday() {
        List<AttendanceDetail> details = attendanceDetailDao.selectAllToday();
        return details;
    }

    @Override
    public List<AttendanceDetail> selectByDate(String certainDate) {
        return attendanceDetailDao.selectByDate(certainDate);
    }

    @Override
    public int updateByEidAndCreateDate(AttendanceDetail attendanceDetail) {
        return attendanceDetailDao.updateByEidAndCreateDate(attendanceDetail);
    }
}

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
import cn.edu.sicnu.cs.utils.analogy.ALinearRegressionAnalogy;
import cn.edu.sicnu.cs.utils.analogy.AverageAnalogy;
import cn.edu.sicnu.cs.utils.analogy.VarianceAnalogy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        System.out.println(attendanceDetail);
        if(ret<0){
            //新增失败抛异常
            return CheckInMsg.CHECK_IN_FAIL;
        }
        Attendance attendance = attendanceDao.selectByEid(attendanceDetail.geteId());

        //判断迟到情况
        Date arriveTime = attendanceDetail.getArriveTime();
        //工作日加1
        if(attendance.getWorkdays()==null){
            init(attendance);
        }
        attendance.setWorkdays(attendance.getWorkdays()+1);
        attendance.setMonthWorkdays(attendance.getMonthWorkdays()+1);
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
        if (ret<0) {
            return CheckInMsg.CHECK_IN_FAIL;
        }

        return CheckInMsg.CHECK_IN_SUCCESS;
    }

    /**
     * 初始化attendance
     * @param attendance
     */
    private void init(Attendance attendance){
        attendance.setWorkdays(0);
        attendance.setNolateDays(0);
        attendance.setLateDays(0);
        attendance.setEarlyLeftdays(0);
        attendance.setLatedLeftdays(0);
        attendance.setMonthWorkdays(0);
        attendance.setMonthNolateDays(0);
        attendance.setMonthLateDays(0);
        attendance.setMonthEarlyLeftdays(0);
        attendance.setMonthLatedLeftdays(0);
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
        int ret = attendanceDetailDao.updateLeftTimeByEid(eid);
        if(ret<0){
            return CheckOutMsg.CHECK_OUT_FAIL;
        }

        //获取当前员工统计信息
        Attendance attendance = attendanceDao.selectByEid(eid);
        if(attendance==null){
            return CheckOutMsg.CHECK_OUT_FAIL;
        }
        Date date = new Date();
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
        ret = attendanceDao.updateAttendance(attendance);
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
    public AttendanceDetail selectTodayByEid(Integer eId) {
        return attendanceDetailDao.selectTodayByEid(eId);
    }

    @Override
    public int updateByEidAndCreateDate(AttendanceDetail attendanceDetail) {
        return attendanceDetailDao.updateByEidAndCreateDate(attendanceDetail);
    }

    /**
     * 根据考勤数据获取数据分析结果
     * @param details
     * @return
     */
    @Override
    public String getDataAnalogyResult(List<AttendanceDetail> details,int numOfLate){
        Calendar calendar = Calendar.getInstance();
        //记录与上班时间时间差的浮动值
        List<Integer> values = new ArrayList<>();
        for (AttendanceDetail detail : details) {
            calendar.setTime(detail.getArriveTime());
            int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);
            int distance = (TimeOfWork.TIME_START_WORK-hour)*60;
            distance -= minute;
            values.add(distance);
        }
        //计算浮动差平均值
        AverageAnalogy averageAnalogy = new AverageAnalogy(values);
        double avg = averageAnalogy.computeAverage();
        //计算方差
        VarianceAnalogy varianceAnalogy = new VarianceAnalogy(avg,values);
        double variance = varianceAnalogy.computeVariance();
        //计算线性回归方程曲线
        ALinearRegressionAnalogy linearRegressionAnalogy = new ALinearRegressionAnalogy(avg,values);
        linearRegressionAnalogy.computeResult();
        //获得斜率
        double a = linearRegressionAnalogy.getA();
        //获得截距
        double b = linearRegressionAnalogy.getB();
        //计算分析结果
        String result = getResult(avg, variance, a, b,numOfLate);

        return result;
    }

    /**
     * 根据平均值，方差，一元线性回归方程曲线，判断结果
     * @param avg
     * @param variance
     * @param a
     * @param b
     * @return
     */
    private String getResult(double avg,double variance,double a,double b,int numOfLate){
        //如果有迟到
        String str ="";
        String trend = "";
        if(numOfLate>0&&5>=numOfLate){
            str= "近期偶有迟到,次数为:"+numOfLate+" 但";
            trend = getTrend(a);
        }else if(numOfLate>5&&numOfLate<=10){
            str= "近期迟到次数较多,次数为:"+numOfLate+" 但";
            trend = getTrend(a);
        }else if(numOfLate>10){
            str= "近期迟到次数很多,次数为:"+numOfLate+" ";
            trend = getTrend(a);
        }else{
            //如果没迟到
            str= "近期无迟到记录,";
            trend = getTrend(a);
        }
        return str+trend;
    }

    /**
     * 根据斜率判断趋势
     * @param a
     * @return
     */
    private String getTrend(double a){
        double tan15 = Math.tan(Math.toRadians(15));
        double tan_15 = -tan15;
        double tan45 = Math.tan(Math.toRadians(45));
        double tan_45 = -tan45;
        System.out.println("tan15:"+tan15+" tan_15:"+tan_15+" tan45:"+tan45+" tan_45:"+tan_45);
        String str="";
        if(a>0){
            if(0<a&&a<=tan15){
                //如果斜率在0~15度之间
                str = "工作积极性呈上升的态势";
            }else if(a>tan15&&a<=tan45){
                //如果斜率在15~45度之间
                str = "工作积极性呈上升的态势，态势较佳";
            }else if (a>tan45){
                //如果斜率在45度以上
                str = "工作积极性上升度很好，正逐步养成或更正习惯.";
            }
        }else{
            if(a>=tan_15 && a<0){
                //如果效率在-15度到0度之间
                str = "上班积极性趋势不佳,有逐步养成迟到习惯苗头的可能.";
            }else if (a>=tan_45 && a<tan_15){
                //如果效率在-15度到-45度之间
                str = "上班积极性趋势正在变差,此习惯在不久可能会养成.";
            }else{
                //-45以下
                str = "上班积极性趋势正变得很差,此习惯可能较顽固.";
            }
        }
        return str;
    }

}

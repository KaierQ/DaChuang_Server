package cn.edu.sicnu.cs.service.check_attendance;

import cn.edu.sicnu.cs.pojo.AttendanceDetail;
import cn.edu.sicnu.cs.utils.CheckInMsg;
import cn.edu.sicnu.cs.utils.CheckOutMsg;

import java.util.List;

/**
 * @author kaier
 * @date 2019-04-22 20:04
 */
public interface AttendanceDetailService {

    /**
     * 删除记录
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增
     * @param record
     * @return
     */
    int insert(AttendanceDetail record);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    AttendanceDetail selectByPrimaryKey(Integer id);

    /**
     * 查找所有
     * @return
     */
    List<AttendanceDetail> selectAll();

    /**
     * 更改信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(AttendanceDetail record);

    /**
     * 根据某位员工的id,下班打卡
     * @param eid
     * @return
     */
    int updateLeftTimeByEid(Integer eid);

    /**
     * 查询某位员工,早于某时间的所有打卡信息
     * @param eid
     * @param findType
     * @return
     */
    List<AttendanceDetail> selectRecentAttendanceDatail(Integer eid, int findType);

    /**
     * 查询某个员工的所有打卡信息
     * @param eid
     * @return
     */
    List<AttendanceDetail> selectAllByEid(Integer eid);

    /**
     * 查询某员工今日的打卡信息
     * @param eid
     * @return
     */
    AttendanceDetail selectTodayByEid(Integer eid);

    /**
     * 查询今日所有员工的打卡信息
     * @return
     */
    List<AttendanceDetail> selectAllToday();

    /**
     * 上班打卡开始工作
     * @param attendanceDetail
     * @return
     */
    CheckInMsg checkIn(AttendanceDetail attendanceDetail);

    /**
     * 下班打卡走人
     * @param eid
     * @return
     */
    CheckOutMsg checkOut(Integer eid);

}

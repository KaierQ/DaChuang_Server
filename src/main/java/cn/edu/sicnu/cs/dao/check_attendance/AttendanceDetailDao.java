package cn.edu.sicnu.cs.dao.check_attendance;

import cn.edu.sicnu.cs.pojo.AttendanceDetail;
import cn.edu.sicnu.cs.pojo.EmployeeTodayDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 员工每日考勤信息表的CRUD
 * @author Kaier
 */
@Repository("attendanceDetailDao")
public interface AttendanceDetailDao {

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
     * 查询今日员工打卡的详情
     * @param
     * @return
     */
    List<EmployeeTodayDetail> selectAllTodayDetail();

    /**
     * 根据员工号查找
     * @param eId
     * @return
     */
    AttendanceDetail selectTodayByEid(Integer eId);

    /**
     * 查询今日所有员工打卡信息
     * @return
     */
    List<AttendanceDetail> selectAllToday();

    /**
     * 查询某个员工的所有打卡信息
     * @param eid
     * @return
     */
    List<AttendanceDetail> selectAllByEid(Integer eid);


    /**
     * 查询某位员工,晚于某个日期之后的所有打卡信息
     * @param eid
     * @param laterThanThisDate
     * @return
     */
    List<AttendanceDetail> selectRecentAttendanceDatail(@Param("eid") Integer eid,@Param("date") Date laterThanThisDate);

    /**
     * 查找所有
     * @return
     */
    List<AttendanceDetail> selectAll();

    /**
     * 查找某天的所有打卡
     * @param certainDate
     * @return
     */
    List<AttendanceDetail> selectByDate(String certainDate);

    /**
     * 更改信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(AttendanceDetail record);

    /**
     * 根据Eid更改离开时间
     * @param eid
     * @return
     */
    int updateLeftTimeByEid(Integer eid);

    /**
     * 根据员工号更改记录
     * @param attendanceDetail
     * @return
     */
    int updateByEidAndCreateDate(AttendanceDetail attendanceDetail);

}
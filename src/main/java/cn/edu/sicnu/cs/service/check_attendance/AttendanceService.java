package cn.edu.sicnu.cs.service.check_attendance;

import cn.edu.sicnu.cs.pojo.Attendance;

import java.util.List;

/**
 * 对员工出勤信息统计的service方法
 * @author kaier
 * @date 2019-04-23 10:47
 */
public interface AttendanceService {

    /**
     * 新增记录
     * @param record
     * @return
     */
    int insert(Attendance record);

    /**
     * 删除某个员工
     * @param eid
     * @return
     */
    int deleteByEid(Integer eid);

    /**
     * 修改考勤信息
     * @param record
     * @return
     */
    int updateAttendance(Attendance record);

    /**
     * 查询所有员工的考勤统计信息
     * @return List<Attendance>
     */
    List<Attendance> selectAll();

    /**
     * 根据员工的id查找统计信息
     * @param eid 员工id
     * @return Attendance 员工信息实体
     */
    Attendance selectByEid(Integer eid);

}

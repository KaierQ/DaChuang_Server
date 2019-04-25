package cn.edu.sicnu.cs.dao.check_attendance;

import cn.edu.sicnu.cs.pojo.Attendance;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * 对员工出勤数据的统计表的CRUD
 * @author Kaier
 */
@Repository("attendanceDao")
public interface AttendanceDao {

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
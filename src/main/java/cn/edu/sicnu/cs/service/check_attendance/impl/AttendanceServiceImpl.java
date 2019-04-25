package cn.edu.sicnu.cs.service.check_attendance.impl;

import cn.edu.sicnu.cs.dao.check_attendance.AttendanceDao;
import cn.edu.sicnu.cs.pojo.Attendance;
import cn.edu.sicnu.cs.service.check_attendance.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author kaier
 * @date 2019-04-23 10:50
 */
@Service("attendanceService")
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    @Qualifier("attendanceDao")
    private AttendanceDao attendanceDao;

    @Override
    public int insert(Attendance record) {
        return attendanceDao.insert(record);
    }

    @Override
    public int deleteByEid(Integer eid) {
        return attendanceDao.deleteByEid(eid);
    }

    @Override
    public int updateAttendance(Attendance record) {
        return attendanceDao.updateAttendance(record);
    }

    @Override
    public List<Attendance> selectAll() {
        return attendanceDao.selectAll();
    }

    @Override
    public Attendance selectByEid(Integer eid) {
        return attendanceDao.selectByEid(eid);
    }


}

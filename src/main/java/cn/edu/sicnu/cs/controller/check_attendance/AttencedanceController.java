package cn.edu.sicnu.cs.controller.check_attendance;

import cn.edu.sicnu.cs.service.check_attendance.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kaier
 * @date 2019-04-26 19:27
 */
@Controller
@RequestMapping("/attendance")
public class AttencedanceController {

    @Autowired
    @Qualifier("attendanceService")
    private AttendanceService attendanceService;



}

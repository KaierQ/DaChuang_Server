package cn.edu.sicnu.cs.controller.check_attendance;

import cn.edu.sicnu.cs.pojo.AttendanceDetail;
import cn.edu.sicnu.cs.service.check_attendance.AttendanceDetailService;
import cn.edu.sicnu.cs.service.check_attendance.EmployeeService;
import cn.edu.sicnu.cs.utils.TimeOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author kaier
 * @date 2019-04-26 19:28
 */
@Controller
@RequestMapping("/attendanceDetail")
public class AttendanceDetailController {

    @Autowired
    @Qualifier("attendanceDetailService")
    private AttendanceDetailService attendanceDetailService;

    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;

    @RequestMapping("/getAllToday")
    public void getTodayCheck(@RequestParam(value = "cid")String cid){
        //获取企业总人数
        int numberOfPersonnel = employeeService.selectNumberOfPersonnel();
        List<AttendanceDetail> details = attendanceDetailService.selectAllToday();
        //统计今日
        //判断现在是否打卡时间
        int typeOfWorkTime = getTypeOfWorkTime();
        if(typeOfWorkTime==TimeOfWork.BRFORE_WORK){
            //如果是上班之前

        }else if (typeOfWorkTime==TimeOfWork.ON_WORK){
            //如果是工作时间到下班时间之间
        }else{
            //如果是下班时间到24:00之前

        }//else

        return ;
    }

    /**
     * 获得当前时间属于哪个时间段
     * @return
     */
    private int getTypeOfWorkTime(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        if(hour<TimeOfWork.TIME_START_WORK){
            return TimeOfWork.BRFORE_WORK;
        }else if(hour>=TimeOfWork.TIME_START_WORK&&hour<=TimeOfWork.TIME_STOP_WORK){
            return TimeOfWork.ON_WORK;
        }else{
            return TimeOfWork.AFTER_WORK;
        }
    }

}

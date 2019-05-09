package cn.edu.sicnu.cs.controller.check_attendance;

import cn.edu.sicnu.cs.pojo.AttendanceDetail;
import cn.edu.sicnu.cs.service.check_attendance.AttendanceDetailService;
import cn.edu.sicnu.cs.service.check_attendance.EmployeeService;
import cn.edu.sicnu.cs.utils.CheckPercent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author kaier
 * @date 2019-04-26 19:27
 */
@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    @Qualifier("attendanceDetailService")
    private AttendanceDetailService attendanceDetailService;

    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;


    @RequestMapping("/getReport")
    public String getReport(@RequestParam(value = "cid")String cid,Model model){
        CheckPercent checkPercent = new CheckPercent();
        List<String> dates = getPerdayPercentOfCheck(checkPercent);
        System.out.println(checkPercent.getPercentCheckIn());
        System.out.println(checkPercent.getPercentCheckOut());
        model.addAttribute("checkPercent",checkPercent);
        model.addAttribute("dates",dates);
        return "report/report";
    }

    /**
     * 获得打卡
     * @return
     */
    private List<String> getPerdayPercentOfCheck(CheckPercent checkPercent){
        Calendar calendar = Calendar.getInstance();
        int numberOfPersonnel = employeeService.selectNumberOfPersonnel();
        List<String> list = new ArrayList<>();
        for (int i=0;i<30;i++){
            calendar.add(Calendar.DAY_OF_MONTH,-1);
            System.out.println(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH));
            list.add(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH));
            List<AttendanceDetail> details =
                    attendanceDetailService.selectByDate(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH));
            for (AttendanceDetail detail : details) {
                System.out.println(detail);
            }
            getPercentOfCheck(checkPercent,numberOfPersonnel,details);
        }
        return list;
    }

    /**
     * 计算打卡比例
     * @param details
     */
    private void getPercentOfCheck(CheckPercent checkPercent,int numberOfPersonnel,List<AttendanceDetail> details){
        int countNumNoLate=0,countNumNoEarlyLeft=0;
        for (AttendanceDetail detail : details) {
            Date arriveTime = detail.getArriveTime();
            Date leftTime = detail.getLeftTime();
            boolean late = attendanceDetailService.isLate(arriveTime);
            boolean earlyLeft = attendanceDetailService.isEarlyLeft(leftTime);
            if(!late){
                countNumNoLate++;
            }
            if(!earlyLeft){
                countNumNoEarlyLeft++;
            }
        }
        checkPercent.getPercentCheckIn().add((int)(((float)countNumNoLate/numberOfPersonnel)*100));
        checkPercent.getPercentCheckOut().add((int)(((float)countNumNoEarlyLeft/numberOfPersonnel)*100));
    }

}

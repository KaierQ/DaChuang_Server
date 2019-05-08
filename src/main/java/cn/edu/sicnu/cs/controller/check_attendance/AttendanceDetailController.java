package cn.edu.sicnu.cs.controller.check_attendance;

import cn.edu.sicnu.cs.pojo.AttendanceDetail;
import cn.edu.sicnu.cs.pojo.EmployeeTodayDetail;
import cn.edu.sicnu.cs.service.check_attendance.AttendanceDetailService;
import cn.edu.sicnu.cs.service.check_attendance.EmployeeService;
import cn.edu.sicnu.cs.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public String getTodayCheck(@RequestParam(value = "cid")String cid,Model model){
        //获取企业总人数
        int numberOfPersonnel = employeeService.selectNumberOfPersonnel();
        List<AttendanceDetail> details = attendanceDetailService.selectAllToday();
        for (AttendanceDetail detail : details) {
            System.out.println(detail);
        }
        //统计今日
        //判断现在是否打卡时间
        int typeOfWorkTime = getTypeOfWorkTime();
//        int typeOfWorkTime = 3;
        System.out.println(typeOfWorkTime);

        if(typeOfWorkTime==TimeOfWork.BRFORE_WORK){
            //如果是上班之前
            //显示现在是未上班状态
            return "check/before_work";
        }

        //如果是工作时间到下班时间之间
        //统计迟到人数
        DataAnalogyOfCheckIn checkInAnalogy = getCheckInAnalogy(details);
        int numOfLate = checkInAnalogy.getNumOfLate();
        //统计未到人数
        int numOfNoArrive = numberOfPersonnel-details.size();

        model.addAttribute("checkInAnalogy",checkInAnalogy);
        model.addAttribute("numberOfPersonnel",numberOfPersonnel);
        model.addAttribute("numOfNoArrive",numOfNoArrive);
        model.addAttribute("numOfLate",numOfLate);
        System.out.println("总人数:"+numberOfPersonnel+" 迟到人数:"+numOfLate+" 未到人数:"+numOfNoArrive);

//        if (typeOfWorkTime==TimeOfWork.AFTER_WORK){
        if(true){
            //如果是下班时间到24:00之前
            //统计下班打卡合格的人数
            DataAnalogyCheckOut dataAnalogyCheckOut = getNumOfCheckout(details);
            int numOfCheckout = dataAnalogyCheckOut.getBEFORE_5_00()
                    +dataAnalogyCheckOut.get_17_00_17_15()
                    +dataAnalogyCheckOut.get_17_15_17_30()
                    +dataAnalogyCheckOut.get_17_30_17_45()
                    +dataAnalogyCheckOut.getAFTER_17_45();
            //统计没有下班打卡或者早退的人数
            int numOfBadCheckout = details.size()-numOfCheckout;
            model.addAttribute("checkOut",true);
            model.addAttribute("dataAnalogyCheckOut",dataAnalogyCheckOut);
            model.addAttribute("numOfBadCheckout",numOfBadCheckout);
            System.out.println("总人数:"+numberOfPersonnel+" 早退人数或没有打卡人数:"+numOfBadCheckout);
        }//else
        return "check/analogy_all_today";
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
        }//else
    }

    /**
     * 获取迟到的人数
     * @param details
     * @return
     */
    private DataAnalogyOfCheckIn getCheckInAnalogy(List<AttendanceDetail> details){
        DataAnalogyOfCheckIn analogy = new DataAnalogyOfCheckIn();
        Calendar calendar = Calendar.getInstance();
        for (AttendanceDetail detail : details) {
            calendar.setTime(detail.getArriveTime());
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
            //判断员工打卡的时间段类型
            analogy.addData(hour,minute,second);
        }
        return analogy;
    }

    /**
     * 获得下班打卡合格的人数
     * @param details
     * @return cnt
     */
    private DataAnalogyCheckOut getNumOfCheckout(List<AttendanceDetail> details){
        Calendar calendar = Calendar.getInstance();
        DataAnalogyCheckOut analogyCheckOut = new DataAnalogyCheckOut();
        for (AttendanceDetail detail : details) {
            Date leftTime = detail.getLeftTime();
            if(leftTime!=null){
                calendar.setTime(leftTime);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                analogyCheckOut.addData(hour,minute,second);
            }
        }
        return analogyCheckOut;
    }

    @RequestMapping("/getEmployeeTodayDetail")
    public String getEmployeeDetail(@RequestParam(value = "cid")String cid,Model model){
        //获取今日所有员工打卡信息
        List<EmployeeTodayDetail> employeeTodayDetails = attendanceDetailService.selectAllTodayDetail();
        for (EmployeeTodayDetail employeeTodayDetail : employeeTodayDetails) {
            System.out.println(employeeTodayDetail);
        }
        model.addAttribute("cid",cid);
        model.addAttribute("employeeTodayDetails",employeeTodayDetails);
        return "check/employees_today_detail";
    }

    @RequestMapping("/getCertainEmployeeDetail")
    public String getCertainEmployeeDetail(@RequestParam(value = "cid")String cid,@RequestParam(value = "eId")String eId,Model model){
        System.out.println(cid+" "+eId);
        List<AttendanceDetail> details = attendanceDetailService.selectAllByEid(Integer.valueOf(eId));

        int numOfLate = 0;
        int numOfNoArrive = 0;
        int numOfEarlyLeave = 0;
        int numOfNoCheckOut = 0;
        int publicData = 0;
        boolean late=false;
        boolean earlyLeave=false;
        Calendar calendar = Calendar.getInstance();
        for (AttendanceDetail detail : details) {
            late=false;
            earlyLeave=false;
            System.out.println(detail);
            Date arriveTime = detail.getArriveTime();
            if(arriveTime!=null){
                //获取时间
                calendar.setTime(arriveTime);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                if(hour==TimeOfWork.TIME_START_WORK&&minute!=0&&second!=0){
                    numOfLate++;
                    late = true;
                }else if(hour>TimeOfWork.TIME_START_WORK){
                    numOfLate++;
                    late = true;
                }
            }else{
                //如果没有打卡就认为未到旷工
                numOfNoArrive++;
            }
            Date leftTime = detail.getLeftTime();
            if(leftTime!=null){
                calendar.setTime(leftTime);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                if(hour<TimeOfWork.TIME_STOP_WORK){
                    numOfEarlyLeave++;
                    earlyLeave = true;
                }
            }else{
                numOfNoCheckOut++;
                earlyLeave = true;
            }
            if(late==true&&earlyLeave==true){
                publicData++;
            }
        }
        int size = details.size();
        model.addAttribute("normalCheckIn",size-numOfLate-numOfNoArrive);
        model.addAttribute("normalCheckOut",size-numOfEarlyLeave-numOfNoCheckOut);
        model.addAttribute("numOfLate",numOfLate);
        model.addAttribute("numOfNoArrive",numOfNoArrive);
        model.addAttribute("numOfEarlyLeave",numOfEarlyLeave);
        model.addAttribute("numOfNoCheckOut",numOfNoCheckOut);
        model.addAttribute("publicData",publicData);
        model.addAttribute("details",details);

        return "check/certain_detail";
    }

    @RequestMapping("/checkIn")
    private @ResponseBody ResultUtil checkIn(@RequestParam(value = "eId")String eId
            , @RequestParam(value = "arriveTime")String arriveTime, HttpServletResponse response) throws IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        AttendanceDetail attendanceDetail = new AttendanceDetail();
        attendanceDetail.seteId(Integer.valueOf(eId));
        attendanceDetail.setArriveTime(new Date(arriveTime));
        CheckInMsg checkInMsg = attendanceDetailService.checkIn(attendanceDetail);
        //返回json结果字符串
        if(checkInMsg==CheckInMsg.CHECK_IN_SUCCESS){
            return new ResultUtil("true");
        }else{
            return new ResultUtil("false");
        }

    }

    @RequestMapping("/checkOut")
    private @ResponseBody ResultUtil checkOut(
            @RequestParam (value = "cid")String cid,
            @RequestParam(value = "eId")String eId
            ,HttpServletResponse response) throws IOException {
        CheckOutMsg checkOutMsg = attendanceDetailService.checkOut(Integer.valueOf(eId));
        //返回json结果字符串
        if(checkOutMsg==CheckOutMsg.CHECK_OUT_SUCCESS){
            return new ResultUtil("true");
        }else{
            return new ResultUtil("false");

        }
    }

}

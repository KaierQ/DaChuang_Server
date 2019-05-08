package cn.edu.sicnu.cs.controller.check_attendance;

import cn.edu.sicnu.cs.pojo.Attendance;
import cn.edu.sicnu.cs.pojo.Employee;
import cn.edu.sicnu.cs.service.check_attendance.AttendanceService;
import cn.edu.sicnu.cs.service.check_attendance.EmployeeService;
import cn.edu.sicnu.cs.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author kaier
 * @date 2019-05-09 02:20
 */
@Controller
@RequestMapping("/salary")
public class SalaryController {
    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/computeMonthlySalary")
    public void computeMoney(
            @RequestParam(value = "cid")String cid
            ,HttpServletRequest request
            ,HttpServletResponse response){
        //查询本公司所有员工工作打卡统计信息
        List<Attendance> attendances = attendanceService.selectAll();
        //Excel标题
        String[] title = {"工号","姓名","工作天数","迟到天数","早退天数","本月薪资"};
        //Excel文件名
        String fileName = "工资结算_" + System.currentTimeMillis() + ".xls";
        //sheet名
        String sheetName = "工资";

        String[][] content = new String[attendances.size()][];

        for(int i=0;i<attendances.size();i++){
            content[i] = new String[title.length];
            Attendance attendance = attendances.get(i);
            content[i][0] = attendance.geteId()+"";
            Employee employee = employeeService.selectByPrimaryKey(attendance.geteId());
            content[i][1] = employee.getName();
            content[i][2] = attendance.getMonthWorkdays()+"";
            content[i][3] = attendance.getMonthLateDays()+"";
            content[i][4] = attendance.getMonthEarlyLeftdays()+"";
            int workDays =  attendance.getMonthWorkdays()- attendance.getMonthLateDays()-attendance.getMonthEarlyLeftdays();
            content[i][5] = employee.getSalary().multiply(new BigDecimal(workDays)).toString();

        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

        response.setCharacterEncoding("UTF-8");
        //设置文件名
        response.addHeader("Content-Disposition","attachment;filename="+fileName);
        //设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        ServletOutputStream outputStream=null;
        try {
            //传输下载
            outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

package cn.edu.sicnu.cs.controller.check_attendance;

import cn.edu.sicnu.cs.pojo.Employee;
import cn.edu.sicnu.cs.service.check_attendance.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author kaier
 * @date 2019-04-26 18:52
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;

    @RequestMapping("/selectAll")
    public String selectAll(@RequestParam(value="cid") String cid,Model model){
        System.out.println("selectAll");
        List<Employee> employees = employeeService.selectAll();
        model.addAttribute("employees",employees);
        return "employee/employee";
    }

    @RequestMapping("/update_homepage")
    public void updateEmployee(@RequestParam(value="cid")String cid,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cid",cid);
        request.getRequestDispatcher("/WEB-INF/pages/employee/update_employee.jsp").forward(request,response);
    }

    @RequestMapping("/update_detail")
    public void updateDetail(@RequestParam(value="cid")String cid,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cid",cid);
        request.getRequestDispatcher("/WEB-INF/pages/employee/update_detail.jsp").forward(request,response);
    }

    @RequestMapping("/update_info")
    public String updateToDB(
                           @RequestParam(value = "cid")String cid,
                           @RequestParam(value = "uid")String uid,
                           @RequestParam(value = "name")String name,
                           @RequestParam(value = "title")String title,
                           @RequestParam(value = "salary")String salary,Model model){
        Employee employee = new Employee();
        employee.setId(Integer.valueOf(uid));
        if(!"".equals(name)){
            employee.setName(name);
        }
        if(!"".equals(title)){
            employee.setTitle(title);
        }
        if(!"".equals(salary)){
            employee.setSalary(new BigDecimal(salary));
        }
        int ret = employeeService.updateByPrimaryKey(employee);
        if(ret>0){
            model.addAttribute("ret","1");
            model.addAttribute("info","更新成功!");
        }else{
            model.addAttribute("ret","2");
            model.addAttribute("info","您的网络出问题了，更新失败...");
        }
        return "employee/result";
    }

    @RequestMapping("/delete_detail")
    public void deleteDetail(@RequestParam(value="cid")String cid,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cid",cid);
        request.getRequestDispatcher("/WEB-INF/pages/employee/delete_detail.jsp").forward(request,response);
    }

    @RequestMapping("delete_info")
    public String deleteToDB(@RequestParam(value="cid")String cid,
                           @RequestParam(value="uid")String uid,
                           @RequestParam(value = "name")String name,Model model
                           ){
        int ret = employeeService.deleteByPrimaryKey(Integer.valueOf(uid));
        if(ret>0){
            model.addAttribute("ret","1");
            model.addAttribute("info","删除成功!");
        }else{
            model.addAttribute("ret","2");
            model.addAttribute("info","您的网络出错或已经被删除,删除失败!");
        }
        return "employee/result";
    }

}

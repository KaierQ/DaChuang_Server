package cn.edu.sicnu.cs.controller.check_attendance;

import cn.edu.sicnu.cs.pojo.Employee;
import cn.edu.sicnu.cs.service.check_attendance.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String selectAll(@RequestParam(value="cid") String cid){
        System.out.println("selectAll");
        System.out.println("----"+cid+"----");
        List<Employee> employees = employeeService.selectAll();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        return "result";
    }

}

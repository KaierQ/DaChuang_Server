package cn.edu.sicnu.cs.controller.check_attendance;

import cn.edu.sicnu.cs.pojo.Department;
import cn.edu.sicnu.cs.service.check_attendance.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 职能部门
 *
 * @author kaier
 * @date 2019-05-16 19:08
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;

    @RequestMapping("/options")
    public String optionsHomePage(@RequestParam(value = "cid")String cid, Model model){
        model.addAttribute("cid",cid);
        return "department/options_department";
    }

    @RequestMapping("/select_all")
    public String selectAll(@RequestParam(value = "cid")String cid, Model model){
        List<Department> departments = departmentService.selectAll();
        if(departments.size()==0){
            return "department/no_data";
        }
        for (Department department : departments) {
            System.out.println(department);
        }
        model.addAttribute("departments",departments);
        return "department/show_departments";
    }

    @RequestMapping("/delete_detail")
    public String deleteDetail(@RequestParam(value = "cid")String cid, Model model){
        model.addAttribute("cid",cid);
        return "department/delete_detail";
    }

    @RequestMapping("/delete_info")
    public String deleteInfo(@RequestParam(value = "cid")String cid,
                             @RequestParam(value = "departmentId")String departmentId,
                             @RequestParam(value = "name")String name,
                             Model model){
        departmentService.deleteByPrimaryKey(Integer.valueOf(departmentId));
        return "department/success";
    }

    @RequestMapping("/add_detail")
    public String addDetail(@RequestParam(value = "cid")String cid, Model model){
        model.addAttribute("cid",cid);
        return "department/add_detail";
    }

    @RequestMapping("/add_info")
    public String addInfo(@RequestParam(value = "cid")String cid,
                          @RequestParam(value = "name")String name,
                          @RequestParam(value = "leaderId")String leaderId,
                          @RequestParam(value = "leaderName")String leaderName,
                          Model model){
        Department department = new Department();
        department.setName(name);
        department.setLeaderId(Integer.valueOf(leaderId));
        department.setLeaderName(leaderName);
        department.setPersonnelNum(0);
        departmentService.insert(department);
        model.addAttribute("departmentId",department.getId());
        return "department/success_add";
    }

    @RequestMapping("/update_detail")
    public String updateDetail(@RequestParam(value = "cid")String cid, Model model){
        model.addAttribute("cid",cid);
        return "department/update_detail";
    }

    @RequestMapping("/update_info")
    public String updateInfo(@RequestParam(value = "cid")String cid,
                             @RequestParam(value = "departmentId")String departmentId,
                             @RequestParam(value = "name")String name,
                             @RequestParam(value = "leaderId")String leaderId,
                             @RequestParam(value = "leaderName")String leaderName,
                             Model model){

        Department department = departmentService.selectByPrimaryKey(Integer.valueOf(departmentId));
        department.setName(name);
        department.setLeaderId(Integer.valueOf(leaderId));
        department.setLeaderName(leaderName);
        System.out.println(department);
        departmentService.updateByPrimaryKey(department);
        model.addAttribute("cid",cid);
        return "department/success";

    }


}

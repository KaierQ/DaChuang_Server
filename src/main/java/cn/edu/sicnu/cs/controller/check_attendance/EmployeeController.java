package cn.edu.sicnu.cs.controller.check_attendance;

import cn.edu.sicnu.cs.pojo.Department;
import cn.edu.sicnu.cs.pojo.Employee;
import cn.edu.sicnu.cs.pojo.IdUtils;
import cn.edu.sicnu.cs.service.check_attendance.DepartmentService;
import cn.edu.sicnu.cs.service.check_attendance.EmployeeService;
import cn.edu.sicnu.cs.service.check_attendance.IdUtilsService;
import cn.edu.sicnu.cs.utils.ResultUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    @Qualifier("idUtilsService")
    private IdUtilsService idUtilsService;

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;

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

    @RequestMapping("/delete_info")
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

    @RequestMapping("/addEmployee")
    public @ResponseBody ResultUtil addEmployee(
            HttpServletRequest request,HttpServletResponse response) throws IOException, FileUploadException {
//        @RequestParam(value = "name")String name,
//        @RequestParam(value = "title")String title,
//        @RequestParam(value = "salary")String salary,
//        @RequestParam(value = "department")String departmentName,
//

//        String name=null;
//        String title=null;
//        String salary=null;
//        String departmentName=null;
        System.out.println("debug......");
        //获得磁盘文件条目工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //文件上传处理
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> list = upload.parseRequest(request);
        //缓冲
        byte[] tempBytes = new byte[1024*10];
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        Map<String,String> fieldMap = new HashMap<>(16);

        for (FileItem fileItem : list) {
            //只处理非表单类型
            if(!fileItem.isFormField()){
                InputStream is = fileItem.getInputStream();
                int read = 0;
                while((read = is.read(tempBytes))!=0){
                    //获得图像的字节数组流,以便存入数据库
                    arrayOutputStream.write(tempBytes,0,read);
                }
            }else{
                fieldMap.put(fileItem.getFieldName(),fileItem.getString("UTF-8"));
            }
        }

        System.out.println(fieldMap.get("name")+" "+fieldMap.get("title")+" "+fieldMap.get("salary")+" "+fieldMap.get("department"));

        //为员工生成id号
        IdUtils id = new IdUtils();
        idUtilsService.insert(id);
        //获取部门的id
        Department department = departmentService.selectByName(fieldMap.get("department"));
        department.setPersonnelNum(department.getPersonnelNum()+1);

        Employee employee = new Employee();
        employee.setId(id.getId());
        employee.setName(fieldMap.get("name"));
        employee.setTitle(fieldMap.get("title"));
        employee.setSalary(new BigDecimal(fieldMap.get("salary")));
        employee.setDepartmentId(department.getId());
        employee.setImage(arrayOutputStream.toByteArray());
        arrayOutputStream.close();

        int ret = employeeService.insert(employee);
        ResultUtil resultUtil = new ResultUtil();
        if (ret<1){
            //错误提示
            resultUtil.setResult("false");
        }
        resultUtil.setResult("true");
        return resultUtil;
    }

}

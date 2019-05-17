package cn.edu.sicnu.cs.controller.check_attendance;

import cn.edu.sicnu.cs.pojo.Attendance;
import cn.edu.sicnu.cs.pojo.Department;
import cn.edu.sicnu.cs.pojo.Employee;
import cn.edu.sicnu.cs.pojo.IdUtils;
import cn.edu.sicnu.cs.service.check_attendance.AttendanceService;
import cn.edu.sicnu.cs.service.check_attendance.DepartmentService;
import cn.edu.sicnu.cs.service.check_attendance.EmployeeService;
import cn.edu.sicnu.cs.service.check_attendance.IdUtilsService;
import cn.edu.sicnu.cs.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.Date;
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

    @Autowired
    @Qualifier("attendanceService")
    private AttendanceService attendanceService;

    @RequestMapping("/selectAll")
    public String selectAll(@RequestParam(value="cid") String cid,Model model){
        System.out.println("selectAll");
        List<Employee> employees = employeeService.selectAll();
        if (employees.size()==0) {
            return "employee/no_data";
        }
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

    @PostMapping("/addEmployee")
    public @ResponseBody ResultUtil addEmployee(
            HttpServletRequest request,HttpServletResponse response) throws Exception {

        //缓冲存储
        byte[] tempBytes = new byte[1024*100];
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream(tempBytes.length);
        Map<String,String> fieldMap = (Map<String, String>) request.getSession().getAttribute("fieldMap");
        String path = (String) request.getSession().getAttribute("path");
        System.out.println(fieldMap.get("cid")+" "+fieldMap.get("name")+" "+fieldMap.get("title")+" "+fieldMap.get("salary")+" "+fieldMap.get("department"));
        //结果
        ResultUtil resultUtil = new ResultUtil();

        //为员工生成id号
        IdUtils id = new IdUtils();
        idUtilsService.insert(id);
        //获取部门的id
        Department department = departmentService.selectByName(fieldMap.get("department"));
        if(department==null){
            //错误提示
            resultUtil.setResult("false");
            return resultUtil;
        }
        department.setPersonnelNum(department.getPersonnelNum()+1);

        //生成员工实体类
        Employee employee = new Employee();
        employee.setId(id.getId());
        employee.setName(fieldMap.get("name"));
        employee.setTitle(fieldMap.get("title"));
        employee.setSalary(new BigDecimal(fieldMap.get("salary")));
        employee.setSignDate(new Date());
        employee.setDepartmentId(department.getId());

        File file = new File(path,"temp.jpg");
        InputStream is = new FileInputStream(file);
        int len = 0;
        while((len = is.read(tempBytes,0,tempBytes.length))!=-1){
            //获得图像的字节数组流,以便存入数据库
            arrayOutputStream.write(tempBytes,0,len);
        }
        is.close();
        employee.setImage(arrayOutputStream.toByteArray());
        arrayOutputStream.close();

        int ret = employeeService.insert(employee);
        //生成打卡总数统计表
        Attendance attendance = new Attendance();
        attendance.seteId(id.getId());
        attendanceService.insert(attendance);


        if (ret<1){
            //错误提示
            resultUtil.setResult("false");
        }
        resultUtil.setResult(""+id.getId());
        file.delete();
        return resultUtil;
    }

    @RequestMapping("/checkIn_getImg")
    public void checkIn(@RequestParam(value = "cid")String cid,
                        @RequestParam(value = "eid")String eid,
                        HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
        //设置文件名
        response.addHeader("Content-Disposition","attachment;filename=image.jpg");
        //设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        ServletOutputStream outputStream=null;
        System.out.println(cid+" "+eid);

        Employee employee = employeeService.selectByPrimaryKey(Integer.valueOf(eid));
        //判断是否员工是否存在
        byte[] bytes = employee.getImage();
        try {
            outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @RequestMapping("/isEmployeeExist")
    public void isEmployeeExist(@RequestParam(value = "cid")String cid,
                                @RequestParam(value = "eid")String eid,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String name = employeeService.selectNameByEid(Integer.valueOf(eid));
        System.out.println("isEmployeeExist:"+eid);
        if(name==null){
            //如果没找到或者不存在返回false
            response.getWriter().print("false");
        }else{
            //找到返回true
            response.getWriter().print("true");
        }
        response.getWriter().close();
        return ;
    }


}

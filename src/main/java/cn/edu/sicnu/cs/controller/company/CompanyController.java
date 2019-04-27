package cn.edu.sicnu.cs.controller.company;

import cn.edu.sicnu.cs.pojo.Company;
import cn.edu.sicnu.cs.pojo.ManagerLoginCheckBean;
import cn.edu.sicnu.cs.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kaier
 * @date 2019-04-21 19:28
 */
@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    @Qualifier("companyService")
    private CompanyService companyService;

    /**
     * 考勤系统的登陆检测
     * @param cId
     * @param password
     * @param response
     * @throws IOException
     */
    @RequestMapping("/check_login")
    public void checkLogin(@RequestParam("cid")String cId, @RequestParam("sys_check_password") String password, HttpServletResponse response) throws IOException {
        System.out.println("---------"+cId+" "+password+"---------");
        Integer cid = Integer.valueOf(cId);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //检查企业账户和密码
        Company company = companyService.selectByPrimaryKey(cid);
        System.out.println(company);
        if(company!=null&&company.getSysCheckPassword().equals(password)){
            //找到之后验证密码是否匹配,匹配就返回true
            response.getWriter().print("true");
        }else{
            //如果没找到或者不存在返回false
            response.getWriter().print("false");
        }
        response.getWriter().close();
        return ;
    }

    /**
     * 公司领导/管理者登陆
     * @param userName
     * @param password
     * @param response
     * @throws IOException
     */
    @RequestMapping("/manager_login")
    public void managerLogin(@RequestParam("username")String userName,@RequestParam("password")String password,HttpServletResponse response) throws IOException {
        System.out.println(userName+"-----"+password);
        //检查企业领导用户名和密码
        ManagerLoginCheckBean ret = companyService.selectPasswordByUsername(userName);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //检测成功返回true
        if(ret!=null&&ret.getPassword().equals(password)){
            response.getWriter().print("true&&"+ret.getcId());
        }else{
            response.getWriter().print("false");
        }
        response.getWriter().close();
        return ;
    }

    /**
     * 转发至企业注册界面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/registerCompany")
    public void registerCompany(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/register_company/registerCompany.jsp").forward(request,response);
    }

    /**
     * 新增企业信息
     * @param companyName
     * @param ceoName
     * @param username
     * @param password
     * @param sysCheckPassword
     * @param tel
     * @param intro
     * @return
     */
    @RequestMapping("/addCompany/{companyName}/{ceoName}/{ceoId}/{username}/{password}/{sysCheckPassword}/{tel}/{intro}")
    public ModelAndView addCompany(
            @PathVariable(value = "companyName")String companyName,
            @PathVariable(value = "ceoName")String ceoName,
            @PathVariable(value = "ceoId")String ceoId,
            @PathVariable(value = "username")String username,
            @PathVariable(value = "password")String password,
            @PathVariable(value = "sysCheckPassword")String sysCheckPassword,
            @PathVariable(value = "tel")String tel,
            @PathVariable(value = "intro")String intro){

        Company company = new Company();
        company.setCompanyName(companyName);
        company.setCeoName(ceoName);
        company.setCeoId(ceoId);
        company.setUsername(username);
        company.setPassword(password);
        company.setSysCheckPassword(sysCheckPassword);
        company.setTel(tel);
        company.setIntro(intro);

        System.out.println(company);
        companyService.insert(company);

        ModelAndView mv = new ModelAndView();
        mv.addObject("cid",company.getcId());
        mv.setViewName("register_company/registerResult");

        return mv;
    }


}

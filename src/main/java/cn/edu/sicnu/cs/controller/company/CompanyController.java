package cn.edu.sicnu.cs.controller.company;

import cn.edu.sicnu.cs.pojo.Company;
import cn.edu.sicnu.cs.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/manager_login")
    public void managerLogin(@RequestParam("username")String userName,@RequestParam("password")String password,HttpServletResponse response) throws IOException {
        System.out.println(userName+"-----"+password);
        String ret = companyService.selectPasswordByUsername(userName);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        if(ret!=null&&ret.equals(password)){
            response.getWriter().print("true");
        }else{
            response.getWriter().print("false");
        }
        response.getWriter().close();
        return ;
    }

}

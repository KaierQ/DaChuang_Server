package cn.edu.sicnu.cs.Interceptor;

import cn.edu.sicnu.cs.datasource.DataSourceInstances;
import cn.edu.sicnu.cs.datasource.DataSourceSwitch;
import cn.edu.sicnu.cs.datasource.DataSources;
import cn.edu.sicnu.cs.utils.Constances;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 每次请求前切换数据源,不同企业用各自的数据库
 *
 * @author kaier
 * @date 2019-04-26 19:31
 */
public class DBOptionInterceptor implements HandlerInterceptor {

    @Autowired
    @Qualifier("xxxCompanyDataSource")
    private ComboPooledDataSource xxxCompanyDataSource;

    @Autowired
    @Qualifier("companyDataSource")
    private ComboPooledDataSource companyDataSource;

    @Autowired
    @Qualifier("dataSources")
    private DataSources dataSources;

    private String cid;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("interceptor");
        String companyId = request.getParameter("cid");
        this.cid = companyId;
        if (cid!=null){
            System.out.println("----------"+cid+"----------");
            String url = Constances.jdbcDriver+Constances.IP+":"+Constances.dbPort+"/"+cid+"_company?useUnicode=true&amp;characterEncoding=UTF8&amp;useSSL=false";
            xxxCompanyDataSource.setJdbcUrl(url);
            DataSourceSwitch.setDataSourceType(DataSourceInstances.XXX_COMPANY_DB);
            StringBuffer requestURL = request.getRequestURL();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        if(cid!=null){
            DataSourceSwitch.clearDataSourceType();
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}

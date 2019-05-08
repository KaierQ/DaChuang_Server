package cn.edu.sicnu.cs.Interceptor;

import cn.edu.sicnu.cs.datasource.DataSourceInstances;
import cn.edu.sicnu.cs.datasource.DataSourceSwitch;
import cn.edu.sicnu.cs.datasource.DataSources;
import cn.edu.sicnu.cs.utils.Constances;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kaier
 * @date 2019-05-08 12:07
 */
public class EmployeeInterceptor implements HandlerInterceptor {

    @Autowired
    @Qualifier("xxxCompanyDataSource")
    private ComboPooledDataSource xxxCompanyDataSource;

    @Autowired
    @Qualifier("companyDataSource")
    private ComboPooledDataSource companyDataSource;

    @Autowired
    @Qualifier("dataSources")
    private DataSources dataSources;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Employee interceptor...");
        //获取文件上传的路径
        String path = request.getSession().getServletContext().getRealPath("/uploads");
        File file=new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        //获得磁盘文件条目工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(new File(path));
        //设置 缓存的大小
        factory.setSizeThreshold(1024*1024);
        //文件上传处理
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> list = upload.parseRequest(new ServletRequestContext(request));

        Map<String,String> fieldMap = new HashMap<>(16);

        for (FileItem fileItem : list) {
            //只处理非表单类型
            if(!fileItem.isFormField()){
                //获取路径名
                String value = fileItem.getName();
                //索引到最后一个反斜杠
                int start = value.lastIndexOf("\\");
                //截取 上传文件的 字符串名字，加1是 去掉反斜杠
                String filename = value.substring(start+1);
                //写到磁盘上
                //第三方提供的
                fileItem.write(new File(path,filename));
                fileItem.delete();
            }else{
                fieldMap.put(fileItem.getFieldName(),fileItem.getString("UTF-8"));
            }
        }

        String url = Constances.jdbcDriver+Constances.IP+":"+Constances.dbPort+"/"+fieldMap.get("cid")+"_company?useUnicode=true&amp;characterEncoding=UTF8&amp;useSSL=false";
        xxxCompanyDataSource.setJdbcUrl(url);
        DataSourceSwitch.setDataSourceType(DataSourceInstances.XXX_COMPANY_DB);

        request.getSession().setAttribute("fieldMap",fieldMap);
        request.getSession().setAttribute("path",path);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        DataSourceSwitch.clearDataSourceType();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}

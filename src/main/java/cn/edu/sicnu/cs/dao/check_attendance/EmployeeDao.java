package cn.edu.sicnu.cs.dao.check_attendance;

import cn.edu.sicnu.cs.pojo.Employee;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 *  对员工信息表进行CRUD操作
 * @author Kaier
 */
@Repository("employeeDao")
public interface EmployeeDao {

    /**
     * 根据主键删除员工
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增员工记录
     * @param record
     * @return
     */
    int insert(Employee record);

    /**
     * 根据主键查找员工信息
     * @param id
     * @return
     */
    Employee selectByPrimaryKey(Integer id);

    /**
     * 查找多个员工信息
     * @return
     */
    List<Employee> selectAll();

    /**
     * 更改员工信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Employee record);


    /**
     * 获取企业的人数
     * @return
     */
    int selectNumberOfPersonnel();

    /**
     * 获取头像
     * @param id
     * @return
     */
    byte[] selectImageByEid(Integer id);

    /**
     *
     * @param id
     * @return
     */
    String selectNameByEid(Integer id);

}
package cn.edu.sicnu.cs.service.check_attendance;

import cn.edu.sicnu.cs.pojo.Employee;

import java.util.List;

/**
 * @author kaier
 * @date 2019-04-21 20:08
 */
public interface EmployeeService {
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

}

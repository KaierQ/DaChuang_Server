package cn.edu.sicnu.cs.dao.check_attendance;

import cn.edu.sicnu.cs.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 部门信息CRUD
 * @author Kaier
 */
@Repository("departmentDao")
public interface DepartmentDao {

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增
     * @param record
     * @return
     */
    int insert(Department record);

    /**
     * 查找
     * @param id
     * @return
     */
    Department selectByPrimaryKey(Integer id);

    /**
     * 查找
     * @return
     */
    List<Department> selectAll();

    /**
     * 更改
     * @param record
     * @return
     */
    int updateByPrimaryKey(Department record);

}
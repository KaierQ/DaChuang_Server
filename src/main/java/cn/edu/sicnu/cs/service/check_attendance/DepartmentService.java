package cn.edu.sicnu.cs.service.check_attendance;

import cn.edu.sicnu.cs.pojo.Department;

import java.util.List;

/**
 * @author kaier
 * @date 2019-04-23 15:49
 */
public interface DepartmentService {
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

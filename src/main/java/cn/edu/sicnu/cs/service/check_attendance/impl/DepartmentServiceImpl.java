package cn.edu.sicnu.cs.service.check_attendance.impl;

import cn.edu.sicnu.cs.dao.check_attendance.DepartmentDao;
import cn.edu.sicnu.cs.pojo.Department;
import cn.edu.sicnu.cs.service.check_attendance.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kaier
 * @date 2019-04-23 15:50
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    @Qualifier("departmentDao")
    private DepartmentDao departmentDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return departmentDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Department record) {
        return departmentDao.insert(record);
    }

    @Override
    public Department selectByPrimaryKey(Integer id) {
        return departmentDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> selectAll() {
        return departmentDao.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Department record) {
        return departmentDao.updateByPrimaryKey(record);
    }

    @Override
    public Department selectByName(String name) {
        return departmentDao.selectByName(name);
    }

}

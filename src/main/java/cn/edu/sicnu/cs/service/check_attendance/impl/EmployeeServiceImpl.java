package cn.edu.sicnu.cs.service.check_attendance.impl;

import cn.edu.sicnu.cs.dao.check_attendance.EmployeeDao;
import cn.edu.sicnu.cs.pojo.Employee;
import cn.edu.sicnu.cs.service.check_attendance.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kaier
 * @date 2019-04-21 20:09
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    @Qualifier("employeeDao")
    private EmployeeDao employeeDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return employeeDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Employee record) {
        int ret = employeeDao.insert(record);

        int i=1/0;
        return ret;
    }

    @Override
    public Employee selectByPrimaryKey(Integer id) {
        return employeeDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> selectAll() {
        return employeeDao.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Employee record) {
        return employeeDao.updateByPrimaryKey(record);
    }

}

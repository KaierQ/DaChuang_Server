package cn.edu.sicnu.cs.service.company.impl;

import cn.edu.sicnu.cs.dao.company.CompanyDao;
import cn.edu.sicnu.cs.pojo.Company;
import cn.edu.sicnu.cs.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 提供对公司表进行操作的CRUD的service方法
 * @author kaier
 * @date 2019-04-21 19:25
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    @Qualifier("companyDao")
    private CompanyDao companyDao;

    @Override
    public int deleteByPrimaryKey(Integer cId) {
        return companyDao.deleteByPrimaryKey(cId);
    }

    @Override
    public int insert(Company record) {
        return companyDao.insert(record);
    }

    @Override
    public Company selectByPrimaryKey(Integer cId) {
        return companyDao.selectByPrimaryKey(cId);
    }

    @Override
    public List<Company> selectAll() {
        return companyDao.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Company record) {
        return companyDao.updateByPrimaryKey(record);
    }

    @Override
    public String selectPasswordByUsername(String username) {
        return companyDao.selectPasswordByUsername(username);
    }

}

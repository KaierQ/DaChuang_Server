package cn.edu.sicnu.cs.service.company;

import cn.edu.sicnu.cs.pojo.Company;

import java.util.List;

/**
 * 对公司信息表进行CRUD操作
 * @author kaier
 * @date 2019-04-21 19:24
 */
public interface CompanyService {
    /**
     *根据主键(公司c_id)删除注册的公司信息
     * @param cId
     * @return
     */
    int deleteByPrimaryKey(Integer cId);

    /**
     * 新增公司信息
     * @param record
     * @return
     */
    int insert(Company record);

    /**
     * 根据主键查找公司信息
     * @param cId
     * @return
     */
    Company selectByPrimaryKey(Integer cId);

    /**
     * 查询所有公司信息
     * @return
     */
    List<Company> selectAll();

    /**
     * 更改公司信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Company record);

    /**
     * 根据管理用户名查找记录
     * @param username
     * @return
     */
    String selectPasswordByUsername(String username);

}

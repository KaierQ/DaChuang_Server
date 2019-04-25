package cn.edu.sicnu.cs.dao.company;

import cn.edu.sicnu.cs.pojo.Company;
import java.util.List;

/**
 * 对Company表的CRUD操作
 * @author Kaier
 */
public interface CompanyDao {
    /**
     * 删除
     * @param cId
     * @return
     */
    int deleteByPrimaryKey(Integer cId);

    /**
     * 新增
     * @param record
     * @return
     */
    int insert(Company record);

    /**
     * 根据主键查找
     * @param cId
     * @return
     */
    Company selectByPrimaryKey(Integer cId);

    /**
     * 查找所有
     * @return
     */
    List<Company> selectAll();

    /**
     * 更新
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
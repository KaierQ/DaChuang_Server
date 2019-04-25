package cn.edu.sicnu.cs.service.check_attendance;

import cn.edu.sicnu.cs.pojo.IdUtils;

import java.util.List;

/**
 * 对IdUtils表的CRUD操作
 * @author kaier
 * @date 2019-04-22 19:17
 */
public interface IdUtilsService {
    /**
     * 删除某个员工的Id
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增
     * @param record
     * @return
     */
    int insert(IdUtils record);

    /**
     * 查找所有
     * @return
     */
    List<IdUtils> selectAll();

}

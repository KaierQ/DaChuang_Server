package cn.edu.sicnu.cs.dao.check_attendance;

import cn.edu.sicnu.cs.pojo.IdUtils;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * ID授权的CRUD
 * @author Kaier
 */
@Repository("idUtilsDao")
public interface IdUtilsDao {
    /**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入
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
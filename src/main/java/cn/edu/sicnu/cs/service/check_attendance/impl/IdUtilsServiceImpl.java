package cn.edu.sicnu.cs.service.check_attendance.impl;

import cn.edu.sicnu.cs.dao.check_attendance.IdUtilsDao;
import cn.edu.sicnu.cs.pojo.IdUtils;
import cn.edu.sicnu.cs.service.check_attendance.IdUtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kaier
 * @date 2019-04-22 19:17
 */
@Service("idUtilsService")
public class IdUtilsServiceImpl implements IdUtilsService {
    @Autowired
    @Qualifier("idUtilsDao")
    private IdUtilsDao idUtilsDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return idUtilsDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(IdUtils record) {
        return idUtilsDao.insert(record);
    }

    @Override
    public List<IdUtils> selectAll() {
        return idUtilsDao.selectAll();
    }

}

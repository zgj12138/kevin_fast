package io.kevin.modules.sys.service.impl;

import io.kevin.modules.sys.dao.SysLogDao;
import io.kevin.modules.sys.entity.SysLogEntity;
import io.kevin.modules.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ZGJ
 * @date 2017/7/9 14:39
 **/
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService{
    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public SysLogEntity queryObject(Long id) {
        return sysLogDao.queryObject(id);
    }

    @Override
    public List<SysLogEntity> queryList(Map<String, Object> map) {
        return sysLogDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysLogDao.queryTotal();
    }

    @Override
    public void save(SysLogEntity sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public void delete(Long id) {
        sysLogDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        sysLogDao.deleteBatch(ids);
    }
}

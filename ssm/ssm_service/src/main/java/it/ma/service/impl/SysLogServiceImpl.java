package it.ma.service.impl;

import it.ma.dao.SysLogDao;
import it.ma.domain.SysLog;
import it.ma.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService  {
    @Autowired
    @Qualifier("sysLogDao")
    private SysLogDao sysLogDao;
    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);

    }

    @Override
    public List<SysLog> findAll() throws Exception{
        return   sysLogDao.findAll();

    }
}

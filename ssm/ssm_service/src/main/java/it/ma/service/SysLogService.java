package it.ma.service;

import it.ma.domain.SysLog;

import java.util.List;

public interface SysLogService {
  public    void save(SysLog sysLog) throws  Exception;

  public  List<SysLog> findAll() throws  Exception;
}

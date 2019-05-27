package it.ma.dao;

import it.ma.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sysLogDao")
public interface SysLogDao {
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
      public void save(SysLog sysLog) throws Exception;

    @Select("select * from  sysLog")
    public    List<SysLog> findAll();

}

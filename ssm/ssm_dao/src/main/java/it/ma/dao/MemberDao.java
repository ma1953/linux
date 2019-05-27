package it.ma.dao;

import it.ma.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface MemberDao {
    @Select("select * from member where id=#{id}")
    public Member findById(String id) throws Exception;
}

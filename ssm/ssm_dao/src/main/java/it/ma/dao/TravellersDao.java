package it.ma.dao;

import it.ma.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellersDao {
    @Select("select * from traveller where id in(select travellerId from order_traveller where orderId=#{id})")
    public List<Traveller> findByOrderId(String id ) throws Exception;

}

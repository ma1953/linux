package it.ma.dao;

import it.ma.domain.Orders;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("ordersDao")
public interface OrdersDao {
    @Select("select * from orders")
   @Results({
           @Result(id =true,column = "id",property = "id"),
           @Result(column = "orderNum",property = "orderNum"),
           @Result(column = "orderTime",property = "orderTime"),
           @Result(column = "orderStatus",property = "orderStatus"),
           @Result(column = "peopleCount",property = "peopleCount"),
           @Result(column = "payType",property = "payType"),
           @Result(column = "orderDesc",property = "orderDesc"),
           @Result(column = "productId",property = "product",one = @One(select = "it.ma.dao.ProductDao.findByid"))
   })
    public List<Orders> findAll() throws  Exception;


    @Select("select * from orders")
    @Results({
            @Result(id =true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one = @One(select = "it.ma.dao.ProductDao.findByid"))
    })
    public List<Orders> findAllByPages(int page,int pageSize) throws  Exception;


    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(id = true,column ="id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one = @One(select = "it.ma.dao.ProductDao.findByid")),
            @Result(column = "travellerId",property = "travellers" ,many = @Many(select = "it.ma.dao.TravellersDao.findByOrderId")),
            @Result(column = "memberId",property = "member",one = @One(select = "it.ma.dao.MemberDao.findById"))
    })
    public  Orders findById(String ordersId) throws Exception;
}

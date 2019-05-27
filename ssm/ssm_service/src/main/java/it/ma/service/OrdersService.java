package it.ma.service;

import it.ma.domain.Orders;

import java.util.List;

public interface OrdersService {
    public List<Orders> findAll() throws Exception;
    public List<Orders> findAllByPages(int page,int pageSize) throws  Exception;
    public  Orders findById(String id) throws Exception;
}

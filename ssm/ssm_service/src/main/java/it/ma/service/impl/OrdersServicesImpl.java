package it.ma.service.impl;

import com.github.pagehelper.PageHelper;
import it.ma.dao.OrdersDao;
import it.ma.domain.Orders;
import it.ma.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("ordersService")
public class OrdersServicesImpl implements OrdersService {
    @Autowired
    @Qualifier("ordersDao")
    private OrdersDao ordersDao;
    @Override
    public List<Orders> findAll() throws Exception {
        return  ordersDao.findAll();
    }

    @Override
    public List<Orders> findAllByPages(int page, int pageSize) throws Exception {
         PageHelper.startPage(page,pageSize);
        return ordersDao.findAllByPages(page,pageSize);
    }

    @Override
    public Orders findById(String ordersId) throws Exception {
        return ordersDao.findById(ordersId);
    }

}

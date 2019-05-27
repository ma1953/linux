package it.ma.controller;
import com.github.pagehelper.PageInfo;
import it.ma.domain.Orders;
import it.ma.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    @Qualifier("ordersService")
    private OrdersService ordersService;
   /* @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
        List<Orders> orders = ordersService.findAll();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("orsers",orders);
        modelAndView.setViewName("orders-list");
        return modelAndView;
    }*/
    @RequestMapping("/findAllByPage.do")
    public ModelAndView findAllByPage(@RequestParam(name = "page", required = true,defaultValue = "1")int page, @RequestParam(name = "pagesize",required = true,defaultValue = "10")int pagesize)throws  Exception{
        ModelAndView modelAndView =new ModelAndView();
        List<Orders> orders = ordersService.findAllByPages(page,pagesize);
        PageInfo pageInfo=new PageInfo(orders);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("orders-list");
        return  modelAndView;
    }


    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id" ,required = true, defaultValue = "1") String ordersId)
            throws  Exception{

        ModelAndView modelAndView =new ModelAndView();
        Orders orders = ordersService.findById(ordersId);
        modelAndView.addObject("orders",orders);
        modelAndView.setViewName("orders-show");
        return  modelAndView;
    }

}






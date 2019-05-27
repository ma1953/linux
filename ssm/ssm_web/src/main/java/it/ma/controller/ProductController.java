package it.ma.controller;

import it.ma.domain.Product;
import it.ma.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    @Qualifier("productService")
    private ProductService productService;
    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll() throws Exception{
        ModelAndView modelAndView =new ModelAndView();
        List<Product> products = productService.findAll();
        modelAndView.addObject("products",products);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }
    @RequestMapping("/save.do")
    public  String save(Product product) throws Exception{
              productService.save(product);
             return  "redirect:findAll.do";
    }

}

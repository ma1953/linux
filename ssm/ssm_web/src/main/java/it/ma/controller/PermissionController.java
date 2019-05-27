package it.ma.controller;

import it.ma.domain.Permission;
import it.ma.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    @Qualifier("permissionService")
    private PermissionService permissionService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
        List<Permission>  permissions= permissionService.findAll();
        ModelAndView  modelAndView=new ModelAndView();
        modelAndView.addObject("permissions",permissions);
        modelAndView.setViewName("permission-list");
        return modelAndView;
    }

@RequestMapping("/save.do")
    public  String  save(Permission permission) throws Exception{
            permissionService.save(permission);
            return  "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public  ModelAndView findById(@RequestParam(name =  "id",required = true) String id) throws  Exception{
     Permission permission=    permissionService.findById(id);
          ModelAndView modelAndView=new ModelAndView();
          modelAndView.addObject("permission",permission);
          modelAndView.setViewName("permission-show");
          return modelAndView;
    }
    @RequestMapping("/deleteById.do")
  public String  deleteById(@RequestParam(name =  "id",required = true)String id) throws Exception{

           permissionService.deleteById(id);
           return  "redirect:findAll.do";
  }

}

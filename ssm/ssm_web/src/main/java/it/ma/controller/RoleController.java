package it.ma.controller;

import it.ma.domain.Permission;
import it.ma.domain.Role;
import it.ma.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    @Qualifier("roleService")
    private RoleService  roleService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws  Exception{
        ModelAndView modelAndView =new ModelAndView();
        List<Role> roles = roleService.findAll();
         modelAndView.addObject("roles",roles);
         modelAndView.setViewName("role-list");
              return  modelAndView;
    }

      @RequestMapping("/save.do")
    public  String save(Role role) throws  Exception{
        roleService.save(role);
        return  "redirect:findAll.do";
    }

@RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id" ,required = true) String id) throws  Exception{
       Role role=  roleService.findById(id);
           ModelAndView modelAndView=new ModelAndView();
           modelAndView.addObject("role",role);
           modelAndView.setViewName("role-show");
           return modelAndView;
    }

    @RequestMapping("/deleteRoleById")
public  String deleteRoleById(@RequestParam(name = "id") String id) throws Exception{
          roleService.deleteRoleById(id);
          return "redirect:findAll.do";

}
/*
public  String addPermissionByRoleId(@RequestParam(name = "id",required = true) String roleId) throws Exception{
        roleService.addPermissionByRoleId(roleId);

}*/

@RequestMapping("/findPermissionByRoleId.do")
public  ModelAndView findPermissionByRoleId(@RequestParam(name = "id",required = true)String roleId) throws Exception{

    List<Permission>  permissions=   roleService.findPermissionByRoleId(roleId);
                ModelAndView modelAndView=new ModelAndView();
               Role role = roleService.findById(roleId);
               modelAndView.addObject("permissions",permissions);
               modelAndView.addObject("role",role);
                modelAndView.setViewName("role-permission-add");
                return modelAndView;
}

}

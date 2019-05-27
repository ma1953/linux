package it.ma.controller;

import it.ma.domain.Role;
import it.ma.domain.Users;
import it.ma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    @Qualifier("userService")
    private UserService userService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll()throws  Exception{
        List<Users> users = userService.findAll();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("users",users);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }
@RequestMapping("/save.do")
public  String save(Users users) throws Exception{
        userService.save(users);
        return "redirect:findAll.do";

}
@RequestMapping("/findById.do")
public  ModelAndView findById(@RequestParam(name = "id",required = true,defaultValue = "1") String id) throws
        Exception{
        ModelAndView modelAndView=new ModelAndView();
        Users user = userService.findById(id);
       modelAndView.addObject("user",user);
       modelAndView.setViewName("user-show");
      return   modelAndView;
}



    @RequestMapping("/findCanAddAllRoleByUserId.do")
    public ModelAndView findCanAddAllRoleByUserId(@RequestParam(name = "id",required = true) String id) throws Exception{
         List<Role> roleslist=    userService.findCanAddAllRoleByUserId(id);
           ModelAndView modelAndView=new ModelAndView();
                    Users user = userService.findById(id);
               modelAndView.addObject("roleslist",roleslist);
               modelAndView.addObject("user",user);
               modelAndView.setViewName("user-role-add");
               return modelAndView;
    }
    @RequestMapping("/addRolesToUsersByUserIdAndRoleId.do")
 public  String addRolesToUsersByUserIdAndRoleId(@RequestParam(name = "userId",required = true) String userId, @RequestParam(name = "ids",required = true) String[] roleIds) throws Exception{
                userService.addRolesToUsersByUserIdAndRoleId(userId,roleIds);
                return "redirect:findAll.do";

    }



}


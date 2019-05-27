package it.ma.controller;

import it.ma.domain.SysLog;
import it.ma.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class sysLogController {
    @Autowired
    private SysLogService sysLogService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
        List<SysLog> sysLogList=   sysLogService.findAll();
        ModelAndView modelAndView=new ModelAndView();
              modelAndView.addObject("sysLogList",sysLogList);
              modelAndView.setViewName("syslog-list");
               return modelAndView;
    }
}

package llcweb.com.controller;

import llcweb.com.dao.repository.UsersRepository;
import llcweb.com.domain.models.Users;
import llcweb.com.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 *@Author: Ricardo
 *@Description: 此文件用于前端所有的页面的请求接口，接口名与html文件名对应
 *@Date: 15:14 2018/8/21
 **/
@Controller
@RequestMapping
public class WebPageController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersService usersService;
    @RequestMapping("/test.html")
    public ModelAndView test(){

        List<String> learnList =new ArrayList<>();
        learnList.add("hello1");
        learnList.add("hello2");
        ModelAndView modelAndView = new ModelAndView("test");
        //添加当前登录用户
        Users users = usersService.getCurrentUser();
        modelAndView.addObject("user", users);
        modelAndView.addObject("learnList", learnList);
        return modelAndView;
    }
    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        Users users = usersService.getCurrentUser();
        modelAndView.addObject("user", users);
        return modelAndView;
    }
    @RequestMapping("/index.html")
    public ModelAndView index1(){
        ModelAndView modelAndView = new ModelAndView("index");
        Users users = usersService.getCurrentUser();
        modelAndView.addObject("user", users);
        return modelAndView;
    }
    @RequestMapping("/index")
    public ModelAndView index2(){

        ModelAndView modelAndView = new ModelAndView("index");
        Users users = usersService.getCurrentUser();
        modelAndView.addObject("user", users);
        return modelAndView;
    }
    @RequestMapping("/main")
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView("index");
        Users users = usersService.getCurrentUser();
        modelAndView.addObject("user", users);
        return modelAndView;
    }

}

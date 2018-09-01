package llcweb.com.controller;

import llcweb.com.dao.repository.UsersRepository;
import llcweb.com.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@Author: Haien
 *@Description: 文档类的控制器
 *@Date: 15:14 2018/8/21
 **/
@Controller
@RequestMapping
public class DocumentController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersService usersService;
//    @RequestMapping("/test.html")
//    public ModelAndView test(){
//
//        List<String> learnList =new ArrayList<>();
//        learnList.add("hello1");
//        learnList.add("hello2");
//        ModelAndView modelAndView = new ModelAndView("test");
//        //添加当前登录用户
//        Users users = usersService.getCurrentUser();
//        modelAndView.addObject("user", users);
//        modelAndView.addObject("learnList", learnList);
//        return modelAndView;
//    }

}

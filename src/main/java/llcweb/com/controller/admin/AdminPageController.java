package llcweb.com.controller.admin;

import llcweb.com.dao.repository.DocumentRepository;
import llcweb.com.dao.repository.ImageRepository;
import llcweb.com.dao.repository.PeopleRepository;
import llcweb.com.domain.models.Document;
import llcweb.com.domain.models.Image;
import llcweb.com.domain.models.People;
import llcweb.com.domain.models.Users;
import llcweb.com.service.DocumentService;
import llcweb.com.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *@Author: Ricardo
 *@Description: 此文件用于admin管理系统的所有的页面的请求接口，接口名与html文件名对应
 *@Date: 15:14 2018/8/21
 **/
@Controller
@RequestMapping("/admin1111111111")
public class AdminPageController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private PeopleRepository peopleRepository;
    @Autowired
    private ImageRepository imageRepository;

    @RequestMapping("/test.html")
    public ModelAndView test(){

        List<String> learnList =new ArrayList<>();
        learnList.add("hello1");
        learnList.add("hello2");
        ModelAndView modelAndView = new ModelAndView("admin/test");
        //添加当前登录用户
        Users users = usersService.getCurrentUser();
        modelAndView.addObject("user", users);
        modelAndView.addObject("learnList", learnList);
        return modelAndView;
    }

    @RequestMapping({"/","/index.html","/index","/main","/main.html"})
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("admin/index");
        Users users = usersService.getCurrentUser();
        modelAndView.addObject("user", users);
        return modelAndView;
    }

}

package llcweb.com.controller.admin;

import llcweb.com.dao.repository.DocumentRepository;
import llcweb.com.domain.models.Document;
import llcweb.com.domain.models.Users;
import llcweb.com.service.DocumentService;
import llcweb.com.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 *@Author: Ricardo
 *@Description: 此文件用于admin管理系统的所有的页面的请求接口，接口名与html文件名对应
 *@Date: 15:14 2018/8/21
 **/
@Controller
@RequestMapping("/admin")
public class AdminPageController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private DocumentService documentService;

    @RequestMapping("/test.html")
    public ModelAndView test(){

        List<String> learnList =new ArrayList<>();
        learnList.add("hello1");
        learnList.add("hello2");
        ModelAndView modelAndView = new ModelAndView("/admin/test");
        //添加当前登录用户
        Users users = usersService.getCurrentUser();
        modelAndView.addObject("user", users);
        modelAndView.addObject("learnList", learnList);
        return modelAndView;
    }

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("/admin/index");
        Users users = usersService.getCurrentUser();
        modelAndView.addObject("user", users);
        return modelAndView;
    }
    @RequestMapping("/index.html")
    public ModelAndView index1(){
        ModelAndView modelAndView = new ModelAndView("/admin/index");
        Users users = usersService.getCurrentUser();
        modelAndView.addObject("user", users);
        return modelAndView;
    }
    @RequestMapping("/index")
    public ModelAndView index2(){

        ModelAndView modelAndView = new ModelAndView("/admin/index");
        Users users = usersService.getCurrentUser();
        modelAndView.addObject("user", users);
        return modelAndView;
    }
    @RequestMapping("/system_users.html")
    public ModelAndView system_users(){

        ModelAndView modelAndView = new ModelAndView("/admin/system_users");
        Users users = usersService.getCurrentUser();
        modelAndView.addObject("user", users);
        return modelAndView;
    }

    /**
     * 文档首页的控制器
     */
    @RequestMapping("/resource_document.html")
    public ModelAndView resource_document(@RequestParam("pageNum")Integer pageNum,
                                          @RequestParam("pageSize")Integer pageSize){
        ModelAndView modelAndView = new ModelAndView("/admin/resource_document");
        Users users = usersService.getCurrentUser();
        //根据用户权限查找文档
        Page<Document> documentList= documentService.selectAll(users,pageNum-1,pageSize);
        modelAndView.addObject("user", users);
        return modelAndView;
    }

    /**
     *更新、新建文档？？？
     */
    @RequestMapping("/edit.html")
    public ModelAndView edit(@RequestParam("id")int id){

        ModelAndView modelAndView = new ModelAndView("/admin/document_edit");
        Document document;
		//为什么要新建一个文档类？？？
        if(id<=0){
            document = new Document();
            document.setTitle("新建文档");
            document.setModel("");
            document.setInfor("");
            document.setContent("新建文档");
        }
        else{
            document= documentRepository.findOne(id);
        }
        Users users = usersService.getCurrentUser();
        modelAndView.addObject("user", users);
        modelAndView.addObject("document", document);
        return modelAndView;
    }
}

package llcweb.com.controller.admin;

import llcweb.com.dao.repository.CommentsRepository;
import llcweb.com.dao.repository.UsersRepository;
import llcweb.com.domain.models.Comments;
import llcweb.com.domain.models.People;
import llcweb.com.domain.models.Users;
import llcweb.com.service.UsersService;
import llcweb.com.tools.IpAddressUtil;
import llcweb.com.tools.PageParam;
import llcweb.com.tools.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Author: Ricardo
 *@Description: 此文件用于user类的数据接口
 *@Date: 15:14 2018/8/21
 **/
@Controller
@RequestMapping("/comments")
public class CommentsController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommentsRepository commentsRepository;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        String content = request.getParameter("content");
        String name = request.getParameter("name");
        String contact = request.getParameter("contact");

        System.out.println("content = "+content);
        Date date = new Date();
        String ipAddress = IpAddressUtil.getIpAddr(request);
        Comments comments = new Comments(name,contact,content,date,ipAddress,0);
        Comments other = commentsRepository.save(comments);

       if(other!=null && other.getId()>0){
           map.put("result", 1);
           map.put("message", "成功保存留言！");
           logger.info("成功保存人员！");
       }else{
           map.put("result", 0);
           map.put("message", "保存留言失败！");
           logger.info("保存留言失败！");
       }

        return map;
    }
}

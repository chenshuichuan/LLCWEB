package llcweb.com.controller.admin;

import llcweb.com.dao.repository.DocumentRepository;
import llcweb.com.dao.repository.ImageRepository;
import llcweb.com.dao.repository.PeopleRepository;
import llcweb.com.domain.models.People;
import llcweb.com.service.PeopleService;
import llcweb.com.service.UsersService;
import llcweb.com.tools.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author haien
 * @Description 人员控制器
 * @Date 2018/10/2
 **/
@RestController
@RequestMapping("people")
public class PeopleController {
    private static final Logger logger=LoggerFactory.getLogger(PeopleController.class);

    @Resource
    private UsersService usersService;
    @Resource
    private PeopleService peopleService;
    @Resource
    private PeopleRepository peopleRepository;
    @Resource
    private DocumentRepository documentRepository;
    @Resource
    private ImageRepository imageRepository;

    /**
     * @Author haien
     * @Description 搜索People
     * @Date 2018/10/3
     * @Param [request]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping(value="/page",method = RequestMethod.GET)
    public Map<String,Object> page(HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();

        //直接返回前台
        String draw = request.getParameter("draw");
        //当前数据的起始位置
        String startIndex = request.getParameter("startIndex");
        //数据长度
        String pageSize = request.getParameter("pageSize");
        if(draw==null||startIndex==null||pageSize==null){
            map.put("result", 0);
            map.put("message", "参数不完整！");
            return map;
        }
        int size = Integer.parseInt(pageSize);
        //页码
        int currentPage = Integer.parseInt(startIndex)/size+1;

        String name=request.getParameter("name");
        String grade1=request.getParameter("grade");
        String position=request.getParameter("position");
        int grade=0;
        if(grade1!=null){
            grade=Integer.parseInt(grade1);
        }
        People people=new People(name,position,grade);

        logger.info("高级查询：size = "+size+",currentPage = "+currentPage);

        //高级查询
        Page<People> peoplePage=peopleService.activeSearch(people,currentPage-1,size);
        //总记录数
        long total = peoplePage.getTotalElements();
        logger.info("total="+total);

        map.put("draw", draw);
        map.put("result", 1);
        if(0==total){
            map.put("message", "未查询到相关人员！");
        }else {
            map.put("total", total);
            map.put("pageData", peoplePage);
            map.put("message", "成功获取分页数据！");
        }
        return map;
    }

    /**
     * @Author haien
     * @Description 保存人员资料
     * @Date 2018/10/4
     * @Param [request, response]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public Map<String,Object> save(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map=new HashMap<>();

        String id=request.getParameter("id");
        String name=request.getParameter("name");
        String portrait1=request.getParameter("portrait");
        String position=request.getParameter("position");
        String introduction1=request.getParameter("introduction");
        String grade1=request.getParameter("grade");

        if(StringUtil.isNull(name)||StringUtil.isNull(portrait1)||
                StringUtil.isNull(position)||StringUtil.isNull(introduction1)||
                StringUtil.isNull(grade1)){
            map.put("result", 0);
            map.put("message", "人员保存失败,信息不完整！");
            return map;
        }

        int portrait=Integer.parseInt(portrait1);
        int introduction=Integer.parseInt(introduction1);
        int grade=Integer.parseInt(grade1);

        People people=null;
        //更新
        if(!StringUtil.isNull(id)&&Integer.parseInt(id)>0){
            logger.info("更新人员--id="+id+"name="+name+"introduction="+introduction1);
            people = peopleRepository.findOne(Integer.parseInt(id));
            if(people==null){
                map.put("result", 0);
                map.put("message", "查无此人！");
                return map;
            }
        }
        //添加
        else {
            logger.info("新增人员--id="+id+"name="+name+"introduction="+introduction1);
            people = new People();
        }

        people.setName(name);
        people.setPortrait(portrait);
        people.setPosition(position);
        people.setGrade(grade);
        people.setIntroduction(introduction);
        peopleRepository.save(people);

        map.put("result", 1);
        map.put("message", "成功保存人员！");
        logger.info("成功保存人员！");

        return map;
    }

    @RequestMapping("/delete")
    public Map<String,Object> delete(@RequestParam("id")Integer id){
        Map<String,Object> map=new HashMap<>();

        logger.info("删除人员：id="+id);

        People people=peopleRepository.findOne(id);
        if (people == null) {
            map.put("result", 0);
            logger.info("删除人员失败");
            map.put("message", "删除人员失败！");
        }else{
            peopleRepository.delete(id);
            documentRepository.delete(people.getIntroduction());
            imageRepository.delete(people.getPortrait());
            logger.info("成功删除人员");
            map.put("result", 1);
            map.put("message", "成功删除人员！");
        }

        return map;
    }
}

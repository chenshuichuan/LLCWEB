package llcweb.com.controller.admin;

import llcweb.com.domain.models.People;
import llcweb.com.service.PeopleService;
import llcweb.com.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
}

package llcweb.com.controller.admin;

import llcweb.com.dao.repository.ActivityRepository;
import llcweb.com.dao.repository.DocumentRepository;
import llcweb.com.domain.entities.PageInfo;
import llcweb.com.domain.models.Activity;
import llcweb.com.service.ActivityService;
import llcweb.com.tools.StringUtil;
import llcweb.com.tools.UsefulTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    private static final Logger logger=LoggerFactory.getLogger(ActivityController.class);

    @Resource
    private ActivityRepository activityRepository;
    @Resource
    private ActivityService activityService;
    @Resource
    private DocumentRepository documentRepository;

    /**
     * @Author haien
     * @Description 首页“中心动态”模块
     * @Date 2018/10/7
     * @Param [count]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/coreDynamics")
    public Map<String,Object> getLatest(@RequestParam("count")Integer count){
        Map<String,Object> map=new HashMap<>();
        if(count==null||count.equals("")||count<=0){
            map.put("result", 0);
            map.put("message", "请正确指定读取数目！");
        }else{
            List<Activity> activities=activityRepository.getLatest(count);
            map.put("result", 1);
            map.put("message", "获取记录成功！");
            map.put("data",UsefulTools.activityToProductInfo(activities));
        }
        return map;
    }

    /**
     * @Author haien
     * @Description 获取具体的活动类
     * @Date 2018/10/9
     * @Param [activityType, count]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/getActivities")
    public Map<String,Object> getActivities(@RequestParam("activityType")String activityType,@RequestParam("count")Integer count){
        Map<String,Object> map=new HashMap<>();
        System.out.println("activityType="+activityType+"  count="+count);
        if(StringUtil.isNull(activityType)){
            map.put("result", 0);
            map.put("message", "请正确指定活动类型！");
        }
        if(count==null||count<=0){
            map.put("result", 0);
            map.put("message", "请正确指定读取数目！");
        }else{
            List<Activity> activityList =  activityRepository.getActivities(activityType,count);
            System.out.println("activity size = "+activityList.size());
            map.put("data",UsefulTools.activityToProductInfo(activityList));
            map.put("result", 1);
            map.put("message", "获取记录成功！");
        }
        return map;
    }
    /**
     * @Author ricardo
     * @Description 获取具体的活动类
     * @Date 2019/2/22
     * @Param [activityType, count]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/getActivitiesById")
    public Map<String,Object> getActivitiesById(@RequestParam("id")Integer id){
        Map<String,Object> map=new HashMap<>();
        logger.info("获取文件信息：id="+id);

        if(null==id||id<=0){
            map.put("result",0);
            map.put("message","参数错误！");
            return map;
        }

        //获取活动
        Activity activity=activityRepository.findOne(id);
        if(activity==null){
            map.put("result",0);
            map.put("message","找不到信息！");
        }else{ //
            map.put("data",activity);
            map.put("result",1);
            map.put("message","成功获取文件！");
        }
        return map;
    }


    /**
     * @Author ricardo
     * @Description 分组获取项目
     * @Date 2018/10/10
     * @Param [count]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/getPage")
    @ResponseBody
    public Map<String,Object> getPage(@RequestParam("activityType")String activityType,@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){
        Map<String,Object> map=new HashMap<>();
        logger.info("activityType="+activityType+",pageNum="+pageNum+",pageSize="+pageSize);

        Page<Activity> activityPage = activityService.getPage(activityType,pageNum-1,pageSize);
        PageInfo pageInfo = new PageInfo(0,UsefulTools.activityToProductInfo(activityPage.getContent()),activityPage.getNumberOfElements());
        pageInfo.setTotalPages(activityPage.getTotalPages());
        map.put("result", 1);
        map.put("message", "获取记录成功！");
        map.put("data",pageInfo);
        return map;
    }
}

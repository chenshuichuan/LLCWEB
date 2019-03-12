package llcweb.com.controller.admin;

import llcweb.com.dao.repository.SoftwareRepository;
import llcweb.com.domain.entities.PageInfo;
import llcweb.com.domain.models.Software;
import llcweb.com.service.SoftwareService;
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
@RequestMapping("/homes/software")
public class SoftwareController {
    private static final Logger logger=LoggerFactory.getLogger(SoftwareController.class);

    @Resource
    private SoftwareRepository softwareRepository;
    @Resource
    private SoftwareService softwareService;

    /**
     * @Author haien
     * @Description 获取最新的软著记录
     * @Date 2018/10/9
     * @Param [activityType, count]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/getLatest")
    public Map<String,Object> getSoftwares(@RequestParam("count")Integer count){
        Map<String,Object> map=new HashMap<>();

        if(count==null||count.equals("")||count<=0){
            map.put("result", 0);
            map.put("message", "请正确指定读取数目！");
        }else{
            List<Software> softwares=softwareRepository.getLatest(count);
            map.put("result", 1);
            map.put("message", "获取记录成功！");
            map.put("data",UsefulTools.softwareToProductInfo(softwares));
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
    public Map<String,Object> getPage(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){
        Map<String,Object> map=new HashMap<>();
        logger.info(",pageNum="+pageNum+",pageSize="+pageSize);

        Page<Software> projectPage = softwareService.getPage(pageNum-1,pageSize);
        PageInfo pageInfo = new PageInfo(0,UsefulTools.softwareToProductInfo(projectPage.getContent()),projectPage.getNumberOfElements());
        pageInfo.setTotalPages(projectPage.getTotalPages());

        map.put("result", 1);
        map.put("message", "获取记录成功！");
        map.put("data",pageInfo);
        return map;
    }
}

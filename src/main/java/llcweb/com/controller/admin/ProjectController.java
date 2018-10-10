package llcweb.com.controller.admin;

import llcweb.com.dao.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private static final Logger logger=LoggerFactory.getLogger(ProjectRepository.class);

    @Resource
    private ProjectRepository projectRepository;

    @RequestMapping("/getProjects")
    public Map<String,Object> getProjects(@RequestParam("count")Integer count){
        Map<String,Object> map=new HashMap<>();

        if(count==null||count.equals("")||count<=0){
            map.put("result", 0);
            map.put("message", "请正确指定读取数目！");
        }else{
            projectRepository.getProjects(count);
            map.put("result", 1);
            map.put("message", "获取记录成功！");
        }
        return map;
    }
}

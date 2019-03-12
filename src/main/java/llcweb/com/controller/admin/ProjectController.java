package llcweb.com.controller.admin;

import llcweb.com.dao.repository.ProjectRepository;
import llcweb.com.domain.entities.PageInfo;
import llcweb.com.domain.models.Project;
import llcweb.com.service.ProjectService;
import llcweb.com.tools.UsefulTools;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @param
 * @author tong
 * @Description:用于项目类的数据接口
 * @Date:15:03 2018/9/9
 */
@Controller
@RequestMapping("/project")
public class ProjectController {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectService projectService;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }

    /**
     * @Author haien
     * @Description 获取最新的项目记录
     * @Date 2018/10/10
     * @Param [count]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/getLatest")
    @ResponseBody
    public Map<String,Object> getProjects(@RequestParam("count")Integer count){
        Map<String,Object> map=new HashMap<>();

        if(count==null||count.equals("")||count<=0){
            map.put("result", 0);
            map.put("message", "请正确指定读取数目！");
        }else{
            List<Project> projects=projectRepository.getProjects(count);
            map.put("result", 1);
            map.put("message", "获取记录成功！");
            map.put("data",UsefulTools.projectToProductInfo(projects));
        }
        return map;
    }


    /*
     * 根据项目id查询
     */
    @RequestMapping(value = "/getProjectById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getProjectById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") int id) {
        Map<String, Object> map = new HashMap<String, Object>();

        Project project = projectRepository.findOne(id);
        if (project != null) {
            map.put("result", 1);
            map.put("message", "成功获取项目！");
            logger.info("成功获取项目！");
        } else {
            map.put("result", 0);
            map.put("message", "获取项目失败！");
            logger.error("获取项目失败！");
        }
        map.put("data", project);
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
    public Map<String,Object> getPage(@RequestParam("team")String team,@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){
        Map<String,Object> map=new HashMap<>();
        logger.info("team="+team+",pageNum="+pageNum+",pageSize="+pageSize);

        Page<Project> projectPage = projectService.getPage(team,pageNum-1,pageSize);
        PageInfo pageInfo = new PageInfo(0,UsefulTools.projectToProductInfo(projectPage.getContent()),projectPage.getNumberOfElements());
        pageInfo.setTotalPages(projectPage.getTotalPages());
        map.put("result", 1);
        map.put("message", "获取记录成功！");
        map.put("data",pageInfo);
        return map;
    }
}

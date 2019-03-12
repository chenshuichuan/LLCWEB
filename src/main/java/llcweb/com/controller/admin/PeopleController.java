package llcweb.com.controller.admin;

import llcweb.com.dao.repository.DocumentRepository;
import llcweb.com.dao.repository.FilesRepository;
import llcweb.com.dao.repository.PeopleRepository;
import llcweb.com.domain.models.People;
import llcweb.com.service.PeopleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author haien
 * @Description 人员控制器
 * @Date 2018/10/2
 **/
@RestController
@RequestMapping("/homes/people")
public class PeopleController {
    private static final Logger logger = LoggerFactory.getLogger(PeopleController.class);

    @Autowired
    private PeopleRepository peopleRepository;

    /**
     * */
    @RequestMapping("/getByPosition")
    public Map<String, Object> getByPosition(@RequestParam("position") String position) {
        Map<String, Object> map = new HashMap<>();
        logger.info("getByPosition：position=" + position);
        List<People> peopleList = peopleRepository.findByPosition(position);
        map.put("data", peopleList);
        map.put("result", 1);
        map.put("message", "获取成功！");
        return map;
    }

    /**
     * */
    @RequestMapping("/getByPositionAndGrade")
    public Map<String, Object> getByPosition(@RequestParam("position") String position, @RequestParam("grade") int grade) {
        Map<String, Object> map = new HashMap<>();
        logger.info("getByPosition：position=" + position+"  grade="+grade);
        List<People> peopleList;
        if(grade>0) {
            peopleList = peopleRepository.findByPositionAndGrade(position,grade);
        }else {
            peopleList = peopleRepository.findByPosition(position);
        }
        map.put("data", peopleList);
        map.put("result", 1);
        map.put("message", "获取成功！");
        return map;
    }

    /**
     * */
    @RequestMapping("/getById")
    public Map<String, Object> getById(@RequestParam("id") Integer id) {
        Map<String, Object> map = new HashMap<>();
        logger.info("getById：id=" + id);
        People peopleList = peopleRepository.findById(id);
        map.put("data", peopleList);
        map.put("result", 1);
        map.put("message", "获取成功！");
        return map;
    }
}

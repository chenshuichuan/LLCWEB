package llcweb.com.controller.admin;

import llcweb.com.dao.repository.PatentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/patent")
public class PatentController {
    private static final Logger logger=LoggerFactory.getLogger(PatentController.class);

    @Resource
    private PatentRepository patentRepository;

    /**
     * @Author haien
     * @Description 获取最新的几条专利
     * @Date 2018/10/9
     * @Param [count]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/getLatest")
    public Map<String,Object> getLatest(@RequestParam("count")Integer count){
        Map<String,Object> map=new HashMap<>();
        if(count==null||count.equals("")||count<=0){
            map.put("result", 0);
            map.put("message", "请正确指定读取数目！");
        }else{
            patentRepository.getLatest(count);
            map.put("result", 1);
            map.put("message", "获取记录成功！");
        }
        return map;
    }

}

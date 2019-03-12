package llcweb.com.controller.admin;

import llcweb.com.dao.repository.PatentRepository;
import llcweb.com.domain.entities.PageInfo;
import llcweb.com.domain.models.Patent;
import llcweb.com.service.PatentService;
import llcweb.com.tools.UsefulTools;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
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
 * @author tong
 * @Description:用于专利的数据接口
 * @Date: 20:01 2018/09/16
 *
 */

@RestController
@RequestMapping("/homes/patent")
public class PatentController {
	private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PatentRepository patentRepository;
	@Autowired
	private PatentService patentService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
	            dateFormat, false));
	}

	/**
	 * @Author haien
	 * @Description 获取最新的几条专利
	 * @Date 2018/10/9
	 * @Param [count]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("/getLatest")
	@ResponseBody
	public Map<String,Object> getLatest(@RequestParam("count")Integer count){
		Map<String,Object> map=new HashMap<>();
		if(count==null||count.equals("")||count<=0){
			map.put("result", 0);
			map.put("message", "请正确指定读取数目！");
		}else{
			List<Patent> patents=patentRepository.getLatest(count);

			map.put("result", 1);
			map.put("message", "获取记录成功！");
			map.put("data",UsefulTools.patentToProductInfo(patents));
		}
		return map;
	}
	
	/*
	 * 根据专利id查询
	 */
    @RequestMapping(value = "/getPatentById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getProjectById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id")int id){
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	Patent patent = patentRepository.findOne(id);
    	if(patent != null) {
            map.put("result", 1);
            map.put("message", "成功获取专利！");
            logger.info("成功获取专利！");
        }else{
            map.put("result", 0);
            map.put("message", "获取专利失败！");
            logger.error("获取专利失败！");
        }
        map.put("data",patent);
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

		Page<Patent> projectPage = patentService.getPage(pageNum-1,pageSize);
		PageInfo pageInfo = new PageInfo(0,UsefulTools.patentToProductInfo(projectPage.getContent()),projectPage.getNumberOfElements());
		pageInfo.setTotalPages(projectPage.getTotalPages());

		map.put("result", 1);
		map.put("message", "获取记录成功！");
		map.put("data",pageInfo);
		return map;
	}
}

package llcweb.com.controller.admin;

import llcweb.com.dao.repository.PaperRepository;
import llcweb.com.domain.entities.PageInfo;
import llcweb.com.domain.models.Paper;
import llcweb.com.service.PaperService;
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
 * @Description:用于论文的数据接口
 * @Date: 21:23 2018/09/11
 *
 */

@RestController
@RequestMapping("/homes/paper")
public class PaperController {
	private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PaperRepository paperRepository;
	@Autowired
	private PaperService paperService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
	            dateFormat, false));
	}

	/**
	 * @Author haien
	 * @Description 首页“论文”模块
	 * @Date 2018/10/7
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
			List<Paper> papers=paperRepository.getLatest(count);
			map.put("result", 1);
			map.put("message", "获取记录成功！");
			map.put("data",UsefulTools.paperToProductInfo(papers));
		}
		return map;
	}
	/*
	 * 根据论文id查询
	 */
    @RequestMapping(value = "/getPaperById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getProjectById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id")int id){
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	Paper paper = paperRepository.findOne(id);
    	if(paper != null) {
            map.put("result", 1);
            map.put("message", "成功获取论文！");
            logger.info("成功获取论文！");
        }else{
            map.put("result", 0);
            map.put("message", "获取论文失败！");
            logger.error("获取论文失败！");
        }
        map.put("data",paper);
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

		Page<Paper> projectPage = paperService.getPage(pageNum-1,pageSize);
		PageInfo pageInfo = new PageInfo(0,UsefulTools.paperToProductInfo(projectPage.getContent()),projectPage.getNumberOfElements());
		pageInfo.setTotalPages(projectPage.getTotalPages());
		map.put("result", 1);
		map.put("message", "获取记录成功！");
		map.put("data",pageInfo);
		return map;
	}
}
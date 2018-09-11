package llcweb.com.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import llcweb.com.dao.repository.PaperRepository;
import llcweb.com.service.ProjectService;
import llcweb.com.service.UsersService;

/**
 * @author tong
 * @Description:用于论文类的数据接口
 * @Date: 21:23 2018/09/11
 *
 */

@Controller
@RequestMapping("/paper")
public class PaperController {
	private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PaperRepository paperRepository;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private UsersService usersService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
	            dateFormat, false));
	}
}

package llcweb.com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import llcweb.com.dao.repository.PeopleRepository;
import llcweb.com.dao.repository2.AttendanceRepository;
import llcweb.com.domain.entity.UsefulAttendance;
import llcweb.com.domain.models.Attendance;
import llcweb.com.service.AttendanceService;
import llcweb.com.service.UsersService;

@RestController
@RequestMapping("/index")
public class AttendanceController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private PeopleRepository peopleRepository;
    private Attendance attendance;
    private String peopleId; //对应people表的id   
    private String peopleName;//对应people表的名称
    private Date attendanceDate;   //标记当天
    private Date morning;   //早上签到时间
    private Date afternoon;//下午签到时间
    private Date evening;  //晚上签到时间

    
    /**
     * 模糊查找
     */
    @RequestMapping(value = "/search")
    @ResponseBody
    public Map<String,Object> page(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map =new HashMap<String,Object>();

        //直接返回前台
        String draw = request.getParameter("draw");
        //当前数据的起始位置 ，如第10条
        String startIndex = request.getParameter("startIndex");
        //数据长度
        String pageSize = request.getParameter("pageSize");
        int size = Integer.parseInt(pageSize);
        //页码
        int currentPage = Integer.parseInt(startIndex)/size+1;
        //关键词
        String fuzzy = request.getParameter("fuzzySearch");

        logger.info("size = "+size+",currentPage = "+currentPage);

        Page<Attendance> attendancePage = null;
        //模糊查找
        if("true".equals(fuzzy)){
            String searchValue=request.getParameter("fuzzy");
            attendancePage = attendanceService.fuzzySearch(currentPage-1,size,searchValue);
        }
        //高级查找
        else{
            peopleId =request.getParameter("peopleId");
            peopleName =request.getParameter("peopleName");
            String firstDate1=request.getParameter("firstDate");
            String lastDate1=request.getParameter("lastDate");
            //字符串对象转为日期对象
            Date firstDate=null;
            Date lastDate=null;
            try {
                firstDate=new SimpleDateFormat("yyyy-MM-dd").parse(firstDate1);
                lastDate=new SimpleDateFormat("yyyy-MM-dd").parse(lastDate1);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            UsefulAttendance usefulAttendance = new UsefulAttendance(peopleId,peopleName,firstDate,lastDate);
            attendancePage = attendanceService.activeSearch(usefulAttendance,currentPage-1,size);
        }

//        //剔除文档内容，传送轻便
//        List<DocumentInfo> documentInfoList = documentService.documentsToDocumentInfos(documentPage.getContent());
        //总记录数
        long total = attendancePage.getTotalElements();
        logger.info("total="+total);

//        map.put("pageData", documentInfoList);
        map.put("total", total);
        map.put("draw", draw);
        map.put("result", 1);
        map.put("message", "成功获取分页数据！");
        return map;
    }

    @RequestMapping(value="/save")
    public Map<String,Object> save(HttpServletRequest request, HttpServletResponse response,String id){
        Map<String,Object> map =new HashMap<String,Object>();
        id = request.getParameter("id");
        SimpleDateFormat f = new SimpleDateFormat("HHmmss");	//判断时间段样板
        Date adDate=new Date();
        int now= Integer.parseInt(f.format(adDate).toString());//a为Date类
        boolean flag = false;
        
        //将时间转化位6位整数判断时间段
        if (0<now&&now<=60000||233000<now&&now<=235959) {
        	map.put("result", 0);
            map.put("message", "打卡失败,打卡时间未到！");
            return map;
		}
        
        //更新打卡记录
        if (id!=null&&!id.equals("")&&Integer.parseInt(id)>0){
        	attendance = attendanceRepository.findOne(Integer.parseInt(id));
        	if (60000<now&&now<=120000) {
        		if (attendance.getMorning()==null) {
        			attendance.setMorning(adDate);
				}else flag = true;               
			}
        	
        	if (120000<now&&now<=180000) {
        		if (attendance.getAfternoon()==null) {
        			attendance.setAfternoon(adDate);
				}else flag = true;
			}
        	
        	if (180000<now&&now<=233000) {
        		if (attendance.getEvening()==null) {
        			attendance.setEvening(adDate);
				}else flag = true;
			}
        	
        	map = attendanceService.update(attendance);
        	if (flag) {
        		map.put("result", 0);
                map.put("msg", "请不要在同一时段重复打卡！");
			}        	
            logger.error("打卡失败！");
        }
        
        //新建打卡记录
        else {
        	attendance = new Attendance();
        	peopleId = String.valueOf(usersService.getCurrentUser().getPeopleId());
        	if (peopleId == null) {
        		map.put("msg","peopleId不得为空");
        		return map;
			}
        	attendance.setPeopleId(peopleId);
        	attendance.setPeopleName(peopleRepository.findById(Integer.parseInt(peopleId)).getName());
        	attendance.setAttendanceDate(adDate);
        	if (60000<now&&now<=120000) {
            	attendance.setMorning(adDate);
			}//早上时段
        	if (120000<now&&now<=180000) {
        		attendance.setAfternoon(adDate);
			}//中午时段
        	if (180000<now&&now<=233000) {
            	attendance.setEvening(adDate);
			}//晚上时段
        	map = attendanceService.add(attendance);
            logger.info("打卡成功！");            
        }
        
        map.put("data",attendance);
        return map;
    }
    
    @RequestMapping(value="/delete")
    public Map<String,Object> delete(@RequestParam("id")Integer id){
    	Map<String,Object> map = attendanceService.delete(id);        
        return map;
    }
    
    
	public Attendance getAttendance() {
		return attendance;
	}

	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}

	public String getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}

	public String getPeopleName() {
		return peopleName;
	}

	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}

	public Date getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public Date getMorning() {
		return morning;
	}

	public void setMorning(Date morning) {
		this.morning = morning;
	}

	public Date getAfternoon() {
		return afternoon;
	}

	public void setAfternoon(Date afternoon) {
		this.afternoon = afternoon;
	}

	public Date getEvening() {
		return evening;
	}

	public void setEvening(Date evening) {
		this.evening = evening;
	}
   
}


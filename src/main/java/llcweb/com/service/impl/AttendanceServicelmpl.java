package llcweb.com.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import llcweb.com.dao.repository2.AttendanceRepository;
import llcweb.com.domain.models.Attendance;
import llcweb.com.service.AttendanceService;
@Service
public class AttendanceServicelmpl implements AttendanceService{
	@Autowired
    private AttendanceRepository attendanceRepository;
	
	 /**
     * 添加打卡记录
	 * @return 
     */
	@Transactional
    public Map<String, Object> add(Attendance attendance) {
        Map<String,Object> map=new HashMap<>();

        if(attendanceRepository.findOne(attendance.getId())==null){
            if(attendanceRepository.save(attendance)!=null){
                map.put("result",1);
                map.put("msg","打卡成功！");
                return map;
            }
        }
        map.put("result",0);
        map.put("msg","打卡失败，请联系管理员！");
        return map;
    }

    /**
     * 更新打卡记录
     * @return 
     */
    @Transactional
    public Map<String, Object> update(Attendance attendance) {

        Map<String,Object> map=new HashMap<>();

        if(attendanceRepository.findOne(attendance.getId())!=null){
            if(attendanceRepository.save(attendance)!=null){
                map.put("result",1);
                map.put("msg","打卡成功！");
                return map;
            }
        }
        map.put("result",0);
        map.put("msg","打卡失败，请联系管理员！");
        return map;
    }

    /**
     * 删除打卡记录
     * @return 
     */
    @Transactional
    public Map<String, Object> delete(int id) {

        Map<String, Object> map = new HashMap<>();

        if (attendanceRepository.findOne(id) != null) {
        	attendanceRepository.delete(id);;      	
            map.put("result", 1);
            map.put("msg", "打卡记录已删除！");
            return map;
        }
        map.put("result", 0);
        map.put("msg", "删除失败，请确认打卡记录是否存在！");
        return map;
    }

}

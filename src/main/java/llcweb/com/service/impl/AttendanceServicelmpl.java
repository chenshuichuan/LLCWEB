package llcweb.com.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import llcweb.com.dao.repository2.AttendanceRepository;
import llcweb.com.domain.entity.UsefulAttendance;
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

	@Override
	public Page<Attendance> fuzzySearch(int pageNum, int pageSize,String key) {
		// TODO Auto-generated method stub
		//封装关键词
		UsefulAttendance usefulAttendance = new UsefulAttendance(key, key);
		Page<Attendance> attendances = activeSearch(usefulAttendance, pageNum, pageSize);
		return attendances;
	}

	@Override
	public Page<Attendance> activeSearch(UsefulAttendance usefulAttendance, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		//规格定义
        Specification<Attendance> specification =new Specification<Attendance>() {

			@Override
			public Predicate toPredicate(Root<Attendance> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				
				//所有的断言
                List<Predicate> predicates = new ArrayList<>();
                
				//添加断言
                if (StringUtils.isNotBlank(usefulAttendance.getPeopleId())) {
                    Predicate like = cb.like(root.get("peopleId").as(String.class),"%"+usefulAttendance.getPeopleId()+"%");
                    predicates.add(like);
                }
                if (usefulAttendance.getFirstDate() != null) {
                    Predicate greaterThanOrEqualTo = cb.greaterThanOrEqualTo(root.get("attendanceDate"), usefulAttendance.getFirstDate());
                    predicates.add(greaterThanOrEqualTo);
                }
                if (usefulAttendance.getLastDate() != null) {
                    Predicate lessThanOrEqualTo = cb.lessThanOrEqualTo(root.get("attendanceDate"), usefulAttendance.getLastDate());
                    predicates.add(lessThanOrEqualTo);
                }
                if (StringUtils.isNotBlank(usefulAttendance.getPeopleName())) {
                    Predicate like = cb.like(root.get("peopleName"), "%" + usefulAttendance.getPeopleName() + "%");
                    predicates.add(like);
                }
                //？？？
                return cb.and(predicates.toArray(new Predicate[0]));
			}         	
        };
      //按时间排序
        Pageable pageable=new PageRequest(pageNum,pageSize, Sort.Direction.DESC,"attendanceDate");

        //查询
        return attendanceRepository.findAll(specification,pageable);
	}

}

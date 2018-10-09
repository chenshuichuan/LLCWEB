package llcweb.com.service;

import org.springframework.data.domain.Page;

import llcweb.com.domain.entity.UsefulAttendance;
import llcweb.com.domain.models.Attendance;

public interface AttendanceService {
	public boolean add(Attendance attendance);
	public boolean update(Attendance attendance);
	public boolean delete(int id);
	
	/**
     * 模糊查询
     */
	public Page<Attendance> fuzzySearch(int pageNum, int pageSize,String key);
	
	/**
     * 动态查找
     */
	public Page<Attendance> activeSearch(UsefulAttendance usefulAttendance, int pageNum, int pageSize);
}

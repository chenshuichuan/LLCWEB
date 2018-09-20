package llcweb.com.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import llcweb.com.domain.entity.UsefulAttendance;
import llcweb.com.domain.models.Attendance;

public interface AttendanceService {
	public Map<String,Object> add(Attendance attendance);
	public Map<String,Object> update(Attendance attendance);
	public Map<String,Object> delete(int id);
	public Page<Attendance> fuzzySearch(int i, int size, String searchValue);
	public Page<Attendance> activeSearch(UsefulAttendance usefulAttendance, int i, int size);
}

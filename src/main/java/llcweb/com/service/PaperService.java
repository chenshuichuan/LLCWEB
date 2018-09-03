package llcweb.com.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import llcweb.com.domain.entity.UsefulPaper;
import llcweb.com.domain.models.Paper;
import llcweb.com.domain.models.Paper;
import llcweb.com.domain.models.Users;

public interface PaperService {
	/**
	 * 动态查询
	 * @param paper
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public Page<Paper> findAll(UsefulPaper paper, int pageNum, int pageSize);
	 
	 public List<Paper> selectAll(Users user);
	 
	 public Map<String, Object> add(Paper paper);
	 public Map<String, Object> update(Paper paper);
	 public Map<String, Object> delete(Paper paper);
	 
	 Page<Paper> getPage(int pageNum, int pageSize, Paper paper);
}

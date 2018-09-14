package llcweb.com.service;

import org.springframework.data.domain.Page;

import llcweb.com.domain.entity.UsefulPaper;
import llcweb.com.domain.models.Paper;

public interface PaperService {
	
	 public Page<Paper> findAll(UsefulPaper paper, int pageNum, int pageSize);
}

package llcweb.com.service;

import org.springframework.data.domain.Page;

import llcweb.com.domain.models.Paper;

public interface PaperService {

	Page<Paper> getPage(int pageNum, int pageSize);

}
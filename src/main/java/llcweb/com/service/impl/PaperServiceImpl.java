package llcweb.com.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import llcweb.com.dao.repository.PaperRepository;
import llcweb.com.domain.entity.UsefulPaper;
import llcweb.com.domain.models.Paper;
import llcweb.com.service.PaperService;

@Service
public class PaperServiceImpl implements PaperService {

	@Autowired
	private PaperRepository paperRepository;


	@Override
	public Page<Paper> getPage(int pageNum, int pageSize) {
		//按时间排序
		Pageable pageable=new PageRequest(pageNum,pageSize, Sort.Direction.DESC,"publicDate");
		Page<Paper> projectPage= paperRepository.findAll(pageable);
		return projectPage;
	}

}

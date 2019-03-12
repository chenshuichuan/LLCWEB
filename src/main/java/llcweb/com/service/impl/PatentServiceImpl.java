package llcweb.com.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import llcweb.com.dao.repository.PatentRepository;
import llcweb.com.domain.models.Patent;
import llcweb.com.service.PatentService;

@Service
public class PatentServiceImpl implements PatentService {
	
	@Autowired
	private PatentRepository patentRepository;

	@Override
	public Page<Patent> getPage(int pageNum, int pageSize) {
		Pageable pageable=new PageRequest(pageNum,pageSize, Sort.Direction.DESC,"appliDate");
		Page<Patent> projectPage= patentRepository.findAll(pageable);
		return projectPage;
	}
	
}

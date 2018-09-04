package llcweb.com.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import llcweb.com.domain.entity.UsefulPatent;
import llcweb.com.domain.models.Patent;
@Service
public interface PatentService {
	public Page<Patent> findAll(UsefulPatent patent, int pageNum, int pageSize);
}

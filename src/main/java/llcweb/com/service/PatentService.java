package llcweb.com.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import llcweb.com.domain.models.Patent;
@Service
public interface PatentService {

	 Page<Patent> getPage(int pageNum, int pageSize);
}

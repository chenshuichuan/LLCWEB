package llcweb.com.service;

import llcweb.com.domain.models.Paper;
import llcweb.com.domain.models.Software;
import org.springframework.data.domain.Page;

public interface SoftwareService {

    Page<Software> getPage(int pageNum, int pageSize);
}

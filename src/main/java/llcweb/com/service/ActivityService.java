package llcweb.com.service;

import llcweb.com.domain.models.Activity;
import org.springframework.data.domain.Page;

public interface ActivityService {

    Page<Activity> getPage(String team,int pageNum,int pageSize);
}

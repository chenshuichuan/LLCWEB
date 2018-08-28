package llcweb.com.service;

import llcweb.com.domain.entity.UsefulConference;
import org.springframework.data.domain.Page;

public interface ConferenceService<T> {

    public Page<T> findAll(UsefulConference conference, int pageNum, int pageSize);

}

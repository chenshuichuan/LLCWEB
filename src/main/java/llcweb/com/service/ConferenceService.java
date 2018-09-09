package llcweb.com.service;

import llcweb.com.domain.entity.UsefulConference;
import llcweb.com.domain.models.Conference;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface ConferenceService<T> {

    /**
     * 动态查找
     */
    public Page<T> findAll(UsefulConference conference, int pageNum, int pageSize);

    public Map<String,Object> add(Conference conference);
    public Map<String,Object> delete(int id);
    Map<String,Object> update(Conference conference);
    Map<String,Object> delete(Conference conference);
}

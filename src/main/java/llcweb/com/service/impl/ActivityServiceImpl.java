package llcweb.com.service.impl;

import llcweb.com.dao.repository.ActivityRepository;
import llcweb.com.domain.models.Activity;
import llcweb.com.service.ActivityService;
import llcweb.com.tools.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public Page<Activity> getPage(String activityType, int pageNum, int pageSize) {
        //按时间排序
        Pageable pageable=new PageRequest(pageNum,pageSize, Sort.Direction.DESC,"startDate");
        Page<Activity> projectPage=null;

        if(StringUtil.isNull(activityType) ||"all".equals(activityType)){
            projectPage = activityRepository.findAll(pageable);
        }else{
            projectPage = activityRepository.findByActivityType(activityType,pageable);
        }
        return projectPage;
    }
}

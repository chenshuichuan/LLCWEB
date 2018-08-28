package llcweb.com.service.impl;

import llcweb.com.dao.repository.ConferenceRepository;
import llcweb.com.domain.entity.UsefulConference;
import llcweb.com.domain.models.Conference;
import llcweb.com.service.ConferenceService;
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

@Service
public class ConferenceServiceImpl implements ConferenceService {

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Override
    public Page<Conference> findAll(UsefulConference conference, int pageNum, int pageSize) {

        //按时间排序
        Pageable pageable=new PageRequest(pageNum,pageSize, Sort.Direction.DESC,"date");

        Page<Conference> conferences = conferenceRepository.findAll(new Specification<Conference>() {
            @Override
            public Predicate toPredicate(Root<Conference> root, CriteriaQuery<?> query, CriteriaBuilder cd) {
                Predicate predicate = cd.conjunction();
                if (conference.getAuthor() != null) {
                    predicate.getExpressions().add(cd.like(root.get("author"), "%" + conference.getAuthor() + "%"));
                }
                if (conference.getFirstDate() != null) {
                    predicate.getExpressions().add(cd.greaterThanOrEqualTo(root.get("date"), conference.getFirstDate()));
                }
                if (conference.getLastDate() != null) {
                    predicate.getExpressions().add(cd.lessThanOrEqualTo(root.get("date"), conference.getLastDate()));
                }
                if (conference.getTitle() != null) {
                    predicate.getExpressions().add(cd.like(root.get("title"), "%" + conference.getTitle() + "%"));
                }
                if (conference.getModel() != null) {
                    predicate.getExpressions().add(cd.like(root.get("model"), "%" + conference.getModel() + "%"));
                }
                if (conference.getType() != null) {
                    predicate.getExpressions().add(cd.like(root.get("type"), "%" + conference.getType() + "%"));
                }
                return predicate;
            }
        }, pageable);

        return conferences;
    }
}

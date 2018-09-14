package llcweb.com.service.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import llcweb.com.dao.repository.PatentRepository;
import llcweb.com.domain.entity.UsefulPatent;
import llcweb.com.domain.models.Paper;
import llcweb.com.domain.models.Patent;
import llcweb.com.service.PatentService;

public class PatentServiceImpl implements PatentService {
	@Autowired
	private PatentRepository patentRepository;
	


	@Override
	public Page<Patent> findAll(UsefulPatent patent, int pageNum, int pageSize) {
		
		 //按公开时间排序
	    Pageable pageable=new PageRequest(pageNum,pageSize, Sort.Direction.DESC,"publicDate");
		
	    
	    Page<Patent> patentList = patentRepository.findAll(new Specification<Patent>() {
        	
        	@Override
        	public Predicate toPredicate(Root<Patent> root, CriteriaQuery<?> query, CriteriaBuilder cd) {
				 Predicate predicate = cd.conjunction();
				 if (patent.getFirstDate() != null) {
	                    predicate.getExpressions().add(cd.greaterThanOrEqualTo(root.get("publicDate"), patent.getFirstDate()));
	                }
	             if (patent.getLastDate() != null) {
	                    predicate.getExpressions().add(cd.lessThanOrEqualTo(root.get("publicDate"), patent.getLastDate()));
	                }
				 if(patent.getTitle() != null) {
					 predicate.getExpressions().add(cd.like(root.get("title"),"%" +  patent.getTitle() + "%"));
				 }
				 if(patent.getAuthorList() != null) {
					 predicate.getExpressions().add(cd.like(root.get("authorList"),"%" +  patent.getAuthorList() + "%"));
				 }
				 if(patent.getBelongProject() != null) {
					 predicate.getExpressions().add(cd.like(root.get("belongProject"),"%" +  patent.getBelongProject() + "%"));
				 }
				 if(patent.getAgency() != null) {
					 predicate.getExpressions().add(cd.like(root.get("agency"),"%" +  patent.getAgency() + "%"));
				 }
				 if(patent.getPublicNum() != null) {
					 predicate.getExpressions().add(cd.like(root.get("publicNum"),"%" +  patent.getPublicNum()+ "%"));
				 }
				return predicate;
				
			}
        }, pageable);
        
		return patentList;
	}

}

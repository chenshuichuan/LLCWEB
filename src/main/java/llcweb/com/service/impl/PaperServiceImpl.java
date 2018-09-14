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
import org.springframework.stereotype.Service;

import llcweb.com.dao.repository.PaperRepository;
import llcweb.com.domain.entity.UsefulPaper;
import llcweb.com.domain.models.Paper;
import llcweb.com.domain.models.Project;
import llcweb.com.service.PaperService;

@Service
public class PaperServiceImpl implements PaperService {
	
	@Autowired
	private PaperRepository paperRepository;
	
	@Override
	public Page<Paper> findAll(UsefulPaper paper, int pageNum, int pageSize) {
		
		 //按时间排序
        Pageable pageable=new PageRequest(pageNum,pageSize, Sort.Direction.DESC,"date");
        
       
        Page<Paper> paperList = paperRepository.findAll(new Specification<Paper>() {
        	
        	@Override
        	public Predicate toPredicate(Root<Paper> root, CriteriaQuery<?> query, CriteriaBuilder cd) {
				 Predicate predicate = cd.conjunction();
				 if (paper.getFirstDate() != null) {
	                    predicate.getExpressions().add(cd.greaterThanOrEqualTo(root.get("date"), paper.getFirstDate()));
	                }
	             if (paper.getLastDate() != null) {
	                    predicate.getExpressions().add(cd.lessThanOrEqualTo(root.get("date"), paper.getLastDate()));
	                }
				 if(paper.getTitle() != null) {
					 predicate.getExpressions().add(cd.like(root.get("title"),"%" +  paper.getTitle() + "%"));
				 }
				 if(paper.getAuthorList() != null) {
					 predicate.getExpressions().add(cd.like(root.get("authorList"),"%" +  paper.getAuthorList() + "%"));
				 }
				 if(paper.getBelongProject() != null) {
					 predicate.getExpressions().add(cd.like(root.get("belongProject"),"%" +  paper.getBelongProject() + "%"));
				 }
				 if(paper.getPeriodical() != null) {
					 predicate.getExpressions().add(cd.like(root.get("periodical"),"%" +  paper.getPeriodical() + "%"));
				 }
				return predicate;
				
			}
        }, pageable);
        
		return paperList;
	}

	
}

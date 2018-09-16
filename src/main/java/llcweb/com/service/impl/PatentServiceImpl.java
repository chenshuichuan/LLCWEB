package llcweb.com.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.transaction.annotation.Transactional;

import llcweb.com.dao.repository.PatentRepository;
import llcweb.com.domain.entity.UsefulPatent;
import llcweb.com.domain.entity.UsefulPatent;
import llcweb.com.domain.models.Patent;
import llcweb.com.domain.models.Patent;
import llcweb.com.domain.models.Project;
import llcweb.com.domain.models.Patent;
import llcweb.com.domain.models.Roles;
import llcweb.com.domain.models.Users;
import llcweb.com.domain.models.Patent;
import llcweb.com.service.PatentService;

@Service
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
	
	
	/**
	 * 添加专利
	 */

	@Override
	public Map<String, Object> add(Patent patent) {
        Map<String,Object> map=new HashMap<>();

        if(patentRepository.findOne(patent.getId())==null){
            if(patentRepository.save(patent)!=null){
                map.put("result",1);
                map.put("msg","专利添加成功！");
                return map;
            }
        }
        map.put("result",0);
        map.put("msg","添加失败，请确认专利是否已存在！");
        return map;
    }

	/**
	 * 更新修改专利
	 */
	@Override
	public Map<String, Object> update(Patent patent) {
		Map<String,Object> map=new HashMap<>();
        if(patentRepository.findOne(patent.getId())!=null){
            if(patentRepository.save(patent)!=null){
                map.put("result",1);
                map.put("msg","专利信息修改成功");
                return map;
            }
        }
        map.put("result",0);
        map.put("msg","更新失败，请确认专利是否存在！");
        return map;
	}
	
	/**
	 * 删除专利
	 */
	public Map<String, Object> delete(int id) {

        Map<String, Object> map = new HashMap<>();

        if (patentRepository.findOne(id) != null) {
        	patentRepository.delete(id);
            map.put("result", 1);
            map.put("msg", "专利已删除！");
            return map;
        }
        map.put("result", 0);
        map.put("msg", "删除失败，请确认专利是否存在！");
        return map;
    }

	@Override
	public List<UsefulPatent> patentsToUsefulpatent(List<Patent> patentList) {
        List<UsefulPatent> usefulPatentList = new ArrayList<>();
        for (Patent patent: patentList){
            UsefulPatent usefulPatent = new UsefulPatent(patent);
            usefulPatentList.add(usefulPatent);
        }
        return usefulPatentList;
	}

	
}
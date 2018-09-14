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

import llcweb.com.dao.repository.ProjectRepository;
import llcweb.com.domain.entity.UsefulProject;
import llcweb.com.domain.models.Project;
import llcweb.com.service.ProjectService;

/**
 * @author tong
 * 实现项目的一些基本操作
 */

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public Page<Project> findAll(UsefulProject project, int pageNum, int pageSize) {
		
		
        //按时间排序
        Pageable pageable=new PageRequest(pageNum,pageSize, Sort.Direction.DESC,"startDate");
       
		
        Page<Project> projectList = projectRepository.findAll(new Specification<Project>() {

			@Override
			public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cd) {
				 Predicate predicate = cd.conjunction();
				 if (project.getFirstDate() != null) {
	                    predicate.getExpressions().add(cd.greaterThanOrEqualTo(root.get("startDate"), project.getFirstDate()));
	                }
	             if (project.getLastDate() != null) {
	                    predicate.getExpressions().add(cd.lessThanOrEqualTo(root.get("startDate"), project.getLastDate()));
	                }
				 if(project.getResponsiblePerson() != null) {
					 predicate.getExpressions().add(cd.like(root.get("responsiblePeople"),"%" +  project.getResponsiblePerson() + "%"));
				 }
				 if(project.getRequireNum() != null) {
					 predicate.getExpressions().add(cd.like(root.get("requireNum"),"%" +  project.getRequireNum() + "%"));
				 }
				 if(project.getProjectType() != null) {
					 predicate.getExpressions().add(cd.like(root.get("projectType"),"%" +  project.getProjectType() + "%"));
				 }
				 if(project.getProjectName() != null) {
					 predicate.getExpressions().add(cd.like(root.get("projectName"),"%" +  project.getProjectName() + "%"));
				 }
				 if(project.getTeam() != null) {
					 predicate.getExpressions().add(cd.like(root.get("team"),"%" +  project.getTeam() + "%"));
				 }
				return predicate;
				
			}
        }, pageable);
        
		return projectList;
	}

	

}

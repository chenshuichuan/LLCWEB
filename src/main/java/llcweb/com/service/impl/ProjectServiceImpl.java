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

import llcweb.com.dao.repository.ProjectRepository;
import llcweb.com.domain.entity.UsefulProject;
import llcweb.com.domain.models.Project;
import llcweb.com.domain.models.Project;
import llcweb.com.domain.models.Project;
import llcweb.com.domain.models.Project;
import llcweb.com.domain.models.Roles;
import llcweb.com.domain.models.Users;
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

	@Override
	public Map<String, Object> add(Project project) {
        Map<String,Object> map=new HashMap<>();

        if(projectRepository.findOne(project.getId())==null){
            if(projectRepository.save(project)!=null){
                map.put("result",1);
                map.put("msg","项目添加成功！");
                return map;
            }
        }
        map.put("result",0);
        map.put("msg","添加失败，请确认项目是否已存在！");
        return map;
    }

	@Override
	public Map<String, Object> update(Project project) {
		Map<String,Object> map=new HashMap<>();
        if(projectRepository.findOne(project.getId())!=null){
            if(projectRepository.save(project)!=null){
                map.put("result",1);
                map.put("msg","项目信息修改成功");
                return map;
            }
        }
        map.put("result",0);
        map.put("msg","更新失败，请确认项目是否存在！");
        return map;
	}

	@Override
	public Map<String, Object> delete(Project project) {

        Map<String, Object> map = new HashMap<>();

        if (projectRepository.findOne(project.getId()) != null) {
            if (projectRepository.save(project) != null) {
                map.put("result", 1);
                map.put("msg", "项目已删除！");
                return map;
            }
        }
        map.put("result", 0);
        map.put("msg", "删除失败，请确认项目是否存在！");
        return map;
    }

	@Override
	public Page<Project> getPage(int pageNum, int pageSize, Project project) {
	      
			Specification<Project> specification = new Specification<Project>() {
	       	
	       	@Override
	       	public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	       		List<Predicate> predicates = new ArrayList<>();
	       		if(project.getTitle() != null) {
	       			predicates.add(cb.like(root.get("title"),"%" +  project.getTitle() + "%"));
					 }
	       		if(project.getMembers() != null) {
						 predicates.add(cb.like(root.get("members"),"%" +  project.getMembers() + "%"));
						 }
	       		if(project.getProjectName() != null) {
						 predicates.add(cb.like(root.get("projectName"),"%" +  project.getProjectName() + "%"));
						 }
	       		if(project.getProjectType() != null) {
						predicates.add(cb.like(root.get("projectType"),"%" +  project.getProjectType() + "%"));
						 }
				 if(project.getResponsiblePerson() != null) {
					 	predicates.add(cb.like(root.get("responsiblePeople"),"%" +  project.getResponsiblePerson() + "%"));
				 		}
				 if(project.getRequireNum() != null) {
					 	predicates.add(cb.like(root.get("requireNum"),"%" +  project.getRequireNum() + "%"));
				 		}
				 if(project.getTeam() != null) {
					 	predicates.add(cb.like(root.get("team"),"%" +  project.getTeam() + "%"));
				 		}
	            return cb.and(predicates.toArray(new Predicate[0]));
	        }
	    };
	    
	    //分页信息
	    Pageable pageable = new PageRequest(pageNum,pageSize); //页码
	    //查询
	    return projectRepository.findAll(specification,pageable);
	       }
	/**
	 * 权限查看项目
	 */
	@Override
	public List<Project> selectAll(Users user) {
		List<Project> projectList = new ArrayList<>();
		List<Roles> roles = user.getRoles();
		for(Roles role: roles) {
			//管理员查看所有项目
			if(role.getrName().equals("admin")) {
				projectList = projectRepository.findAll();
			}
			else {
				projectList = projectRepository.findByAuthorList(user.getId());
			}
		}
		return projectList;
	}
	

}

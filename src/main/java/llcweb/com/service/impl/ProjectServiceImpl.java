package llcweb.com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import llcweb.com.tools.StringUtil;
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

	/**
	 *  添加项目
	 */
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
	
	/*
	 * 修改项目
	 * 
	 */

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

	/*
	 * 删除项目
	 * @see llcweb.com.service.ProjectService#delete(llcweb.com.domain.models.Project)
	 */
	@Override
	public Map<String, Object> delete(int id) {

        Map<String, Object> map = new HashMap<>();

        if (projectRepository.findOne(id) != null) {
        	projectRepository.delete(id);
            map.put("result", 1);
            map.put("msg", "项目已删除！");
            return map;
        }
        map.put("result", 0);
        map.put("msg", "删除失败，请确认项目是否存在！");
        return map;
    }

    @Override
    public List<UsefulProject> projectsToUsefulProject(List<Project> projectList) {
        return null;
    }

    @Override
    public Page<Project> getPage(String team, int pageNum, int pageSize) {
        //按时间排序
        Pageable pageable=new PageRequest(pageNum,pageSize, Sort.Direction.DESC,"startDate");
        Page<Project> projectPage=null;

        if(StringUtil.isNull(team) ||"all".equals(team)){
            projectPage = projectRepository.findAll(pageable);
        }else{
            projectPage = projectRepository.findByTeam(team,pageable);
        }
        return projectPage;
    }

}

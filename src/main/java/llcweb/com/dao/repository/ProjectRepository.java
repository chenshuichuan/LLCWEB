package llcweb.com.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import llcweb.com.domain.models.Conference;
import llcweb.com.domain.models.Project;


/**
 * Created by:Tong
 * Description: 项目类的repository类
 * Date: 2018/8/24
 */


public interface ProjectRepository extends JpaRepository<Project,Integer>{
	
	
	//分页查询
	Page<Project> findAll(Specification<Project> specification, Pageable pageable);
	
    Page<Project> findByTeam(String team,Pageable pageable);
    
    /**
     * 实现模糊查询
     **/
    @Query(value = "SELECT p from Project p where p.status like %?1% " + 
    		"or p.title like %?1% " + 
    		"or p.responsiblePerson like %?1% "+ 
    		"or p.requireNum like %?1% " + 
    		"or projectType like %?1% " + 
    		"or p.projectName like %?1% " + 
    		"or p.members like %?1% " + 
    		"or p.team like %?1% " + 
    		"or p.hostUnit like %?1% " + 
    		"or p.coUnit like %?1% " + 
    		"or p.undertakeUnit like %?1%")
    Page<Project> findByOneKey(String key, Pageable pageable);
    
	
}



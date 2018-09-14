package llcweb.com.dao.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import llcweb.com.domain.models.Document;
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
    
	
<<<<<<< HEAD
=======
//	//模糊查询
//	@Query("from Project p where p.responsiblePerson like %?1%"
//			+ "or p.requireNum like %?1%"
//			+ "or p.projectType like %?1%"
//			+ "or p.projectName like %?1%")
//	Page<Project> findByOneKey(String key, Pageable pageable);

>>>>>>> 5e07cb37f22ed182747bb81e718abb7e71e1b6ed
}



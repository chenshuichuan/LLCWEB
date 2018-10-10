package llcweb.com.dao.repository;

import llcweb.com.domain.models.Activity;
import llcweb.com.domain.models.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


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
     * @Author haien
     * @Description 获取最新的项目记录
     * @Date 2018/10/9
     * @Param [count]
     * @return java.util.List<llcweb.com.domain.models.Activity>
     **/
	@Query(value="select * from project order by start_date desc limit ?1",nativeQuery = true)
	List<Activity> getProjects(int count);
}



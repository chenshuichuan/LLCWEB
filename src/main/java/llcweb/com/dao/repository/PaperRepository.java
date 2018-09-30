package llcweb.com.dao.repository;

import llcweb.com.domain.models.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by:Tong
 * Description: 论文类的repository类
 * Date: 2018/8/24
 */

public interface PaperRepository extends JpaRepository<Paper,Integer>{
	
	//分页查询
	Page<Paper> findAll(Specification<Paper> spec, Pageable pageable);
	
/*	@Query("from Paper p where p.title like %?1%"
			+ "or p.authorList like %?1%"
			+ "or p.belongProject like %?1%"
			+ "or p.periodical like %?1%"
			)
	Page<Paper> findByOneKey(String key, Pageable pageable);*/

	Page<Paper> findByAuthorList(String userName, Pageable pageable);

	//Page<Paper> findByTeam(String string, Pageable pageable);
	
}

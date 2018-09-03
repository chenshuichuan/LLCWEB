package llcweb.com.dao.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import llcweb.com.domain.models.Patent;

/**
 * Created by:Tong
 * Description: 专利类的repository类
 * Date: 2018/8/24
 */

public interface PatentRepository extends JpaRepository<Patent,Integer>{
	Page<Patent> findAll(Specification<Patent> spec, Pageable pageable);

	List<Patent> findByAuthorList(int id);

	//模糊查询
	@Query("from patent p where p.appliDate like %?1% "
			+ "or p.authorList like %?1% "
			+ "or p.appliNum like %?1% "
			+ "or p.publiNum like %?1% "
			+ "or p.agency like %?1% "
			+ "or p.appliPeople like %?1% ")
	Page<Patent> findByOneKey(String string, PageRequest pageRequest);
}

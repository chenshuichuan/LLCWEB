package llcweb.com.dao.repository;

import llcweb.com.domain.models.Patent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by:Tong
 * Description: 专利类的repository类
 * Date: 2018/8/24
 */

public interface PatentRepository extends JpaRepository<Patent,Integer>{
	Page<Patent> findAll(Specification<Patent> specification, Pageable pageable);

//	List<Patent> findByAuthorList(int id);
/*
	//模糊查询
	@Query("from patent p where p.appliDate like %?1% "
			+ "or p.authorList like %?1% "
			+ "or p.appliNum like %?1% "
			+ "or p.publiNum like %?1% "
			+ "or p.agency like %?1% "
			+ "or p.appliPeople like %?1% ")
	Page<Patent> findByOneKey(String string, PageRequest pageRequest);*/
	Page<Patent> findByAuthorList(String userName, Pageable pageable);
}

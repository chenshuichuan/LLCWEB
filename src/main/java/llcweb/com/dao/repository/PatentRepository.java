package llcweb.com.dao.repository;

import java.util.List;
import llcweb.com.domain.models.Patent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import llcweb.com.domain.models.Patent;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by:Tong
 * Description: 专利类的repository类
 * Date: 2018/8/24
 */

public interface PatentRepository extends JpaRepository<Patent,Integer>{

	Page<Patent> findAll(Specification<Patent> specification, Pageable pageable);



	Page<Patent> findByAuthorList(String userName, Pageable pageable);
//	/*
//	 * 模糊查询
//	 */
//	@Query(value = "SELECT p from Patent p where p.title like %?1%"
//				+ "or p.authorList like %?1%"
//				+ "or p.belongProject like %?1%"
//				+ "or p.appliNum like %?1%"
//				+ "or p.publicNum like %?1%"
//				+ "or p.agency like %?1%"
//				+ "or p.appliPeople like %?1%"
//				)
//	Page<Patent> findByOneKey(String key, Pageable pageable);
	
	
}

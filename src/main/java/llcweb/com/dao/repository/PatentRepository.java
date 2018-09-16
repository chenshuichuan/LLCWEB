package llcweb.com.dao.repository;

import llcweb.com.domain.models.Patent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by:Tong
 * Description: 专利的repository类
 * Date: 2018/8/24
 */

public interface PatentRepository extends JpaRepository<Patent,Integer>{
	
	Page<Patent> findAll(Specification<Patent> specification, Pageable pageable);

	Page<Patent> findByAuthorList(String userName, Pageable pageable);
}

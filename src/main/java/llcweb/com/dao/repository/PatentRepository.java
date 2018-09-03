package llcweb.com.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import llcweb.com.domain.models.Paper;
import llcweb.com.domain.models.Patent;

/**
 * Created by:Tong
 * Description: 专利类的repository类
 * Date: 2018/8/24
 */

public interface PatentRepository extends JpaRepository<Patent,Integer>{
	Page<Patent> findAll(Specification<Patent> spec, Pageable pageable);
}

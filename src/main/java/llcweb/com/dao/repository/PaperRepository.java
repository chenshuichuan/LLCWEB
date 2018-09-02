package llcweb.com.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import llcweb.com.domain.models.Paper;

/**
 * Created by:Tong
 * Description: 论文类的repository类
 * Date: 2018/8/24
 */

public interface PaperRepository extends JpaRepository<Paper,Integer>{
	Page<Paper> findAll(Specification<Paper> spec, Pageable pageable);
}

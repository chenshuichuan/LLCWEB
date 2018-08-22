package llcweb.com.dao.repository;


import llcweb.com.domain.models.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by:Haien
 * Description: 文档类的repository类
 * Date: 2018/8/22
 */
public interface DocumentRepository extends JpaRepository<Document,Integer>{

    Page<Document> findAll(Specification<Document> specification, Pageable pageable);
}

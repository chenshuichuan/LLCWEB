package llcweb.com.dao.repository;


import llcweb.com.domain.models.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by:Haien
 * Description: 文档类的repository类
 * Date: 2018/8/22
 */
public interface DocumentRepository extends JpaRepository<Document,Integer>{

    /**
     * 分页动态查询
     **/
    Page<Document> findAll(Specification<Document> specification, Pageable pageable);
    /**
     * 模糊查询
     **/
    @Query("from Document d where d.author like %?1% or content like %?1% or title like %?1%")
    Page<Document> findByOneKey(String key,Pageable pageable);
}

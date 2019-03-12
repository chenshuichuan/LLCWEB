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

    /**
     * 分页动态查询
     **/
    @Query("from Document d where d.model like %?1%" +
            " or d.title like %?1%" +
            " or d.infor like %?1%" +
            " and d.author like %?2%")
    Page<Document> fuzzySearch(String key,String userName, Pageable pageable);

    Page<Document> findByAuthorId(int id,Pageable pageable);
    Page<Document> findByModel(String model,Pageable pageable);
}

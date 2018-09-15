package llcweb.com.dao.repository;


import llcweb.com.dao.repository.myInterface.ResourceRepository;
import llcweb.com.domain.models.Document;

/**
 * Created by:Haien
 * Description: 文档类的repository类
 * Date: 2018/8/22
 */
/*public interface DocumentRepository extends JpaRepository<Document,Integer> {

    *//**
     * 分页动态查询(只能叫findAll)
     **//*
    Page<Document> findAll(Specification<Document> specification, Pageable pageable);

    Page<Document> findByAuthorId(int id,Pageable pageable);
    Page<Document> findByModel(String model,Pageable pageable);
}*/

public interface DocumentRepository extends ResourceRepository<Document,Integer> {

}

package llcweb.com.dao.repository;


import llcweb.com.domain.models.File;
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
public interface FileRepository extends JpaRepository<File,Integer>{

    /**
     * 分页动态查询
     **/
    Page<File> findAll(Specification<File> specification, Pageable pageable);
    /**
     * 模糊查询
     **/
    @Query("from File f where f.author like %?1% or f.introduction like %?1% or f.model like %?1%")
    Page<File> fuzzySearch(String key, Pageable pageable); //排序放在Pageable中做

    /**
     * 模糊查询
     **/
    @Query("from File f where f.author=?1")
    Page<File> findAllByAuthor(String author, Pageable pageable); //排序放在Pageable中做
}

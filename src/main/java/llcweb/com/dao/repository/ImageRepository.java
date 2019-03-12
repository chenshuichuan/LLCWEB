package llcweb.com.dao.repository;


import llcweb.com.dao.myInterface.ResourceRepository;
import llcweb.com.domain.models.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by:Haien
 * Description: 图片类的repository类
 * Date: 2018/8/22
 */
public interface ImageRepository extends ResourceRepository<Image,Integer> {
    /**
     * 模糊查询
     **/
    @Query("from Image i where i.description like %?1% or i.author like %?1% or i.model like %?1%")
    Page<Image> fuzzySearch(String key,Pageable pageable);
}

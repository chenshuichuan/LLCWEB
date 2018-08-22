package llcweb.com.dao.repository;


import llcweb.com.domain.models.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by:Haien
 * Description: 图片类的repository类
 * Date: 2018/8/22
 */
@Repository
public interface ImageRepository extends JpaRepository<Image,Integer>{
    Page<Image> findAll(Specification<Image> spec, Pageable pageable);

}

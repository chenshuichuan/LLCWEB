package llcweb.com.dao.repository;


import llcweb.com.domain.models.Conference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by:Haien
 * Description: 会议类的repository类
 * Date: 2018/8/28
 */
public interface ConferenceRepository extends JpaRepository<Conference,Integer>{

    /**
     * 分页动态查询
     **/
    Page<Conference> findAll(Specification<Conference> specification,
                           Pageable pageable);
    /**
     * 模糊查询
     **/
    @Query("from Conference c where c.author like %?1% " +
            "or c.model like %?1% " +
            "or c.title like %?1% " +
            "or c.type like %?1%")
    Page<Conference> findByOneKey(String key, Pageable pageable);
}

package llcweb.com.dao.repository;

import llcweb.com.domain.models.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActivityRepository extends JpaRepository<Activity,Integer> {
    /**
     * @Author haien
     * @Description 按照标题、上传者、参与人员、起始时间、组别和类型动态查询
     * @Date 2018/10/6
     * @Param [spec, pageable]
     * @return org.springframework.data.domain.Page<llcweb.com.domain.models.Activity>
     **/
    Page<Activity> findAll(Specification<Activity> spec, Pageable pageable);

    @Query("from Activity a where a.author like %?1%" +
            " or a.title like %?1%" +
            " or a.peopleList like %?1%" +
            " or a.model like %?1%" +
            " or a.activityType like %?1%")
    Page<Activity> fuzzySearch(String key,Pageable pageable);

}

package llcweb.com.dao.repository;


import llcweb.com.domain.models.People;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeopleRepository extends JpaRepository<People,Integer>{
    /**
     * @Author haien
     * @Description 按照姓名、年级动态查询
     * @Date 2018/10/2
     * @Param [spec, pageable]
     * @return org.springframework.data.domain.Page<llcweb.com.domain.models.People>
     **/
    Page<People> findAll(Specification<People> spec, Pageable pageable);

    List<People> findByPosition(String position);
}


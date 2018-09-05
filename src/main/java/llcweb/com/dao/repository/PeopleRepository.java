package llcweb.com.dao.repository;


import llcweb.com.domain.models.People;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeopleRepository extends JpaRepository<People,Integer>{

    Page<People> findAll(Specification<People> spec, Pageable pageable);
    /**
     *根据姓名和密码查询
     */
    People findByNameAndPassword(String name, String passwd);
    /**
     *根据姓名查询
     */
    People findByName(String name);
    /**
     * 根据年级查询
     * */
    List<People> findByGrade(String grade);
    /**
     *根据职位查询
     */
    List<People> findByPosition(String position);
    /**
     *根据加入年份查询
     */
    List<People> findByIntroduction(int introduction);
}


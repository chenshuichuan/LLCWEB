package llcweb.com.dao.repository;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import llcweb.com.domain.models.People;

public interface PeopleRepository extends JpaRepository<People,Integer>{
//    Page<People> findAll(Specification<People> spec, Pageable pageable);
    /**
     *根据姓名和密码查询
     */
    People findByNameAndPasswd(String name, String passwd);
    /**
     *根据id查询
     */
    People findById(int id);
    /**
     *根据姓名查询
     */
    People findByName(String name);
    /**
     *根据年级查询
     */
    People findByGrade(String grade);
    /**
     *根据职位查询
     */
    People findByPosition(String position);
    /**
     *根据加入年份查询
     */
    People findByIntroduction(int introduction);
}

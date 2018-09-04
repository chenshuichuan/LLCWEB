package llcweb.com.dao.repository;

<<<<<<< HEAD
import llcweb.com.domain.models.People;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeopleRepository extends JpaRepository<People,Integer> {
=======
/*public interface PeopleRepository extends JpaRepository<People,Integer>{
>>>>>>> 48976cb113d6a1b6e3afccc1fa4a511297c0948e
    Page<People> findAll(Specification<People> spec, Pageable pageable);
    *//**
     *根据姓名和密码查询
     *//*
    People findByNameAndPassword(String name, String passwd);
    *//**
     *根据姓名查询
     *//*
    People findByName(String name);
    *//**
     *根据年级查询
<<<<<<< HEAD
     */
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
=======
     *//*
    People findByGrade(String grade);
    *//**
     *根据职位查询
     *//*
    People findByPosition(String position);
    *//**
     *根据加入年份查询
     *//*
    People findByIntroduction(int introduction);
}*/
>>>>>>> 48976cb113d6a1b6e3afccc1fa4a511297c0948e

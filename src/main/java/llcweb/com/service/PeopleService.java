package llcweb.com.service;

import llcweb.com.domain.entity.UsefulPeople;
import llcweb.com.domain.models.People;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface PeopleService {
    public Page<People> findAll(Specification<People> spec, Pageable pageable);
    public People findByNameAndPassword(String name, String passwd);
    public People findByName(String name);
    public List<People> findByGrade(String grade);
    public List<People> findByPosition(String position);
    public List<People> findByIntroduction(int introduction);
}

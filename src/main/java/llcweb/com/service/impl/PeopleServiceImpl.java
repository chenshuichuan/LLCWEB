package llcweb.com.service.impl;
import llcweb.com.dao.repository.PeopleRepository;
import llcweb.com.domain.entity.UsefulPeople;
import llcweb.com.domain.models.People;
import llcweb.com.service.PeopleService;
import llcweb.com.tools.PageParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class PeopleServiceImpl implements PeopleService {
    @Autowired
    private PeopleRepository peopleRepository;

    

    @Override
    public Page<People> findAll(Specification<People> spec, Pageable pageable){return null;}
    @Override
    public People findByNameAndPassword(String name, String passwd) {
        People people = peopleRepository.findByNameAndPassword(name, passwd);
        if (people == null) {
            throw new UsernameNotFoundException("未查询到用户：" + name + passwd + "的信息！");
        }
        return people;
    }

    @Override
    public People findByName(String name) {
        People people = peopleRepository.findByName(name);
        if (people == null) {
            throw new UsernameNotFoundException("未查询到用户：" + name + "的信息！");
        }
        return people;
    }

    @Override
    public List<People> findByGrade(String grade){
        List<People> peopleList = peopleRepository.findByGrade(grade);
        if (peopleList == null){
            throw new UsernameNotFoundException("未查询到用户：" + grade + "的信息！");
        }
        return peopleList;
    }

    @Override
    public List<People> findByPosition(String position){
        List<People> peopleList = peopleRepository.findByPosition(position);
        if (peopleList == null){
            throw new UsernameNotFoundException("未查询到用户：" + position + "的信息！");
        }
        return peopleList;
    }

    @Override
    public List<People> findByIntroduction(int introduction){
        List<People> peopleList = peopleRepository.findByIntroduction(introduction);
        if (peopleList == null){
            throw new UsernameNotFoundException("未查询到用户：" + introduction + "的信息！");
        }
        return peopleList;
    }
}










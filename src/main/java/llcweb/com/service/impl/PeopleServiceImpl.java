package llcweb.com.service.impl;
import llcweb.com.dao.repository.PeopleRepository;
import llcweb.com.domain.models.People;
import llcweb.com.service.PeopleService;
import llcweb.com.tools.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {
    @Autowired
    private PeopleRepository peopleRepository;

    @Resource
    private PeopleService peopleService;

    /**
     * @Author haien
     * @Description 按照姓名、年级和职位动态查询
     * @Date 2018/10/2
     * @Param [spec, pageable]
     * @return org.springframework.data.domain.Page<llcweb.com.domain.models.People>
     **/
    @Override
    public Page<People> activeSearch(People people, int pageNum, int pageSize) {
        //规格定义
        Specification<People> specification=new Specification<People>() {
            @Override
            public Predicate toPredicate(Root<People> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //所有的断言
                List<Predicate> predicates=new ArrayList<>();
                //添加断言
                if(!StringUtil.isNull(people.getName())){
                    Predicate like=cb.like(root.get("name"),"%"+people.getName()+"%");
                    predicates.add(like);
                }
                if(people.getGrade()!=0){
                    Predicate like=cb.equal(root.get("grade"),people.getGrade());
                    predicates.add(like);
                }
                if(!StringUtil.isNull(people.getPosition())){
                    Predicate equal=cb.equal(root.get("position"),people.getPosition());
                    predicates.add(equal);
                }
                //将List转换为数组
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        };

        //按年份排序
        Pageable pageable=new PageRequest(pageNum,pageSize,Sort.Direction.DESC,"grade");
        //查询
        return peopleRepository.findAll(specification,pageable);
    }
}










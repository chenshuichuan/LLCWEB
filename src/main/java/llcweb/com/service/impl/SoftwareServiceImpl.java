package llcweb.com.service.impl;

import llcweb.com.dao.repository.SoftwareRepository;
import llcweb.com.domain.models.Software;
import llcweb.com.service.SoftwareService;
import llcweb.com.tools.StringUtil;
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
public class SoftwareServiceImpl implements SoftwareService {
    @Resource
    private SoftwareRepository softwareRepository;

    @Override
    public Page<Software> getPage(int pageNum, int pageSize) {
        Pageable pageable=new PageRequest(pageNum,pageSize, Sort.Direction.DESC,"appliDate");
        Page<Software> projectPage= softwareRepository.findAll(pageable);
        return projectPage;
    }
}

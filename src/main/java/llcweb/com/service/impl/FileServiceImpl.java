package llcweb.com.service.impl;

import llcweb.com.dao.repository.FileRepository;
import llcweb.com.domain.entity.UsefulFile;
import llcweb.com.domain.models.File;
import llcweb.com.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public Page<File> findAll(UsefulFile file, int pageNum, int pageSize) {

        //按时间排序
        Pageable pageable=new PageRequest(pageNum,pageSize, Sort.Direction.DESC,"date");

        Page<File> fileList = fileRepository.findAll(new Specification<File>() {
            @Override
            public Predicate toPredicate(Root<File> root, CriteriaQuery<?> query, CriteriaBuilder cd) {
                Predicate predicate = cd.conjunction();
                if (file.getOwner() != null) {
                    predicate.getExpressions().add(cd.like(root.get("owner"), "%" + file.getOwner() + "%"));
                }
                if (file.getFirstDate() != null) {
                    predicate.getExpressions().add(cd.greaterThanOrEqualTo(root.get("date"), file.getFirstDate()));
                }
                if (file.getLastDate() != null) {
                    predicate.getExpressions().add(cd.lessThanOrEqualTo(root.get("date"), file.getLastDate()));
                }
                if (file.getIntroduction() != null) {
                    predicate.getExpressions().add(cd.like(root.get("introduction"), "%" + file.getIntroduction() + "%"));
                }
                if (file.getModel() != null) {
                    predicate.getExpressions().add(cd.like(root.get("model"), "%" + file.getModel() + "%"));
                }
                return predicate;
            }
        }, pageable);

        return fileList;
    }
}

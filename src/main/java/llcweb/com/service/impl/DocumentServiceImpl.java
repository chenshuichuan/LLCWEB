package llcweb.com.service.impl;

import llcweb.com.dao.repository.DocumentRepository;
import llcweb.com.domain.UsefulDocument;
import llcweb.com.domain.models.Document;
import llcweb.com.service.DocumentService;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public Page<Document> findAll(UsefulDocument document, int pageNum, int pageSize) {

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        if (document.getFirstDate() != null) {
            orders.add(new Sort.Order(Sort.Direction.DESC, "createDate"));
        } else if (document.getLastDate() != null) {
            orders.add(new Sort.Order(Sort.Direction.DESC, "createDate"));
        } else {
            orders.add(new Sort.Order(Sort.Direction.ASC, "id"));
        }
        Sort sort = new Sort(orders);
        Pageable pageable=new PageRequest(pageNum,pageSize,sort);

        Page<Document> documentList = documentRepository.findAll(new Specification<Document>() {
            @Override
            public Predicate toPredicate(Root<Document> root, CriteriaQuery<?> query, CriteriaBuilder cd) {
                Predicate predicate = cd.conjunction();
                if (document.getAuthor() != null) {
                    predicate.getExpressions().add(cd.like(root.get("author"), "%" + document.getAuthor() + "%"));
                }
                if (document.getFirstDate() != null) {
                    predicate.getExpressions().add(cd.greaterThanOrEqualTo(root.get("createDate"), document.getFirstDate()));
                }
                if (document.getLastDate() != null) {
                    predicate.getExpressions().add(cd.lessThanOrEqualTo(root.get("createDate"), document.getLastDate()));
                }
                if (document.getTitle() != null) {
                    predicate.getExpressions().add(cd.like(root.get("title"), "%" + document.getTitle() + "%"));
                }
                return predicate;
            }
        }, pageable);

        return documentList;
    }
}

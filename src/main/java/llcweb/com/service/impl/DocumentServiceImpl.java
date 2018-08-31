package llcweb.com.service.impl;

import llcweb.com.dao.repository.DocumentRepository;
import llcweb.com.domain.entities.DocumentInfo;
import llcweb.com.domain.entity.UsefulDocument;
import llcweb.com.domain.models.Document;
import llcweb.com.service.DocumentService;
import org.apache.commons.lang3.StringUtils;
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

        //按时间排序
        Pageable pageable=new PageRequest(pageNum,pageSize, Sort.Direction.DESC,"createDate");

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
                if (document.getModel() != null) {
                    predicate.getExpressions().add(cd.like(root.get("model"), "%" + document.getModel() + "%"));
                }
                return predicate;
            }
        }, pageable);

        return documentList;
    }
    @Override
    public List<DocumentInfo> documentsToDocumentInfos(List<Document> documentList){
        List<DocumentInfo> documentInfoList = new ArrayList<>();
        for (Document document: documentList){
            DocumentInfo documentInfo = new DocumentInfo(document);
            documentInfoList.add(documentInfo);
        }
        return documentInfoList;
    }

    @Override
    public Page<Document> getPage(int pageNum, int pageSize, Document example) {
        //规格定义
        Specification<Document> specification = new Specification<Document>() {

            @Override
            public Predicate toPredicate(Root<Document> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>(); //所有的断言
                if(StringUtils.isNotBlank(example.getAuthor())){ //添加断言
                    Predicate like = cb.like(root.get("author").as(String.class),"%"+example.getAuthor()+"%");
                    predicates.add(like);
                }
                if(StringUtils.isNotBlank(example.getContent())){ //添加断言
                    Predicate like = cb.like(root.get("content").as(String.class),"%"+example.getContent()+"%");
                    predicates.add(like);
                }
                if(StringUtils.isNotBlank(example.getTitle())){ //添加断言
                    Predicate like = cb.like(root.get("title").as(String.class),"%"+example.getTitle()+"%");
                    predicates.add(like);
                }
                if(StringUtils.isNotBlank(example.getInfor())){ //添加断言
                    Predicate like = cb.like(root.get("infor").as(String.class),"%"+example.getInfor()+"%");
                    predicates.add(like);
                }
                if(StringUtils.isNotBlank(example.getModel())){ //添加断言
                    Predicate like = cb.like(root.get("model").as(String.class),"%"+example.getModel()+"%");
                    predicates.add(like);
                }
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        };
        //分页信息
        Pageable pageable = new PageRequest(pageNum,pageSize); //页码：前端从1开始，jpa从0开始，做个转换
        //查询
        return documentRepository.findAll(specification,pageable);
    }
}

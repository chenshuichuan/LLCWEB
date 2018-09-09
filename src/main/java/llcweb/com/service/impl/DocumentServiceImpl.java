package llcweb.com.service.impl;

import llcweb.com.dao.repository.DocumentRepository;
import llcweb.com.domain.entities.DocumentInfo;
import llcweb.com.domain.entity.UsefulDocument;
import llcweb.com.domain.models.Document;
import llcweb.com.domain.models.Roles;
import llcweb.com.domain.models.Users;
import llcweb.com.service.DocumentService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DocumentServiceImpl implements DocumentService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DocumentRepository documentRepository;

    /**
      * 动态查找
     */
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


    /**
     * 查找用户编辑过的文档
     */
    @Override
    public Page<Document> selectAll(Users user,int pageNum,int pageSize) {

        Page<Document> documents;
        List<Roles> roles=user.getRoles();
        Pageable page=new PageRequest(pageNum,pageSize, Sort.Direction.DESC,"createDate");

        //管理员查看所有文档
        for(Roles role:roles){
            if(role.getrFlag().equals("ADMIN")){
                documents=documentRepository.findAll(page);
                return documents;
            }
        }

        //组长查看本人编辑文档
        for(Roles role:roles){
            //管理员查看所有文档
            if(role.getrFlag().equals("GROUP")){
                documents=documentRepository.findByModel("user.getModel()",page);
                return documents;
            }
        }

        //普通用户查找编辑过的文档
        documents=documentRepository.findByAuthorId(user.getId(),page);
        return documents;
    }

    /**
     * 添加document
     */
    @Override
    public Map<String,Object> add(Document document) {
        Map<String,Object> map=new HashMap<>();

        if(documentRepository.findOne(document.getId())==null){
            if(documentRepository.save(document)!=null){
                map.put("result",1);
                map.put("msg","文档添加成功！");
                return map;
            }
        }
        map.put("result",0);
        map.put("msg","添加失败，请确认文档是否已存在！");
        return map;
    }

    /**
     * 更新document
     */
    //@Override
    public Map<String,Object> update(Document document) {

        Map<String,Object> map=new HashMap<>();

        if(documentRepository.findOne(document.getId())!=null){
            if(documentRepository.save(document)!=null){
                map.put("result",1);
                map.put("msg","文档修改成功！");
                return map;
            }
        }
        map.put("result",0);
        map.put("msg","更新失败，请确认文档是否存在！");
        return map;
    }

    /**
     * 删除document
     */
    @Override
    public Map<String,Object> delete(int id) {

        Map<String, Object> map = new HashMap<>();

        if (documentRepository.findOne(id) != null) {
            documentRepository.delete(id);
            map.put("result", 1);
            map.put("msg", "文档已删除！");
            return map;
        }
        map.put("result", 0);
        map.put("msg", "删除失败，请确认文档是否存在！");
        return map;
    }

    /**
     * ???
     */
    @Override
    public List<DocumentInfo> documentsToDocumentInfos(List<Document> documentList){
        List<DocumentInfo> documentInfoList = new ArrayList<>();
        for (Document document: documentList){
            DocumentInfo documentInfo = new DocumentInfo(document);
            documentInfoList.add(documentInfo);
        }
        return documentInfoList;
    }

    /**
     * 重写第一个方法：动态查找？？？但是没有按时间查找
     */
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
                    logger.info("author="+example.getAuthor());
                }
                if(StringUtils.isNotBlank(example.getContent())){ //添加断言
                    Predicate like = cb.like(root.get("content").as(String.class),"%"+example.getContent()+"%");
                    predicates.add(like);
                    logger.info("author="+example.getAuthor());
                }
                if(StringUtils.isNotBlank(example.getTitle())){ //添加断言
                    Predicate like = cb.like(root.get("title").as(String.class),"%"+example.getTitle()+"%");
                    predicates.add(like);
                    logger.info("author="+example.getAuthor());
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
        Pageable pageable = new PageRequest(pageNum,pageSize);
        //查询
        return documentRepository.findAll(specification,pageable);
    }
}

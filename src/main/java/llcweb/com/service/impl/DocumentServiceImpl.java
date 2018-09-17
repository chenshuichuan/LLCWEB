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
    public Page<Document> activeSearch(UsefulDocument document, int pageNum, int pageSize) {

        //规格定义
        Specification<Document> specification =new Specification<Document>() {
            @Override
            public Predicate toPredicate(Root<Document> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //所有的断言
                List<Predicate> predicates = new ArrayList<>();
                //添加断言
                if (StringUtils.isNotBlank(document.getAuthor())) {
                    Predicate like = cb.like(root.get("author").as(String.class),"%"+document.getAuthor()+"%");
                    predicates.add(like);
                }
                if (document.getFirstDate() != null) {
                    Predicate greaterThanOrEqualTo = cb.greaterThanOrEqualTo(root.get("createDate"), document.getFirstDate());
                    predicates.add(greaterThanOrEqualTo);
                }
                if (document.getLastDate() != null) {
                    Predicate lessThanOrEqualTo = cb.lessThanOrEqualTo(root.get("createDate"), document.getLastDate());
                    predicates.add(lessThanOrEqualTo);
                }
                if (StringUtils.isNotBlank(document.getTitle())) {
                    Predicate like = cb.like(root.get("title"), "%" + document.getTitle() + "%");
                    predicates.add(like);
                }
                if (StringUtils.isNotBlank(document.getModel())) {
                    Predicate like = cb.like(root.get("model"), "%" + document.getModel() + "%");
                    predicates.add(like);
                }
                if(StringUtils.isNotBlank(document.getInfo())){
                    Predicate like = cb.like(root.get("infor"),"%"+document.getInfo()+"%");
                    predicates.add(like);
                }
                //？？？
                return cb.and(predicates.toArray(new Predicate[0]));
            }

        };

        //按时间排序
        Pageable pageable=new PageRequest(pageNum,pageSize, Sort.Direction.DESC,"createDate");

        //查询
        return documentRepository.findAll(specification,pageable);
    }


    /**
     * 根据用户权限获取文档
     */
    @Override
    public Page<Document> selectByRole(Users user,int pageNum,int pageSize) {

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
     * 剔除文档内容
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

}

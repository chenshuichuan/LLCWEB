package llcweb.com.service;

import llcweb.com.domain.entities.DocumentInfo;
import llcweb.com.domain.entity.UsefulDocument;
import llcweb.com.domain.models.Document;
import llcweb.com.domain.models.Users;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;


public interface DocumentService {

    /**
     * 动态查找
     */
    public Page<Document> activeSearch(UsefulDocument document, int pageNum, int pageSize);

    /**
     * 方法定义着不用，因为没有整合全局异常，但是整合了的由于不好也先不用
     **/
    public Page<Document> selectByRole(Users user, int pageNum, int pageSize);
    public Map<String,Object> add(Document document);
    public Map<String,Object> delete(int id);

    /**
     * 文档转换为省略内容的文档
     */
    List<DocumentInfo> documentsToDocumentInfos(List<Document> documentList);
    /**
     * 模糊查询
     */
    Page<Document> fuzzySearch(int pageNum, int pageSize,String key);
}

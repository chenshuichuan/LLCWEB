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

    public Page<Document> selectByRole(Users user,int pageNum,int pageSize);
    public Map<String,Object> add(Document document);
    public Map<String,Object> delete(int id);

    /**
     * ???
     */
    List<DocumentInfo> documentsToDocumentInfos(List<Document> documentList);
    /**
     * ???
     */
    Page<Document> getPage(int pageNum, int pageSize,Document document);
}

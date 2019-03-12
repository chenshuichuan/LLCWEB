
package llcweb.com.controller.admin;

import llcweb.com.dao.repository.DocumentRepository;
import llcweb.com.domain.entities.DocumentInfo;
import llcweb.com.domain.models.Document;
import llcweb.com.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Author: Ricardo
 *@Description: 此文件用于文档类的数据接口
 *@Date: 15:14 2018/8/21
 **/
@Controller
@RequestMapping("/homes/document")
public class DocumnetController {
    private static final Logger logger = LoggerFactory.getLogger(DocumnetController.class);

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentService documentService;



    /**
     * 浏览文档
     **/
    @RequestMapping(value = "/getDocumentById",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getDocumById(HttpServletRequest request, HttpServletResponse response,
                                           @RequestParam("id")Integer id){
        Map<String,Object> map =new HashMap<String,Object>();

        logger.info("---浏览文档：id="+id+"---");

        Document document = documentRepository.findOne(id);
        if(document!=null){
            map.put("result", 1);
            map.put("message", "成功获取文档！");
            logger.info("成功获取文档！");
        }else{
            map.put("result", 0);
            map.put("message", "获取文档失败！");
            logger.error("获取文档失败！");
        }
        map.put("data",document);
        return map;
    }

    /**
     * 删除文档
     **/
    @RequestMapping(value="delete",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> delete(@RequestParam("id")Integer id){
        Map<String,Object> map=new HashMap<>();

        logger.info("删除文档：id="+id);

        Document document=documentRepository.findOne(id);
        if (document == null) {
            map.put("result", 0);
            logger.info("删除文档失败");
            map.put("message", "删除文档失败！");
        }else{
            documentRepository.delete(id);
            logger.info("成功删除文档");
            map.put("result", 1);
            map.put("message", "成功删除文档！");
        }
        return map;
    }

    /**
     * 获取所有的文档标题和id等信息
     **/
    @RequestMapping(value="/getAll",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAll(){
        Map<String,Object> map=new HashMap<>();
        logger.info("获取所有的文档标题和id等信息");

        List<Document> documentList=documentRepository.findAll();

        if (documentList == null||documentList.size()==0) {
            map.put("documentInfoList",null);
            map.put("result", 0);
            logger.info("不存在文档！");
            map.put("message", "不存在文档！");
        }else{
            //剔除文档内容，传送轻便
            List<DocumentInfo> documentInfoList = documentService.documentsToDocumentInfos(documentList);
            map.put("documentInfoList",documentInfoList);
            map.put("result", 1);
            map.put("message", "成功获取文档！");
        }
        return map;
    }
}
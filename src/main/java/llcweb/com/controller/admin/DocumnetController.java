package llcweb.com.controller.admin;

import llcweb.com.dao.repository.DocumentRepository;
import llcweb.com.domain.entities.DocumentInfo;
import llcweb.com.domain.entity.UsefulDocument;
import llcweb.com.domain.models.Document;
import llcweb.com.service.DocumentService;
import llcweb.com.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Author: Ricardo
 *@Description: 此文件用于文档类的数据接口
 *@Date: 15:14 2018/8/21
 **/
@Controller
@RequestMapping("/document")
public class DocumnetController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private UsersService usersService;

    /**
     * 模糊查找
     */
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> page(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map =new HashMap<String,Object>();

        //直接返回前台
        String draw = request.getParameter("draw");
        //当前数据的起始位置 ，如第10条
        String startIndex = request.getParameter("startIndex");
        //数据长度
        String pageSize = request.getParameter("pageSize");
        int size = Integer.parseInt(pageSize);
        //页码
        int currentPage = Integer.parseInt(startIndex)/size+1;
        //关键词
        String fuzzy = request.getParameter("fuzzySearch");

        Page<Document> documentPage = null;
        //模糊查找
        if("true".equals(fuzzy)){
            String searchValue=request.getParameter("fuzzy");
            documentPage = documentService.fuzzySearch(currentPage-1,size,searchValue);
        }
        //高级查找
        else{
            String author=request.getParameter("author");
            String infor=request.getParameter("infor");
            String model=request.getParameter("model");
            String title=request.getParameter("title");
            String firstDate1=request.getParameter("firstDate");
            String lastDate1=request.getParameter("lastDate");
            //字符串对象转为日期对象
            Date firstDate=null;
            Date lastDate=null;
            try {
                firstDate=new SimpleDateFormat("yyyy-MM-dd").parse(firstDate1);
                lastDate=new SimpleDateFormat("yyyy-MM-dd").parse(lastDate1);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            UsefulDocument document=new UsefulDocument(author,title,model,infor,firstDate,lastDate);
            documentPage = documentService.activeSearch(document,currentPage-1,size);
        }

        //剔除文档内容，传送轻便
        List<DocumentInfo> documentInfoList = documentService.documentsToDocumentInfos(documentPage.getContent());
        //总记录数
        long total = documentPage.getTotalElements();
        map.put("pageData", documentInfoList);
        map.put("total", total);
        map.put("draw", draw);
        map.put("result", 1);
        map.put("message", "成功获取分页数据！");
        return map;
    }

    /**
     * 浏览文档
     **/
    @RequestMapping(value = "/getDocumentById",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getDocumById(HttpServletRequest request, HttpServletResponse response,
                                              @RequestParam("id")int id){
        Map<String,Object> map =new HashMap<String,Object>();

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
     * 保存文档，若id不存在则新建文档
     **/
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> save(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map =new HashMap<String,Object>();

        String id = request.getParameter("id");
        String content = request.getParameter("content");
        String title = request.getParameter("title");
        String infor = request.getParameter("infor");
        String group = request.getParameter("group");
        Document document;
        boolean flag = true;

        //更新文档
        if (id!=null&&!id.equals("")&&Integer.parseInt(id)>0){
             document = documentRepository.findOne(Integer.parseInt(id));
            if(document==null){
                flag= false;
            }
        }
        //新建文档 所以前端新建文档时，传的id要为空
        else document = new Document();

        if(flag){
            document.setContent(content);
            document.setTitle(title);
            document.setInfor(infor);
            document.setModel(group);
            documentRepository.save(document);
            map.put("result", 1);
            map.put("message", "成功保存文档！");
            logger.info("成功保存文档！");
        }else{
            map.put("result", 0);
            map.put("message", "保存文档失败！");
            logger.error("保存文档失败！");
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
        Document document=documentRepository.findOne(id);
        if (document == null) {
            map.put("result", 0);
            map.put("message", "删除文档失败！");
            logger.error("删除文档失败！");
        }else{
            documentRepository.delete(id);
            map.put("result", 1);
            map.put("message", "成功删除文档！");
            logger.info("成功删除文档！");
        }
        return map;
    }
}

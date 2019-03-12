package llcweb.com.controller.admin;

import llcweb.com.dao.repository.FilesRepository;
import llcweb.com.domain.models.Files;
import llcweb.com.exception.BusinessException;
import llcweb.com.service.FilesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author haien
 * @Description 文件控制器
 * @Date 22:05 2018/9/5
 **/
@RestController
@RequestMapping("/homes/file")
public class FileController {
    private final static Logger logger=LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FilesService filesService;
    @Autowired
    private FilesRepository fileRepository;

    /**
     * 获取文件链接
     **/
    @RequestMapping("/getPath")
    public Map<String,Object> loadFile(@RequestParam("id")Integer id,HttpServletResponse response){
        Map<String,Object> map=new HashMap<>();
        logger.info("获取文件链接：id="+id);

        if(null==id||id<=0){
            map.put("result",0);
            map.put("message","参数错误！");
            return map;
        }

        //获取文件
        Files file=fileRepository.findOne(id);
        if(file==null){
            map.put("result",0);
            map.put("message","找不到文件！");
        }else{ //获取文件输出流
            try {
                filesService.getOutputStream(file.getUrl(),response);
            } catch (FileNotFoundException e) {
                map.put("result",0);
                map.put("message","找不到该文件！");
            } catch (IOException e) {
                map.put("result",0);
                map.put("message","格式错误！");
            }
            map.put("result",1);
            map.put("message","成功获取文件！");
        }
        return map;
    }


    /**
     * @Author haien
     * @Description 前端请求下载Excel表格，返回一个输出流
     * @Date 11:34 2018/7/22
     * @Param [request, response]
     * @return void
     **/
    @RequestMapping(value = "/getFileById")
    @ResponseBody
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, BusinessException {
        // TODO Auto-generated method stub
        //获得请求文件名
        String encoding = System.getProperty("file.encoding");
        String idStr = request.getParameter("id");
        if(idStr==null||idStr.isEmpty()){
            throw new BusinessException(404,"file not find!");
        }
        //String enFileName = URLEncoder.encode(filename,"utf-8");
        System.out.println("/getFileById id="+idStr);
        int id = Integer.parseInt(idStr);
        Files file =  fileRepository.findOne(id);
        if(file!=null&&file.getUrl()!=null){
            //设置Content-Disposition
            response.setHeader("Content-Disposition", "attachment;filename="+file.getFileName());
            //读取目标文件，通过response将目标文件写到客户端
            //读取文件
            String fileName = new String(file.getFileName().getBytes("UTF-8"),encoding);
            InputStream in = new FileInputStream(fileName);
            OutputStream out = response.getOutputStream();
            //写文件
            int b;
            while((b=in.read())!= -1) {
                out.write(b);
            }
            in.close();
            out.close();
        }


    }


    /**
     * 获取Files 对象信息
     **/
    @RequestMapping("/getFile")
    public Map<String,Object> getFile(@RequestParam("id")Integer id,HttpServletResponse response){
        Map<String,Object> map=new HashMap<>();
        logger.info("获取文件信息：id="+id);

        if(null==id||id<=0){
            map.put("result",0);
            map.put("message","参数错误！");
            return map;
        }

        //获取文件
        Files file=fileRepository.findOne(id);
        if(file==null){
            map.put("result",0);
            map.put("message","找不到文件！");
        }else{ //获取文件输出流
            map.put("data",file);
            map.put("result",1);
            map.put("message","成功获取文件！");
        }
        return map;
    }
}
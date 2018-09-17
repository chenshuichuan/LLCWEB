package llcweb.com.controller.admin;

import llcweb.com.dao.repository.FileRepository;
import llcweb.com.dao.repository.UsersRepository;
import llcweb.com.domain.entity.UsefulFile;
import llcweb.com.domain.models.File;
import llcweb.com.domain.models.Users;
import llcweb.com.exception.BusinessException;
import llcweb.com.service.FileService;
import llcweb.com.service.UsersService;
import llcweb.com.tools.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author haien
 * @Description 图片控制器
 * @Date 22:05 2018/9/5
 **/
@RestController
@RequestMapping("/file")
public class FileController {
    private final static Logger logger=LoggerFactory.getLogger(FileController.class);

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private FileService fileService;
    @Autowired
    private FileRepository fileRepository;

    /**
     * @Author haien
     * @Description 文件上传
     * @Date 2018/9/5
     * @Param [image]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/upload")
    @Transactional
    public Map<String,Object> uploadFile(@RequestParam("file")MultipartFile multipartFile,
                                          @RequestParam("group")String group,
                                          @RequestParam("introduction")String introduction){
        Map<String,Object> map=new HashMap<>();

        //验证文件是否为空
        if(multipartFile.isEmpty() || StringUtil.isNull(multipartFile.getOriginalFilename())){
            map.put("result",0);
            map.put("message","文件为空！");
            return map;
        }

        logger.info("上传文件：name="+multipartFile.getOriginalFilename()+",type="+multipartFile.getContentType());

        /*保存图片*/
        //保存到数据库
        Users user=usersService.getCurrentUser();
        String userName=user.getUsername();
        int userId=usersRepository.findByUsername(userName).getId();
        File file=new File(introduction,new Date(),group,userName,userId,multipartFile.getOriginalFilename());
        //获取数据库生成的id
        file = fileRepository.save(file);
        map.put("result",0);
        map.put("message","上传失败，请确认文件是否已存在！");

        //保存到项目
        String path;
        try {
            //返回图片路径
            path=fileService.saveFile(multipartFile,file);
        } catch (BusinessException e) {
            e.printStackTrace();
            map.put("result",0);
            map.put("message","上传失败，文件格式错误！");
            return map;
        }
        file.setPath(path); //保存图片路径
        fileRepository.save(file);
        return map;
    }

    /**
     * 查找文件
     */
    @RequestMapping(value = "/page",method = RequestMethod.GET)
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

        logger.info("size = "+size+",currentPage = "+currentPage);

        Page<File> filePage = null;
        //模糊查找
        if("true".equals(fuzzy)){
            String searchValue=request.getParameter("fuzzy");
            Pageable pageable=new PageRequest(currentPage-1,size, Sort.Direction.DESC,"date");
            filePage = fileRepository.fuzzySearch(searchValue,pageable);
        }
        //高级查找
        else{
            String author=request.getParameter("author");
            String introduction=request.getParameter("introduction");
            String model=request.getParameter("model");
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

            UsefulFile file=new UsefulFile(introduction,firstDate,lastDate,model,author);
            filePage = fileService.activeSearch(file,currentPage-1,size);
        }

        //总记录数
        long total = filePage.getTotalElements();
        logger.info("total="+total);

        map.put("pageData", filePage);
        map.put("total", total);
        map.put("draw", draw);
        map.put("result", 1);
        map.put("message", "成功获取分页数据！");
        return map;
    }

    /**
     * 获取图片链接
     **/
    /*@RequestMapping("/getPath")
    public Map<String,Object> loadFile(@RequestParam("id")Integer id,HttpServletResponse response){
        Map<String,Object> map=new HashMap<>();
        logger.info("获取文件链接：id="+id);

        if(null==id||id<=0){
            map.put("result",0);
            map.put("message","参数错误！");
            return map;
        }

        //获取图片
        File file=fileRepository.findOne(id);
        if(file==null){
            map.put("result",0);
            map.put("message","找不到文件！");
        }else{ //获取图片输出流
            try {
                fileService.getOutputStream(file,response);
            } catch (FileNotFoundException e) {
                map.put("result",0);
                map.put("message","找不到该图片！");
            } catch (IOException e) {
                map.put("result",0);
                map.put("message","格式错误！");
            }
            map.put("result",1);
            map.put("message","成功获取图片！");
        }
        return map;
    }*/

}
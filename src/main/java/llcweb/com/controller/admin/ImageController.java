package llcweb.com.controller.admin;

import llcweb.com.dao.repository.ImageRepository;
import llcweb.com.dao.repository.UsersRepository;
import llcweb.com.domain.entity.UsefulImage;
import llcweb.com.domain.models.Image;
import llcweb.com.domain.models.Users;
import llcweb.com.exception.BusinessException;
import llcweb.com.service.ImageService;
import llcweb.com.service.UsersService;
import llcweb.com.tools.ImageUtil;
import llcweb.com.tools.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
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
@RequestMapping("/image")
public class ImageController {
    private final static Logger logger=LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageRepository imageRepository;

    /**
     * @Author haien
     * @Description 更新/添加图片
     * @Date 2018/9/5
     * @Param [image]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @PostMapping("/save")
    @Transactional
    public Map<String,Object> saveImage(@RequestParam("file")MultipartFile file,
                                          @RequestParam("group")String group,
                                          @RequestParam("description")String description,
                                          @RequestParam(value="id",required = false)Integer id){
        Map<String,Object> map=new HashMap<>();

        //验证文件是否为空
        if(file.isEmpty() || StringUtil.isNull(file.getOriginalFilename())){
            map.put("result",0);
            map.put("message","图片为空！");
            return map;
        }

        //验证是否图片
        if(!ImageUtil.isImage(file)){
            map.put("result",0);
            map.put("message","文件格式错误！");
            return map;
        }

        /*保存图片*/
        //保存到数据库
            //添加
        Image image=null;
        if (null == id) {
            logger.info("添加图片：name=" + file.getOriginalFilename() + ",type=" + file.getContentType());
            image=new Image();
        }
            //更新
        else if (id > 0) {
            logger.info("更新图片：id=" + id + "name=" + file.getOriginalFilename() + ",type=" + file.getContentType());
            image=imageRepository.findOne(id);
            if(image==null){
                map.put("result", 0);
                map.put("message", "找不到该图片！");
                return map;
            }
        } else {
            map.put("result", 0);
            map.put("message", "参数错误！");
            return map;
        }
        //获取当前用户
        Users user=usersService.getCurrentUser();
        String userName=user.getUsername();
        int userId=usersRepository.findByUsername(userName).getId();
        //保存数据
        if(description ==null || description.length()>50){
            map.put("result", 0);
            map.put("message", "请填写50字内的图片描述！");
            return map;
        }
        image.setDescription(description);
        image.setDate(new Date());
        image.setAuthor(userName);
        image.setAuthorId(userId);
        image.setModel(group);
        image.setOriginalName(file.getOriginalFilename());

        //获取数据库生成的id，用于生成唯一命名
        image = imageRepository.save(image);

        //保存到项目
        String path;
            //更新操作应当先删除原有图片
        if(id!=null&&id>0){
            path=image.getPath();
            //删除图片
            try {
                imageService.deleteImg(path);
            } catch (FileNotFoundException e) {
                map.put("result",0);
                map.put("message","项目中不存在该图片！");
                return map;
            } catch (BusinessException e) {
                e.printStackTrace();
                map.put("result",0);
                map.put("message","删除原图片失败！");
                return map;
            }
        }
        try {
            //返回图片路径
            path=imageService.saveImg(file,image);
        } catch (BusinessException e) {
            e.printStackTrace();
            map.put("result",0);
            map.put("message","上传失败，图片格式错误！");
            return map;
        }
        image.setPath(path); //保存图片路径
        imageRepository.save(image);
        return map;
    }

    /**
     * 删除图片
     */
    @RequestMapping("/delete")
    @Transactional
    public Map<String,Object> deleteImage(@RequestParam("id")Integer id){
        Map<String,Object> map=new HashMap<>();

        logger.info("删除图片：id="+id);

        Image image=null;
        if(id != null && id>0){ //验证参数部分放在controller层，放在service层则删除项目文件、数据库记录分别需要一次验证，更复杂
            //删除项目文件
            image=imageRepository.findOne(id);
            if(image==null){
                map.put("result",0);
                map.put("message","找不到该图片！");
                return map;
            }
            try {
                imageService.deleteImg(image.getPath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                map.put("result",0);
                map.put("message","找不到该图片！");
                return map;
            } catch (BusinessException e) {
                e.printStackTrace();
                map.put("result",0);
                map.put("message",e.getMessage());
                return map;
            }
            //删除数据库记录
            imageRepository.delete(id);
        }
        logger.info("--删除图片"+image.getPath()+"成功--");
        map.put("result",1);
        map.put("message","删除成功！");
        return map;
    }

    /**
     * 查找图片
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

        Page<Image> imagePage = null;
        //模糊查找
        if("true".equals(fuzzy)){
            String searchValue=request.getParameter("fuzzy");
            Pageable pageable=new PageRequest(currentPage-1,size, Sort.Direction.DESC,"date");
            imagePage = imageRepository.fuzzySearch(searchValue,pageable);
        }
        //高级查找
        else{
            String author=request.getParameter("author");
            String description=request.getParameter("description");
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

            UsefulImage image=new UsefulImage(description,firstDate,lastDate,author,model);
            imagePage = imageService.activeSearch(image,currentPage-1,size);
        }

        //总记录数
        long total = imagePage.getTotalElements();
        logger.info("total="+total);

        map.put("pageData", imagePage);
        map.put("total", total);
        map.put("draw", draw);
        map.put("result", 1);
        map.put("message", "成功获取分页数据！");
        return map;
    }

    /**
     * 获取图片链接
     **/
    @RequestMapping("/getPath")
    public Map<String,Object> loadImage(@RequestParam("id")Integer id,HttpServletResponse response){
        Map<String,Object> map=new HashMap<>();
        logger.info("获取图片链接：id="+id);

        if(null==id||id<=0){
            map.put("result",0);
            map.put("message","参数错误！");
            return map;
        }

        //获取图片
        Image image=imageRepository.findOne(id);
        if(image==null){
            map.put("result",0);
            map.put("message","找不到该图片！");
        }else{ //获取图片输出流
            try {
                imageService.getOutputStream(image,response);
            } catch (FileNotFoundException e) {
                map.put("result",0);
                map.put("message","找不到该图片！");
                return map;
            } catch (IOException e) {
                map.put("result",0);
                map.put("message","格式错误！");
                return map;
            }
            logger.info("成功获取图片链接--项目地址:"+image.getPath());
            map.put("result",1);
            map.put("message","成功获取图片！");
        }
        return map;
    }

}
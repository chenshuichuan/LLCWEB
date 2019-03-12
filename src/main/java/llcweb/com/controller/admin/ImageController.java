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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
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
     * 删除图片
     */
    /**存在问题：当删除不存在路径的图片记录时，无法成功删除记录*/
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
                imageService.deleteResource(image.getPath());
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
                imageService.getOutputStream(image.getPath(),response);
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

    //未成功！改用配置静态资源的方式了
    //http://localhost:8080/imag/getImageById.do?id=10
    @RequestMapping(value = "/getImageById")
    @ResponseBody
    public String getImageById(HttpServletRequest request, HttpServletResponse response,Model model
            ,@RequestParam("id")int id) {
        Image image = imageRepository.findOne(id);
        if(image==null){
            return "error!";
        }
        String imgPath = image.getPath();
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            //String path1 = System.getProperty("user.dir");
            fis = new FileInputStream(imgPath);
            os = response.getOutputStream();
            response.setHeader("Content-type","image/png"); //设置响应内容类型(不管图片是什么格式都能正确显示)
            int count = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                os.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }try {
            fis.close();
            os.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return "ok";
    }
}
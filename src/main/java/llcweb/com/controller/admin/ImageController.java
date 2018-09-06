package llcweb.com.controller.admin;

import llcweb.com.dao.repository.ImageRepository;
import llcweb.com.service.ImageService;
import llcweb.com.service.UsersService;
import llcweb.com.tools.ImageUtil;
import llcweb.com.tools.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author haien
 * @Description 文件控制器
 * @Date 22:05 2018/9/5
 **/
@RestController
@RequestMapping("/image")
public class ImageController {
    private final static Logger logger=LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private UsersService usersService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageRepository imageRepository;

    /**
     * @Author haien
     * @Description 图片上传方法
     * @Date 2018/9/5
     * @Param [image]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/upload")
    public Map<String,Object> uploadImage(@RequestParam("image")MultipartFile image, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();

        if(image.isEmpty() || StringUtil.isNull(image.getOriginalFilename())){
            map.put("result",0);
            map.put("message","图片为空！");
            return map;
        }

        if(!ImageUtil.isImage(image)){
            map.put("result",0);
            map.put("message","文件格式错误！");
            return map;
        }

        logger.info("上传图片：name="+image.getOriginalFilename()+",type="+image.getContentType());

        //保存图片

        return map;
    }
}

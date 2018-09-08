package llcweb.com.controller.admin;

import llcweb.com.dao.repository.ImageRepository;
import llcweb.com.dao.repository.UsersRepository;
import llcweb.com.domain.entity.BusinessException;
import llcweb.com.domain.models.Image;
import llcweb.com.domain.models.Users;
import llcweb.com.service.ImageService;
import llcweb.com.service.UsersService;
import llcweb.com.tools.ImageUtil;
import llcweb.com.tools.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
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
    private UsersRepository usersRepository;
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
    @Transactional
    public Map<String,Object> uploadImage(@RequestParam("file")MultipartFile file,
                                          @RequestParam("group")String group,
                                          @RequestParam("description")String description){
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

        logger.info("上传图片：name="+file.getOriginalFilename()+",type="+file.getContentType());

        /*保存图片*/
        //保存到数据库
        Users user=usersService.getCurrentUser();
        String userName=user.getUsername();
        int userId=usersRepository.findByUsername(userName).getId();
        Image image=new Image(description,new Date(),userName,userId,group);
        int id=0;
        try {
            //获取数据库生成的id
            id=imageService.add(image);
        } catch (BusinessException e) {
            e.printStackTrace();
            map.put("result",0);
            map.put("message","上传失败，请确认图片是否已存在！");
            return map;
        }

        //保存到项目
        image.setId(id); //id用于构建图片名
        String path;
        try {
            //返回图片路径
            path=imageService.saveImg(file,image); //传入图片信息，构建图片名
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
}

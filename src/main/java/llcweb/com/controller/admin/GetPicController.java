package llcweb.com.controller.admin;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import llcweb.com.dao.repository.FilesRepository;
import llcweb.com.domain.models.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetPicController {

    private final static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private Environment env;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private FilesRepository filesRepository;

    /**
     * @param id 根据图片的id读取图片流并发回页面
     * @throws IOException
     */
    @RequestMapping("/getPic")
    public void getPic(@RequestParam("id") Integer id) throws IOException {
        if (id == null) {
            responseErrorMessage(response, "id is null");
            return;
        }
        logger.debug("getPic:id=" + id);
        //获取图片信息
        try {
            Files pic = filesRepository.findOne(id);
            if (pic == null) {
                responseErrorMessage(response, "pic not exist");
                return;
            }
            sendPic(pic);
        } catch (Exception e) {
            responseErrorMessage(response, e.getMessage());
            return;
        }

    }


    /**
     * 根据图片ID获取图片缩放后在返回
     *
     * @param response
     * @param name
     * @param height
     * @param wight
     * @throws IOException
     */
    @RequestMapping("/getPicLimitSize")
    public void getPicLimitSize(HttpServletResponse response, @RequestParam("id") Integer pic_code
            , @RequestParam("height") int height, @RequestParam("wight") int width) throws IOException {
        if (pic_code == null) {
            responseErrorMessage(response, "pic_code is null");
            return;
        }
        logger.debug("getPicLimitSize:+" + pic_code);

        try {

            Files pic = filesRepository.findOne(pic_code);
            if (pic == null){
                throw new RuntimeException("pic not exist");
            }
            String picUrl = pic.getUrl();
            //picUrl = env.getProperty("image.location") + picUrl;


            //读取图片文件
            File f = new File(picUrl);
            BufferedImage bufferedImage = ImageIO.read(f);
            BufferedImage newImage = zoomPic(bufferedImage, width, height);

            //设置图片头
            String suffix = picUrl.substring(picUrl.lastIndexOf(".") + 1);
            if (!suffix.equals("png")) {
                String contentType = new MimetypesFileTypeMap().getContentType(f);
                response.setContentType(contentType);
            } else{
                response.setContentType("image/" + suffix);
            }
            setCache(response);
            ImageIO.write(newImage, suffix, response.getOutputStream());
        } catch (Exception e) {
            responseErrorMessage(response, e.getMessage());
            return;
        }


    }

    /**
     * 对传入的图片进行缩放后返回
     *
     * @param originalImage
     * @param width
     * @param height
     * @return newImage
     */
    private BufferedImage zoomPic(BufferedImage originalImage, int width, int height) {
        logger.debug("zoomPic:originalImage" + originalImage + "width:" + width + "height" + height);
        BufferedImage newImage = new BufferedImage(width, height, originalImage.getType());
        Graphics graphics = newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, width, height, null);
        graphics.dispose();
        return newImage;
    }


    /**
     * 将图片流发到页面
     *
     * @param pic
     * @throws IOException
     */
    private void sendPic(Files pic) throws IOException {
        if (pic == null) {
            responseErrorMessage(response, "pic not exist");
            return;
        }
        String picUrl = pic.getUrl();

        //picUrl = env.getProperty("pic.root.path") + picUrl;
        char newChar = '/';
        char oldChar = '\\';
        picUrl = picUrl.replace(oldChar, newChar);
        logger.debug("picUrl=" + picUrl);
        //读取图片文件
        File f = new File(picUrl);
        BufferedImage bufferedImage = ImageIO.read(f);
        //设置图片头
        String suffix = picUrl.substring(picUrl.lastIndexOf(".") + 1);
        if (!suffix.equals("png")) {
            String contentType = new MimetypesFileTypeMap().getContentType(f);
            response.setContentType(contentType);
        } else{
            response.setContentType("image/" + suffix);
        }
        setCache(response);
        ImageIO.write(bufferedImage, suffix, response.getOutputStream());
    }

    /**
     * 设置图片缓存
     *
     * @param response
     */
    private void setCache(HttpServletResponse response) {
        java.util.Date date = new java.util.Date();
        response.setDateHeader("Last-Modified", date.getTime());
        response.setDateHeader("Expires", date.getTime() + 1000 * 60 * 60 * 24 * 7);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "Pragma");
    }
    private void responseErrorMessage(HttpServletResponse response, String message) throws IOException {
        logger.error(message);
        response.setStatus(404);
        response.getWriter().write(message);
        response.flushBuffer();
    }


}

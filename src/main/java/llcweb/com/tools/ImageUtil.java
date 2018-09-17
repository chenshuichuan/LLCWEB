package llcweb.com.tools;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtil {
    /**
     * @Author haien
     * @Description 判断是否图片
     * @Date 2018/9/6
     * @Param [file]
     * @return boolean
     **/
    public static boolean isImage(MultipartFile file){
        String fileName=file.getOriginalFilename();
        //根据扩展名判断
        if(!fileName.endsWith("jpg")&&!fileName.endsWith("png")&&!fileName.endsWith("gif")){
            return false;
        }

        //根据图片类型判断,防止文件后缀被篡改
        InputStream inputStream = null;
        BufferedImage bi=null;
        try {
            inputStream = file.getInputStream();
            //解码图片文件
            bi=ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //出现不能解码的文件格式
        if(bi==null){
            return false;
        }
        return true;
    }
}

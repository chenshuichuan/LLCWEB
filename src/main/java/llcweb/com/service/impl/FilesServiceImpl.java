package llcweb.com.service.impl;

import llcweb.com.domain.models.Files;
import llcweb.com.service.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class FilesServiceImpl implements FilesService {

    /**
     * @Author haien
     * @Description 客户端获取文件输出流
     * @Date 2018/9/21
     * @Param [path, response]
     * @return void
     **/
    @Override
    public void getOutputStream(String path, HttpServletResponse response) throws IOException {
        byte[] buff = new byte[1024];
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
        //服务器向浏览器发送字节输出流
        OutputStream os = response.getOutputStream();
        //设置响应内容类型(虽然设置的是png,但是连gif也可以正常显示)
        response.setHeader("Content-type","image/png");
        int len = 0;
        while ((len = bis.read(buff)) != -1) {
            os.write(buff, 0, len);
        }
        os.flush();
        bis.close();
    }

}

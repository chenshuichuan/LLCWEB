package llcweb.com.service;

import llcweb.com.domain.models.Files;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FilesService{
    /**
     * 获取文件输出流
     **/
    public void getOutputStream(String path,HttpServletResponse response) throws IOException;
}

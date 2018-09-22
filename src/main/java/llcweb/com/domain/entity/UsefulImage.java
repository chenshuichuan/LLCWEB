package llcweb.com.domain.entity;

import java.util.Date;

/**
 * @Author haien
 * @Description 封装搜索条件的图片类
 * @Date 22:01 2018/8/22
 * @Param
 * @return
 **/
public class UsefulImage extends UsefulResource {

    public UsefulImage() {
    }
    public UsefulImage(String author, String model, String description,
                       Date firstDate, Date lastDate) {
        super(author, null, model, null, description, null, firstDate, lastDate);
    }
}

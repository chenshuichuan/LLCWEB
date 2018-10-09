package llcweb.com.domain.entity;

import java.util.Date;

/**
 * @Author haien
 * @Description 封装搜索信息的文档类, 主要在搜索文档时封装搜索条件，考虑到搜索信息可能与文档类属性有出入，
 *              比如按照时间段搜索，那么应该增加一个时间段的属性
 * @Date 9:55 2018/8/24
 **/
public class UsefulDocument extends UsefulResource{

    public UsefulDocument() {

    }
    public UsefulDocument(String author, String title, String model, String info
                        ,Date firstDate, Date lastDate) {
        super(author, title, model, info, null, null, firstDate, lastDate);
    }
}
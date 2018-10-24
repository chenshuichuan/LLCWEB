package llcweb.com.domain.entity;

import java.util.Date;

/**
 * @Author haien
 * @Description 封装搜索信息的资源类, 主要在搜索文档时封装搜索条件，考虑到搜索信息可能与文档类属性有出入，
 *              比如按照时间段搜索，那么应该增加一个时间段的属性
 * @Date 9:55 2018/8/24
 **/
public class UsefulResource {

    private String author;
    private String title;
    private String model;
    //document简介（或者content，反正哪个是简介用哪个）
    private String info;
    //image描述
    private String description;
    //file简介
    private String introduction;

    /**
     * 按以下时间段搜索
     **/
    private Date firstDate;
    private Date lastDate;

    public UsefulResource() {

    }

    public UsefulResource(String author, String title, String model, String info,
                          String description,String introduction,Date firstDate, Date lastDate) {
        this.author = author;
        this.title = title;
        this.model = model;
        this.info = info;
        this.description=description;
        this.introduction=introduction;
        this.firstDate = firstDate;
        this.lastDate = lastDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        firstDate = firstDate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        lastDate = lastDate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

}
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

    /*public String description;

    //搜索时间段
    public Date firstDate;
    public Date lastDate;

    public String author;
    public String model;

    public UsefulImage() {
    }

    public UsefulImage(String description, Date firstDate, Date lastDate, String author, String model) {
        this.description = description;
        this.firstDate = firstDate;
        this.lastDate = lastDate;
        this.author = author;
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }*/
}

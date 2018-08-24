package llcweb.com.domain.entity;

import java.util.Date;

/**
 * @Author haien
 * @Description 封装搜索条件的图片类
 * @Date 22:01 2018/8/22
 * @Param
 * @return
 **/
public class UsefulImage {
    public int id;
    public String description;

    //搜索时间段
    public Date firstDate;
    public Date lastDate;

    public int ownerId;
    public String owner;

    public UsefulImage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}

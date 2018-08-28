package llcweb.com.domain.entity;

import java.util.Date;

public class UsefulFile {

    //文件简介
    private String introduction;
    //文件日期
    public Date FirstDate;
    public Date LastDate;
    //组别
    private String model;
    //文件上传者
    private String owner;

    public UsefulFile() {
    }

    public UsefulFile(String introduction, Date firstDate, Date lastDate, String model, String owner) {
        this.introduction = introduction;
        FirstDate = firstDate;
        LastDate = lastDate;
        this.model = model;
        this.owner = owner;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getFirstDate() {
        return FirstDate;
    }

    public void setFirstDate(Date firstDate) {
        FirstDate = firstDate;
    }

    public Date getLastDate() {
        return LastDate;
    }

    public void setLastDate(Date lastDate) {
        LastDate = lastDate;
    }
}

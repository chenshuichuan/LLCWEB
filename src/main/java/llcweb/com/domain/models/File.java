package llcweb.com.domain.models;

import llcweb.com.domain.entity.Resource;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="file")
public class File extends Resource {

    //文件简介
    @Column(length = 50)
    private String introduction;
    //文件位于项目中的路径
    private String path;
    //原文件名
    private String originalName;

    public File() {
    }

    public File(String introduction, Date createDate, String model,String author,int authorId,String originalName) {
        super(createDate,model,author,authorId);
        this.introduction = introduction;
        this.originalName=originalName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

}
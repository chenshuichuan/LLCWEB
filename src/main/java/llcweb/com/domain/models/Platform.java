package llcweb.com.domain.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

/**
 * 实验室平台表 llc_platform
 *
 * @author ricardo
 * @date 2019-03-09
 */
@Entity
@Table(name = "llc_platform" )
public class Platform  {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    private Integer id;
    /**
     * 平台名称
     */
    @Column(name = "name" )
    private String name;
    /**
     * 平台转跳地址
     */
    @Column(name = "address" )
    private String address;
    /**
     * 平台介绍视频
     */
    @Column(name = "video_address" )
    private String videoAddress;
    /**
     * j介绍封面图片
     */
    @Column(name = "cover_image" )
    private String coverImage;
    /**  */
    @Column(name = "date" )
    private Date date;
    /**
     * 平台介绍文档
     */
    @Column(name = "introduction" )
    private Integer introduction;
    /**
     * p平台介绍PDF文件
     */
    @Column(name = "pdf_file" )
    private Integer pdfFile;
    /**
     * 是否发布
     */
    @Column(name = "is_publish" )
    private Integer isPublish;
    /**
     * 更新时间
     */
    @Column(name = "update_time" )
    private Date updateTime;

    public Platform() {
        this.updateTime = new Date();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setVideoAddress(String videoAddress) {
        this.videoAddress = videoAddress;
    }

    public String getVideoAddress() {
        return videoAddress;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setIntroduction(Integer introduction) {
        this.introduction = introduction;
    }

    public Integer getIntroduction() {
        return introduction;
    }

    public void setPdfFile(Integer pdfFile) {
        this.pdfFile = pdfFile;
    }

    public Integer getPdfFile() {
        return pdfFile;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id" , getId())
                .append("name" , getName())
                .append("address" , getAddress())
                .append("videoAddress" , getVideoAddress())
                .append("coverImage" , getCoverImage())
                .append("date" , getDate())
                .append("introduction" , getIntroduction())
                .append("pdfFile" , getPdfFile())
                .append("isPublish" , getIsPublish())
                .append("updateTime" , getUpdateTime())
                .toString();
    }
}

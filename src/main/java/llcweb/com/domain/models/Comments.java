package llcweb.com.domain.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

/**
 * 访客留言表 llc_comments
 *
 * @author ricardo
 * @date 2019-03-09
 */
@Entity
@Table(name = "llc_comments" )
public class Comments {
    private static final long serialVersionUID = 1L;

    /**  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    private Integer id;
    /**
     * 姓名
     */
    @Column(name = "name" )
    private String name;
    /**
     * 留言内容
     */
    @Column(name = "content" )
    private String content;
    /**
     * 联系方式
     */
    @Column(name = "contact" )
    private String contact;
    /**
     * 留言时间
     */
    @Column(name = "create_time" )
    private Date createTime;
    /**
     * 管理员是否已经查看过
     */
    @Column(name = "is_view" )
    private Integer isView;
    /**
     * ip地址
     */
    @Column(name = "ip_address" )
    private String ipAddress;

    public Comments() {this.createTime = new Date();

    }

    public Comments(String name,String contact, String content,
                    Date createTime, String ipAddress, Integer isView ) {
        this.name = name;
        this.content = content;
        this.contact = contact;
        this.createTime = createTime;
        this.isView = isView;
        this.ipAddress = ipAddress;
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

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setIsView(Integer isView) {
        this.isView = isView;
    }

    public Integer getIsView() {
        return isView;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id" , getId())
                .append("name" , getName())
                .append("content" , getContent())
                .append("contact" , getContact())
                .append("createTime" , getCreateTime())
                .append("isView" , getIsView())
                .append("ipAddress" , getIpAddress())
                .toString();
    }
}

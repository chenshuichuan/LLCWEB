package llcweb.com.domain;

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
    /** 照片描述(相当于标题）
     *
     * @pdOid 8393be4e-3238-42d5-8eec-5d65bb26e282 */

    public String description;
    /** 照片日期
     *
     * @pdOid ccf35a27-a918-4f8c-b220-ceb345850357 */
    public Date firstDate;
    public Date lastDate;
    /** 照片拥有者、或是上传者id
     *
     * @pdOid f1c4220d-41c3-4f25-a5b2-d941bd4a8b8c */
    public int ownerId;
    /** 照片拥有者、或是上传者
     *
     * @pdOid f1c4220d-41c3-4f25-a5b2-d941bd4a8b8c */
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

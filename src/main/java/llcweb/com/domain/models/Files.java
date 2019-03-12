package llcweb.com.domain.models;

import javax.persistence.*;
import java.util.Date;

/**
 * 文件上传表 sys_files
 * 
 * @author yc
 * @date 2018-12-20
 */
@Entity
@Table(name = "sys_files" )
public class Files
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id" )
	private Integer id;
	/**  */
	@Column(name = "url" )
	private String url;
	/**  */
	@Column(name = "status" )
	private String status;
	/**  */
	@Column(name = "file_name" )
	private String fileName;
	/**  */
	@Column(name = "remark" )
	private String remark;
	/** 类型（0代表图片 1代表视频） */
	@Column(name = "type" )
	private String type;
	/** 删除标志（0代表存在 2代表删除） */
	@Column(name = "del_flag" )
	private String delFlag;
	/** 创建者 */
	@Column(name = "create_by" )
	private String createBy;
	@Column(name = "create_by_name" )
	private String createByName;
	/** 创建时间 */
	@Column(name = "create_time" )
	private Date createTime;
	/** 更新者 */
	@Column(name = "update_by" )
	private String updateBy;
	@Column(name = "update_by_name" )
	private String updateByName;
	/** 更新时间 */
	@Column(name = "update_time" )
	private Date updateTime;

	/**后缀**/
	@Column(name = "suffix" )
	private String suffix;
	@Column(name = "content" )
	private String content;

	/**修改标识 0 新增 1 修改**/
	@Transient
	private int updateFlag;
	@Transient
	private Long channelId;

	public Files() { this.updateTime = new Date();
	}

	public Files(String url, String status, String fileName, String remark, String type,
                 String delFlag, String createBy, String createByName, Date createTime, String updateBy,
                 String updateByName, Date updateTime, String suffix, String content,
                 int updateFlag, Long channelId) {
		this.url = url;
		this.status = status;
		this.fileName = fileName;
		this.remark = remark;
		this.type = type;
		this.delFlag = delFlag;
		this.createBy = createBy;
		this.createByName = createByName;
		this.createTime = createTime;
		this.updateBy = updateBy;
		this.updateByName = updateByName;
		this.updateTime = updateTime;
		this.suffix = suffix;
		this.content = content;
		this.updateFlag = updateFlag;
		this.channelId = channelId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setUrl(String url) 
	{
		this.url = url;
	}

	public String getUrl() 
	{
		return url;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}

	public String getFileName() 
	{
		return fileName;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setType(String type) 
	{
		this.type = type;
	}

	public String getType() 
	{
		return type;
	}
	public void setDelFlag(String delFlag) 
	{
		this.delFlag = delFlag;
	}

	public String getDelFlag() 
	{
		return delFlag;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	public String getUpdateBy() 
	{
		return updateBy;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public String getUpdateByName() {
		return updateByName;
	}

	public void setUpdateByName(String updateByName) {
		this.updateByName = updateByName;
	}

	@Override
	public String toString() {
		return "Files{" +
				"id=" + id +
				", url='" + url + '\'' +
				", status='" + status + '\'' +
				", fileName='" + fileName + '\'' +
				", remark='" + remark + '\'' +
				", type='" + type + '\'' +
				", delFlag='" + delFlag + '\'' +
				", createBy='" + createBy + '\'' +
				", createByName='" + createByName + '\'' +
				", createTime=" + createTime +
				", updateBy='" + updateBy + '\'' +
				", updateByName='" + updateByName + '\'' +
				", updateTime=" + updateTime +
				'}';
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public int getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(int updateFlag) {
		this.updateFlag = updateFlag;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
}

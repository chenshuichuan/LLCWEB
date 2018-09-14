package llcweb.com.domain.models;


import java.util.Date;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project {
	
	//项目id
    @Id
    @GeneratedValue
    private Integer id;
    
    //项目状态
    @Column(name = "status", length = 16)
    private String status;
    
    //项目经费
    @Column(name = "money", length = 64)
    private String money;
    
    //结束时间
    @Column(name = "end_date")
    private Date endDate;
    
    //开始时间
    @Column(name = "start_date")
    private Date startDate;
    
    //负责人职称
    @Column(name = "title", length = 64)
    private String title;
    
    //项目负责人
    @Column(name = "responsible_person", length = 64)
    private String responsiblePerson;
    
    //项目编号
    @Column(name = "require_num", length = 128)
    private String requireNum;
    
    //项目类型
    @Column(name = "project_type", length = 128)
    private String projectType;
    
    //项目名称
    @Column(name = "project_name", length = 128)
    private String projectName;
    
    //项目人员
    @Column(name = "members", length = 128)
    private String members;
    
    //所属组别
    @Column(name = "team", length = 128)
    private String team;
    
    //项目描述
    @Column(name = "project_des")
    private String projectDes;
    
    //项目目标
    @Column(name = "project_aim")
    private String projectAim;
    
    //主办单位
    @Column(name = "host_unit", length = 64)
    private String hostUnit;
    
    //协办单位
    @Column(name = "co_unit", length = 64)
    private String coUnit;
    
    //承办单位
    @Column(name = "undertake_unit", length = 64)
    private String undertakeUnit;
    
    public Project() {
    	
    }
    
    

	public Project(Integer id, String status, String money, Date endDate, Date startDate, String title,
			String responsiblePerson, String requireNum, String projectType, String projectName, String members,
			String team, String projectDes, String projectAim, String hostUnit, String coUnit, String undertakeUnit) {
		super();
		this.id = id;
		this.status = status;
		this.money = money;
		this.endDate = endDate;
		this.startDate = startDate;
		this.title = title;
		this.responsiblePerson = responsiblePerson;
		this.requireNum = requireNum;
		this.projectType = projectType;
		this.projectName = projectName;
		this.members = members;
		this.team = team;
		this.projectDes = projectDes;
		this.projectAim = projectAim;
		this.hostUnit = hostUnit;
		this.coUnit = coUnit;
		this.undertakeUnit = undertakeUnit;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getRequireNum() {
		return requireNum;
	}

	public void setRequireNum(String requireNum) {
		this.requireNum = requireNum;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	
	public String getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getMembers() {
		return members;
	}

	public void setMembers(String members) {
		this.members = members;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getProjectDes() {
		return projectDes;
	}

	public void setProjectDes(String projectDes) {
		this.projectDes = projectDes;
	}

	public String getProjectAim() {
		return projectAim;
	}

	public void setProjectAim(String projectAim) {
		this.projectAim = projectAim;
	}

	public String getHostUnit() {
		return hostUnit;
	}

	public void setHostUnit(String hostUnit) {
		this.hostUnit = hostUnit;
	}

	public String getCoUnit() {
		return coUnit;
	}

	public void setCoUnit(String coUnit) {
		this.coUnit = coUnit;
	}

	public String getUndertakeUnit() {
		return undertakeUnit;
	}

	public void setUndertakeUnit(String undertakeUnit) {
		this.undertakeUnit = undertakeUnit;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", status=" + status + ", money=" + money + ", endDate=" + endDate + ", startDate="
				+ startDate + ", title=" + title + ", responsiblePerson=" + responsiblePerson + ", requireNum="
				+ requireNum + ", projectType=" + projectType + ", projectName=" + projectName + ", members=" + members
				+ ", team=" + team + ", projectDes=" + projectDes + ", projectAim=" + projectAim + ", hostUnit="
				+ hostUnit + ", coUnit=" + coUnit + ", undertakeUnit=" + undertakeUnit + "]";
	}
    
    
}


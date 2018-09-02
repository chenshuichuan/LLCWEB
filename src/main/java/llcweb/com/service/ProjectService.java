package llcweb.com.service;

import org.springframework.data.domain.Page;

import llcweb.com.domain.entity.UsefulDocument;
import llcweb.com.domain.entity.UsefulProject;
import llcweb.com.domain.models.Document;
import llcweb.com.domain.models.Project;

/**
 * @author tong
 * @登录以后的项目信息服务接口
 *
 */
public interface ProjectService {
	
	/**
	 * 查询项目并按照时间分页排序
	 * @param project
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */

	public Page<Project> findAll(UsefulProject project, int pageNum, int pageSize);
}

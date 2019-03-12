package llcweb.com.dao.repository;

import llcweb.com.domain.models.Patent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by:Tong
 * Description: 专利类的repository类
 * Date: 2018/8/24
 */

public interface PatentRepository extends JpaRepository<Patent,Integer>{

	Page<Patent> findAll(Specification<Patent> specification, Pageable pageable);


	/**
	 * @Author haien
	 * @Description 获取最新的专利记录
	 * @Date 2018/10/7
	 * @Param [count]
	 * @return java.util.List<llcweb.com.domain.models.Patent>
	 **/
	@Query(value="select * from llc_patent where state='授权' order by public_date desc limit ?1",nativeQuery=true)
	List<Patent> getLatest(int count);


}

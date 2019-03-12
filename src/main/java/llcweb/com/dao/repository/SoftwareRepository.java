package llcweb.com.dao.repository;

import llcweb.com.domain.models.Software;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SoftwareRepository extends JpaRepository<Software,Integer> {
    /**
     * @Author haien
     * @Description 扩展findAll实现动态查询
     * @Date 2018/10/10
     * @Param [spec, pageable]
     * @return org.springframework.data.domain.Page<llcweb.com.domain.models.Software>
     **/
    Page<Software> findAll(Specification<Software> spec, Pageable pageable); //搜索条件全为null时取到全部数据


    /**
     * @Author haien
     * @Description 获取最新的软著记录
     * @Date 2018/10/10
     * @Param [count]
     * @return java.util.List<llcweb.com.domain.models.Software>
     **/
    @Query(value="select * from llc_software where status='发表' order by public_date desc limit ?1",nativeQuery = true)
    List<Software> getLatest(int count);
}

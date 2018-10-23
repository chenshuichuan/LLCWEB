package llcweb.com.dao.repository2;


import llcweb.com.domain.models.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by:ricardo
 * Description: 签到类的repository类
 * Date: 2018/8/22
 */
public interface AttendanceRepository extends JpaRepository<Attendance,Integer>{

    /**
     * 分页动态查询
     **/
    Page<Attendance> findAll(Specification<Attendance> specification, Pageable pageable);

}

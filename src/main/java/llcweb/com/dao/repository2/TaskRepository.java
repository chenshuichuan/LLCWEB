package llcweb.com.dao.repository2;


import llcweb.com.domain.models.File;
import llcweb.com.domain.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by:ricardo
 * Description: 任务类的repository类
 * Date: 2018/8/22
 */
public interface TaskRepository extends JpaRepository<Task,Integer>{

    /**
     * 分页动态查询
     **/
    Page<File> findAll(Specification<File> specification, Pageable pageable);

}

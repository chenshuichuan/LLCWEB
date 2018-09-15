package llcweb.com.dao.repository.myInterface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @Author haien
 * @Description 资源类的泛型接口
 * @Date 2018/9/14
 * @Param
 * @return
 **/
@NoRepositoryBean //不要暴露出来，数据库中没有表和这个接口匹配
public interface ResourceRepository<T,ID extends Serializable> extends JpaRepository<T,ID> { //id序列化,传入id的类型
    /**
     * 分页动态查询(只能叫findAll)
     **/
    Page<T> findAll(Specification<T> specification, Pageable pageable);

    Page<T> findByAuthorId(int id,Pageable pageable);
    Page<T> findByModel(String model,Pageable pageable);
}

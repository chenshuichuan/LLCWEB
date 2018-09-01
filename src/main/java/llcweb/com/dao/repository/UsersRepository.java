package llcweb.com.dao.repository;


import llcweb.com.domain.models.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by:Ricardo
 * Description: 用户类的repository类
 * Date: 2018/8/21
 * Time: 13:36
 */
 //Integer 是id 的类型
public interface UsersRepository extends JpaRepository<Users,Integer>{

    /**
     *分页+动态查询
     **/
    Page<Users> findAll(Specification<Users> spec, Pageable pageable);

    /**
     *按用户名和用户密码查询
     */
    Users findByUsernameAndPassword(String userName, String password);
    Users findByUsername(String userName);

    /**
     * 模糊查询
     **/
    @Query("from Users j where j.username like %?1%")
    Page<Users> findByOneKey(String key,Pageable pageable);
}

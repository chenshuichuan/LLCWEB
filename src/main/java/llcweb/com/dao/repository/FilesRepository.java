package llcweb.com.dao.repository;


import llcweb.com.domain.models.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by:Haien
 * Description: 文档类的repository类
 * Date: 2018/8/22
 */
public interface FilesRepository extends JpaRepository<Files,Integer> {


}

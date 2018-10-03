package llcweb.com.service;

import llcweb.com.domain.models.People;
import org.springframework.data.domain.Page;

public interface PeopleService {

    /**
     * @Author haien
     * @Description 按照姓名、年级和职位动态查询
     * @Date 2018/10/2
     * @Param [spec, pageable]
     * @return org.springframework.data.domain.Page<llcweb.com.domain.models.People>
     **/
    Page<People> activeSearch(People people, int pageNum, int pageSize);

    /**
     * @Author haien
     * @Description 保存人员
     * @Date 2018/10/2
     * @Param
     * @return
     **/

    /**
     * @Author haien
     * @Description 删除人员
     * @Date 2018/10/2
     * @Param
     * @return
     **/
}

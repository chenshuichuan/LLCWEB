package llcweb.com.dao.repository;

import llcweb.com.domain.models.Image;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.notNullValue;

/**
 * @Author haien
 * @Description TODO
 * @Date 16:23 2018/8/22
 * @Param
 * @return
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageRepositoryTest {
    @Autowired
    private ImageRepository imageRepository;

    private Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2018-08-20");
    private Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2018-08-25");
    private String people = "haien1";
    private String title = "第8次";
    private String description = "项目组";

    public ImageRepositoryTest() throws ParseException {
    }


    @Test
    public void add() {
        Image image = new Image();
        for (int i = 1; i < 10; i++) {
            image.setId(i);
            image.setOwner("haien" + i);
            image.setDescription("项目组第" + i + "次会议");
            image.setDate(new Date());
            Assert.assertThat(imageRepository.save(image), notNullValue());
        }
    }

    @Test
    public void findAll() {
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        if (date1 != null) {
            orders.add(new Sort.Order(Sort.Direction.DESC, "date"));
        } else if (date2 != null) {
            orders.add(new Sort.Order(Sort.Direction.DESC, "date"));
        } else {
            orders.add(new Sort.Order(Sort.Direction.ASC, "id"));
        }
        Sort sort = new Sort(orders);
        Pageable pageable = new PageRequest(0, 2, sort);

        Page<Image> imageList = imageRepository.findAll(new Specification<Image>() {
            @Override
            public Predicate toPredicate(Root<Image> root, CriteriaQuery<?> query, CriteriaBuilder cd) {
                Predicate predicate = cd.conjunction();
                if (description != null) {
                    predicate.getExpressions().add(cd.like(root.get("description"), "%" + description + "%"));
                }
                if (date1 != null) {
                    predicate.getExpressions().add(cd.greaterThanOrEqualTo(root.get("date"), date1));
                }
                if (date2 != null) {
                    predicate.getExpressions().add(cd.lessThanOrEqualTo(root.get("date"), date2));
                }
                return predicate;
            }
        }, pageable);
        for (Image image : imageList) {
            System.out.println(image);
        }
    }

}

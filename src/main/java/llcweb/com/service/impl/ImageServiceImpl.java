package llcweb.com.service.impl;

import llcweb.com.dao.repository.ImageRepository;
import llcweb.com.domain.entity.UsefulImage;
import llcweb.com.domain.models.Image;
import llcweb.com.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@ConfigurationProperties(prefix="image")
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Value("${image.location}")
    private String path;

    @Override
    public Page<Image> findAll(UsefulImage image, int pageNum,int pageSize) {

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        //按时间排序
        orders.add(new Sort.Order(Sort.Direction.DESC, "date"));
        Sort sort = new Sort(orders);
        Pageable pageable=new PageRequest(pageNum,pageSize,sort);

        Page<Image> imageList = imageRepository.findAll(new Specification<Image>() {
            @Override
            public Predicate toPredicate(Root<Image> root, CriteriaQuery<?> query, CriteriaBuilder cd) {
                Predicate predicate = cd.conjunction();
                if (image.getDescription() != null) {
                    predicate.getExpressions().add(cd.like(root.get("description"), "%" + image.getDescription() + "%"));
                }
                if (image.getFirstDate() != null) {
                    predicate.getExpressions().add(cd.greaterThanOrEqualTo(root.get("date"), image.getFirstDate()));
                }
                if (image.getLastDate() != null) {
                    predicate.getExpressions().add(cd.lessThanOrEqualTo(root.get("date"), image.getLastDate()));
                }
                if (image.getOwner() != null) {
                    predicate.getExpressions().add(cd.like(root.get("description"), "%" + image.getOwner() + "%"));
                }
                if (image.getModel() != null) {
                    predicate.getExpressions().add(cd.like(root.get("model"), "%" + image.getModel() + "%"));
                }
                return predicate;
            }
        }, pageable);

        return imageList;
    }

    /**
     * 添加image
     */
    @Override
    public Map<String,Object> add(Image image) {
        Map<String,Object> map=new HashMap<>();

        if(imageRepository.findOne(image.getId())==null){
            if(imageRepository.save(image)!=null){
                map.put("result",1);
                map.put("msg","图片添加成功！");
                return map;
            }
        }
        map.put("result",0);
        map.put("msg","添加失败，请确认图片是否已存在！");
        return map;
    }

    /**
     * 更新image
     */
    @Override
    public Map<String,Object> update(Image image) {

        Map<String,Object> map=new HashMap<>();

        if(imageRepository.findOne(image.getId())!=null){
            if(imageRepository.save(image)!=null){
                map.put("result",1);
                map.put("msg","图片修改成功！");
                return map;
            }
        }
        map.put("result",0);
        map.put("msg","更新失败，请确认图片是否存在！");
        return map;
    }

    /**
     * 删除image
     */
    @Override
    public Map<String,Object> delete(Image image) {

        Map<String,Object> map=new HashMap<>();

        if(imageRepository.findOne(image.getId())!=null){
            if(imageRepository.save(image)!=null){
                map.put("result",1);
                map.put("msg","图片已删除！");
                return map;
            }
        }
        map.put("result",0);
        map.put("msg","删除失败，请确认图片是否存在！");
        return map;
    }

    /**
     * @Author haien
     * @Description 保存图片到项目
     * @Date 2018/9/6
     * @Param [file]
     * @return java.lang.String
     **/
    @Override
    public String saveImg(MultipartFile file) {


        return null;
    }
}

package llcweb.com.service.impl;

import llcweb.com.dao.repository.ImageRepository;
import llcweb.com.domain.UsefulImage;
import llcweb.com.domain.models.Image;
import llcweb.com.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Page<Image> findAll(UsefulImage image, int pageNum,int pageSize) {

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        if (image.getFirstDate() != null) {
            orders.add(new Sort.Order(Sort.Direction.DESC, "date"));
        } else if (image.getLastDate() != null) {
            orders.add(new Sort.Order(Sort.Direction.DESC, "date"));
        } else {
            orders.add(new Sort.Order(Sort.Direction.ASC, "id"));
        }
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
                return predicate;
            }
        }, pageable);

        return imageList;
    }
}

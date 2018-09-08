package llcweb.com.service.impl;

import llcweb.com.dao.repository.ImageRepository;
import llcweb.com.domain.entity.BusinessException;
import llcweb.com.domain.entity.ReturnCode;
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
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
    public int add(Image image) throws BusinessException {
        Image image1=null;
        if(imageRepository.findOne(image.getId())==null){
            image1=imageRepository.save(image);
            if(image1==null){
                throw new BusinessException(ReturnCode.CODE_FAIL,"图片已存在！");
            }
        }
        return image1.getId();
    }

    /**
     * 更新image
     */
    @Override
    public void update(Image image) throws BusinessException {

        if(imageRepository.findOne(image.getId())!=null){
            if(imageRepository.save(image)!=null){
                return;
            }
        }
        throw new BusinessException(ReturnCode.CODE_FAIL,"图片不存在！");
    }

    /**
     * 删除image
     */
    @Override
    public void delete(Image image) throws BusinessException {

        if(imageRepository.findOne(image.getId())!=null){
            if(imageRepository.save(image)!=null){
                return;
            }
        }
        throw new BusinessException(ReturnCode.CODE_FAIL,"图片不存在！");
    }

    /**
     * @Author haien
     * @Description 保存图片到项目
     * @Date 2018/9/6
     * @Param [file]
     * @return java.lang.String
     **/
    @Override
    public String saveImg(MultipartFile file,Image image) throws BusinessException {
        //拼接文件名
        String originalFileName=file.getOriginalFilename();
        String suffix=originalFileName.substring(originalFileName.lastIndexOf(".")+1);
        //加上id防止重名，如果觉得太简单可以用originalFileName代替suffix
        String fileName=image.getId()+image.getModel()+image.getOwnerId()+"."+suffix;

        try {
            /**
             * 直接获取MultipartFile的字节数组，这样就省了先获取InputStream再用一个1024的字节数组去read出来的麻烦
             * 重点是FileInputStream fis=(FileInputStream)file.getInputStream()抛异常：
             * java.lang.ClassCastException: java.io.ByteArrayInputStream cannot be cast to java.io.FileInputStream
             * 因为getInputStream()有两种返回，ByteArrayInputStream和FileInputStream(实际上返回值是InputStream)，视情况而定
            */

            byte[] flush=file.getBytes();
            BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(path+ File.separator+fileName));
            //已转为字节数组，可一次性写出
            bos.write(flush);
            bos.flush();
            bos.close();

            /**
             * 直接用InputStream接收
             * 好像所有文件返回的都是ByteArrayInputStream，用它接收也行
             */
            /*
            InputStream fileInputStream = file.getInputStream();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
            byte[] bs = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bs)) != -1) {
                bos.write(bs, 0, len);
            }
            bos.flush();
            bos.close();
            */

        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException(ReturnCode.CODE_FAIL, "格式错误！");
        }
        return fileName;
    }
}

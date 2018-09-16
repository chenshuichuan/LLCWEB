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
import javax.servlet.http.HttpServletResponse;
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
    public Page<Image> activeSearch(UsefulImage image, int pageNum,int pageSize) {

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
                    predicate.getExpressions().add(cd.like(root.get("author"), "%" + image.getOwner() + "%"));
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
        String fileName=image.getId()+image.getModel()+image.getAuthorId()+"."+suffix;

        try {
            BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(path+ File.separator+fileName));
            //可以直接获取MultipartFile的字节数组，这样就省了先获取InputStream再用一个1024的字节数组去read出来的麻烦
            //重点是FileInputStream fis=(FileInputStream)file.getInputStream()抛异常：
            //java.lang.ClassCastException: java.io.ByteArrayInputStream cannot be cast to java.io.FileInputStream
            byte[] flush=file.getBytes();
            //已转为字节数组，可一次性写出
            bos.write(flush);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException(ReturnCode.CODE_FAIL, "格式错误！");
        }
        return path+fileName;
    }

    @Override
    public void getOutputStream(Image image, HttpServletResponse response) throws IOException {
        String fileName=image.getOriginalName();
        byte[] buff=new byte[1024];
        BufferedInputStream bis=new BufferedInputStream(new FileInputStream(image.getPath()));
        OutputStream os=response.getOutputStream(); //服务器向浏览器发送字节输出流
        int len=0;
        while ((len=bis.read(buff))!=-1){
            os.write(buff,0,len);
        }
        os.flush();
        bis.close();
    }

}

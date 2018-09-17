package llcweb.com.service.impl;

import llcweb.com.dao.repository.FileRepository;
import llcweb.com.domain.entity.UsefulFile;
import llcweb.com.domain.models.File;
import llcweb.com.exception.BusinessException;
import llcweb.com.exception.ReturnCode;
import llcweb.com.service.FileService;
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
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Value("${image.location}")
    private String path; //文件保存路径

    @Override
    public Page<File> activeSearch(UsefulFile file, int pageNum, int pageSize) {

        //按时间排序
        Pageable pageable=new PageRequest(pageNum,pageSize, Sort.Direction.DESC,"date");

        Page<File> fileList = fileRepository.findAll(new Specification<File>() {
            @Override
            public Predicate toPredicate(Root<File> root, CriteriaQuery<?> query, CriteriaBuilder cd) {
                Predicate predicate = cd.conjunction();
                if (file.getOwner() != null) {
                    predicate.getExpressions().add(cd.like(root.get("author"), "%" + file.getOwner() + "%"));
                }
                if (file.getFirstDate() != null) {
                    predicate.getExpressions().add(cd.greaterThanOrEqualTo(root.get("date"), file.getFirstDate()));
                }
                if (file.getLastDate() != null) {
                    predicate.getExpressions().add(cd.lessThanOrEqualTo(root.get("date"), file.getLastDate()));
                }
                if (file.getIntroduction() != null) {
                    predicate.getExpressions().add(cd.like(root.get("introduction"), "%" + file.getIntroduction() + "%"));
                }
                if (file.getModel() != null) {
                    predicate.getExpressions().add(cd.like(root.get("model"), "%" + file.getModel() + "%"));
                }
                return predicate;
            }
        }, pageable);

        return fileList;
    }

    /**
     * 更新file
     */
    @Override
    public void update(File file) throws BusinessException {

        if(fileRepository.findOne(file.getId())!=null){
            if(fileRepository.save(file)!=null){
                return;
            }
        }
        throw new BusinessException(ReturnCode.CODE_FAIL,"文件不存在！");
    }

    /**
     * 删除file
     */
    @Override
    public void delete(File file) throws BusinessException {

        if(fileRepository.findOne(file.getId())!=null){
            if(fileRepository.save(file)!=null){
                return;
            }
        }
        throw new BusinessException(ReturnCode.CODE_FAIL,"文件不存在！");
    }

    /**
     * @Author haien
     * @Description 保存文件
     * @Date 2018/9/8
     * @Param [multipartFile, file]
     * @return java.lang.String
     **/
    @Override
    public String saveFile(MultipartFile multipartFile, File file) throws BusinessException {
        //拼接文件名
        String originalFileName=multipartFile.getOriginalFilename();
        String suffix=originalFileName.substring(originalFileName.lastIndexOf(".")+1);
        //加上id防止重名，如果觉得太简单可以用originalFileName代替suffix
        String fileName=file.getId()+file.getModel()+file.getAuthor()+"."+suffix;

        try {
            byte[] flush=multipartFile.getBytes();
            BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(path+ java.io.File.separator +fileName));
            //已转为字节数组，可一次性写出
            bos.write(flush);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException(ReturnCode.CODE_FAIL, "格式错误！");
        }
        return fileName;
    }

}

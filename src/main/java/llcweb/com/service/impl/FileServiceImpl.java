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
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Value("${file.location}")
    private String path; //文件保存路径

    /**
     * @Author haien
     * @Description 高级查询
     * @Date 2018/9/17
     * @Param [file, pageNum, pageSize]
     * @return org.springframework.data.domain.Page<llcweb.com.domain.models.File>
     **/
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

        String filePath=path+ java.io.File.separator +fileName;
        try {
            byte[] flush=multipartFile.getBytes();
            BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(filePath)); //必须指定java.io.File，否则会与导入包中的实体类File混淆
            //已转为字节数组，可一次性写出
            bos.write(flush);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException(ReturnCode.CODE_FAIL, "格式错误！");
        }
        return filePath;
    }

    /**
     * @Author haien
     * @Description 客户端获取文件输出流
     * @Date 2018/9/17
     * @Param [image, response]
     * @return void
     **/
    @Override
    public void getOutputStream(File file, HttpServletResponse response) throws IOException {
        String fileName = file.getOriginalName();
        byte[] buff = new byte[1024];
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file.getPath()));
        OutputStream os = response.getOutputStream(); //服务器向浏览器发送字节输出流
        int len = 0;
        while ((len = bis.read(buff)) != -1) {
            os.write(buff, 0, len);
        }
        os.flush();
        bis.close();
    }

    /**
     * @Author haien
     * @Description 删除项目中指定文件
     * @Date 2018/9/17
     * @Param [path]
     * @return void
     **/
    @Override
    public void deleteFile(String path) throws FileNotFoundException, BusinessException {
        java.io.File file=new java.io.File(path); //指定类型为java.io.File，避免与实体类File混淆
        //路径为文件且不为空则删除
        if(file.isFile()&&file.exists()){
            file.delete();
        }else if(!file.isFile()){
            System.out.println(path);
            throw new BusinessException(ReturnCode.CODE_FAIL,"路径非文件");
        }else{
            throw new FileNotFoundException();
        }
    }
}

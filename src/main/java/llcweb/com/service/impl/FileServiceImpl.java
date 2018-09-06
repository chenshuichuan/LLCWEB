package llcweb.com.service.impl;

import llcweb.com.dao.repository.FileRepository;
import llcweb.com.domain.entity.UsefulFile;
import llcweb.com.domain.models.File;
import llcweb.com.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public Page<File> findAll(UsefulFile file, int pageNum, int pageSize) {

        //按时间排序
        Pageable pageable=new PageRequest(pageNum,pageSize, Sort.Direction.DESC,"date");

        Page<File> fileList = fileRepository.findAll(new Specification<File>() {
            @Override
            public Predicate toPredicate(Root<File> root, CriteriaQuery<?> query, CriteriaBuilder cd) {
                Predicate predicate = cd.conjunction();
                if (file.getOwner() != null) {
                    predicate.getExpressions().add(cd.like(root.get("owner"), "%" + file.getOwner() + "%"));
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
     * 添加file
     */
    @Override
    public Map<String,Object> add(File file) {
        Map<String,Object> map=new HashMap<>();

        if(fileRepository.findOne(file.getId())==null){
            if(fileRepository.save(file)!=null){
                map.put("result",1);
                map.put("msg","文件添加成功！");
                return map;
            }
        }
        map.put("result",0);
        map.put("msg","添加失败，请确认文件是否已存在！");
        return map;
    }

    @Override
    public Map<String, Object> delete(int id) {
        return null;
    }

    /**
     * 更新file
     */
    //@Override
    public Map<String,Object> update(File file) {

        Map<String,Object> map=new HashMap<>();

        if(fileRepository.findOne(file.getId())!=null){
            if(fileRepository.save(file)!=null){
                map.put("result",1);
                map.put("msg","文件修改成功！");
                return map;
            }
        }
        map.put("result",0);
        map.put("msg","更新失败，请确认文件是否存在！");
        return map;
    }

    /**
     * 删除file
     */
    @Override
    public Map<String,Object> delete(int id) {

        Map<String,Object> map=new HashMap<>();

        if(fileRepository.findOne(id)!=null){
            fileRepository.delete(id);
            map.put("result",1);
            map.put("msg","文件已删除！");
            return map;
        }

        map.put("result",0);
        map.put("msg","删除失败，请确认文件是否存在！");
        return map;
    }
}

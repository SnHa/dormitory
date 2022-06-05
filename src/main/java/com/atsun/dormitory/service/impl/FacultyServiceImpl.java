package com.atsun.dormitory.service.impl;

import com.atsun.dormitory.convert.Impl.FacultyConvent;
import com.atsun.dormitory.dto.FacultyDTO;
import com.atsun.dormitory.enums.TransCode;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.service.StudentService;
import com.atsun.dormitory.utils.SnowFlake;
import com.atsun.dormitory.vo.FacultyVO;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.atsun.dormitory.po.Faculty;
import com.atsun.dormitory.dao.FacultyMapper;
import com.atsun.dormitory.service.FacultyService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
@Log4j2
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Service
public class FacultyServiceImpl implements FacultyService {

    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Resource
    private FacultyMapper facultyMapper;

    @Override
    public int deleteByPrimaryKey(String id) throws TransException {
        return facultyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Faculty record) throws TransException {
        return facultyMapper.insert(record);
    }

    @Override
    public int insertSelective(Faculty record) throws TransException {
        return facultyMapper.insertSelective(record);
    }

    @Override
    public Faculty selectByPrimaryKey(String id) throws TransException {
        return facultyMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Faculty record) throws TransException {
        return facultyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Faculty record) throws TransException {
        return facultyMapper.updateByPrimaryKey(record);
    }


    @Override
    public List<FacultyVO> list() throws TransException {
        // todo
        List<FacultyVO> facultyVOS = this.buildTree(facultyMapper.list(null));
        this.setStudentNum(facultyVOS);
        return facultyVOS;
    }

    @Override
    public FacultyVO query(String facultyId) throws TransException {
        return facultyMapper.query(facultyId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(FacultyDTO facultyDTO) throws TransException {
        // todo 判断id是否存在
        Faculty faculty = FacultyConvent.INSTANCE.dtoToPo(facultyDTO);
        if (StringUtils.isBlank(faculty.getId())) {
            faculty.setId(String.valueOf(SnowFlake.nextId()));
            facultyMapper.insert(faculty);
        } else {
            facultyMapper.updateByPrimaryKeySelective(faculty);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String facultyId) throws TransException {
        // todo 判断id下是否存在父id
        List<String> strings = new ArrayList<>();
        strings.add(facultyId);
        if (studentService.countByFacultyId(strings)>0){
            throw new TransException(TransCode.CUSTOM_EXCEPTION_MSG,"该节点或子节点下还有学生，无法删除");
        }
        // todo 判断id下是否存在学生
        facultyMapper.deleteByPrimaryKey(facultyId);
    }

    @Override
    public List<FacultyVO> listAll() throws TransException {
        return facultyMapper.listAll();
    }

    @Override
    public Object FaculyByNumber() throws TransException {
        //TODO 查询出宜宾学院的所有子id
        List<FacultyVO> list = facultyMapper.list("8190569817705660416");
        this.setStudentNum(list);
        Map<String ,List<String>>  map= new HashMap<>();
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> number = new ArrayList<>();
        list.forEach(facultyVO -> {
           nameList.add(facultyVO.getName());
           number.add(String.valueOf(facultyVO.getStudentNum()));
        });
        map.put("nameList",nameList);
        map.put("number",number);
        return map;
    }

    @Override
    public List<String> getIdsByParentId(String facultyId) throws TransException {

        List<String> res = new ArrayList<>(16);
        res.add(facultyId);
        this.getIdsByParentId(facultyId, res);
        return res;
    }

    private void getIdsByParentId(String facultyId, List<String> result) throws TransException {
        List<String> childrenId = facultyMapper.listByParentId(facultyId);
        result.addAll(childrenId);
        childrenId.forEach(l -> {
            try {
                getIdsByParentId(l, result);
            } catch (TransException e) {
                e.printStackTrace();
            }
        });
    }

    // todo 统计学院，班级数量
    private void setStudentNum(List<FacultyVO> list) {
        list.forEach(facultyVO -> {
            try {
                List<String> facultyIds = this.getIdsByParentId(facultyVO.getId());
                // todo 查询当前学院/班级id查询学生总数
                facultyVO.setStudentNum(studentService.countByFacultyId(facultyIds));
                // TODO 判断不为空在进行查询 不然会出现null指针报错
                if (facultyVO.getChildren() != null) {
                    setStudentNum(facultyVO.getChildren());
                }
            } catch (TransException e) {
                e.printStackTrace();
            }
        });
    }

    // TODO 给菜单构建树形结构

    public List<FacultyVO> buildTree(List<FacultyVO> treeNodes) {
        //遍历list
        for (FacultyVO treeNode : treeNodes) {
            //如果元素不是顶级目录
            if (treeNode.getPId() != null && !"0".equals(treeNode.getPId())) {
                //再次遍历List
                for (FacultyVO node : treeNodes) {
                    //在整个list中查找元素的父级
                    if (treeNode.getPId().equals(node.getId())) {
                        //将元素放入父级中
                        if (node.getChildren() == null) {
                            node.setChildren(new ArrayList<>());
                        }
                        node.getChildren().add(treeNode);
                    }
                }
            }
        }
        //删除所有不为顶级目录的元素
        treeNodes.removeIf(treeNode -> treeNode.getPId() != null && !"0".equals(treeNode.getPId()));
        System.out.println("树结构的长度为：" + treeNodes.size());
        return treeNodes;
    }

}

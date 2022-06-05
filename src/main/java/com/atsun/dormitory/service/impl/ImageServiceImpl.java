package com.atsun.dormitory.service.impl;

import com.atsun.dormitory.exception.TransException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.atsun.dormitory.po.Image;
import com.atsun.dormitory.dao.ImageMapper;
import com.atsun.dormitory.service.ImageService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/2/14 21:13
 */
@Log4j2
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Service
public class ImageServiceImpl implements ImageService {

    @Resource
    private ImageMapper imageMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(String id) throws TransException {
        return imageMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(Image record) throws TransException {
        return imageMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(Image record) throws TransException {
        return imageMapper.insertSelective(record);
    }

    @Override
    public Image selectByPrimaryKey(String id) throws TransException {
        return imageMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(Image record) throws TransException {
        return imageMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(Image record) throws TransException {
        return imageMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Image> selectByMd5(String md5) throws TransException {
        return imageMapper.selectByMd5(md5);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBySaveName(List<String> strings) throws TransException {
        //  TODO 删除文件

        imageMapper.deleteBySaveName(strings);
    }

}

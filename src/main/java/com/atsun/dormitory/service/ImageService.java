package com.atsun.dormitory.service;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Image;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/2/14 21:13
 */
public interface ImageService {


    int deleteByPrimaryKey(String id) throws TransException;

    int insert(Image record) throws TransException;

    int insertSelective(Image record) throws TransException;

    Image selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(Image record) throws TransException;

    int updateByPrimaryKey(Image record) throws TransException;

    /**
     * 按md5查询上传图片
     *
     * @param md5 加密
     * @return list
     * @throws TransException 异常
     */
    List<Image> selectByMd5(String md5) throws TransException;

    /**
     * 根据上传名字删除图片
     * @param strings  上传名字s
     * @throws TransException 异常
     */
    void deleteBySaveName(List<String> strings) throws TransException;
}

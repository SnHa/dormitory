package com.atsun.dormitory.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/** 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @Author SH
 * @Date 2022/2/14 21:13 
 */
@Data
public class Image implements Serializable {
    /**
    * id
    */
    private String id;

    /**
    * 上传名称
    */
    private String saveName;

    /**
    * 原始名称
    */
    private String originalName;

    /**
    * md5
    */
    private String md5;

    /**
    * 上传用户
    */
    private String uploadUser;

    /**
    * 创建时间
    */
    private Date uploadTime;

    private static final long serialVersionUID = 1L;
}
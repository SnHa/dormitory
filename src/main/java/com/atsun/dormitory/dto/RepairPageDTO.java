package com.atsun.dormitory.dto;

import com.atsun.dormitory.po.Room;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @Description: TODO(维修表单)
 * @Author SH
 * @Date 2022/2/13 16:52
 */
@Data
public class RepairPageDTO {


    /**
     * id
     */
    private String id;

    /**
     * 房间号
     */
    private String roomId;

    /**
     * 问题
     */
    private String describe;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 创建时间
     */
/*    *//*@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")*//*
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")*/
    private String createDate;

    /**
     * 完成时间
     */
    private Date finishDate;

    /**
     * 图片
     */
    private String img;

    /**
     * 房间号
     */

    private Room room;
    /**
     * 上传图片集合
     */
    private List<String> picture;

    /**
     * 当前页
     */
    private Integer page;
    /**
     * 每页最大记录数
     */
    private Integer rows;
}

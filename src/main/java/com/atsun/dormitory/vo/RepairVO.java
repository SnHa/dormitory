package com.atsun.dormitory.vo;

import com.atsun.dormitory.po.Room;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description: TODO(维修)
 * @Author SH
 * @Date 2022/2/13 17:01
 */
@Data
public class RepairVO {
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
    private Date createDate;

    /**
     * 完成时间
     */
    private Date finishDate;

    /**
     * 图片
     */
    private  String img;

    private List<String> picture;

    private Room room;
}

package com.atsun.dormitory.convert.Impl;

import com.atsun.dormitory.dto.NoticeDTO;
import com.atsun.dormitory.po.Notice;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/2/10 9:14
 */
@Mapper
public interface NoticeConvent {

    NoticeConvent INSTANCE = Mappers.getMapper(NoticeConvent.class);

    /**
     * dto->po
     *
     * @param noticeDTO dto
     * @return 通知
     */
    Notice dtoToPo(NoticeDTO noticeDTO);

}

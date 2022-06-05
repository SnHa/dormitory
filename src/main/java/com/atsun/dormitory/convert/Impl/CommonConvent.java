package com.atsun.dormitory.convert.Impl;

import com.atsun.dormitory.po.User;
import com.atsun.dormitory.vo.CommonVO;
import com.atsun.dormitory.vo.PermissionVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-08 12:25
 **/
@Mapper
public interface CommonConvent {

    CommonConvent INSTANCE = Mappers.getMapper(CommonConvent.class);

    /**userAndMenuPermissionToCommonVO
     * @param user
     * @param menu
     * @param permiss
     * @return
     */
    @Mappings(
            value = {
                    @Mapping(source = "menu", target = "menu"),
                    @Mapping(source = "permiss", target = "permissions")
            }
    )
    CommonVO userAndMenuPermissionToCommonVO(User user, List<PermissionVO> menu, List<String> permiss);
}

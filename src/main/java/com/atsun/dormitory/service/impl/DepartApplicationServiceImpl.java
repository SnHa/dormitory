package com.atsun.dormitory.service.impl;

import com.atsun.dormitory.dto.DepartApplicationPageDTO;
import com.atsun.dormitory.enums.TransCode;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.DepartApplicationUser;
import com.atsun.dormitory.service.DepartApplicationUserService;
import com.atsun.dormitory.utils.JwtUtil;
import com.atsun.dormitory.vo.DepartApplicationVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.atsun.dormitory.dao.DepartApplicationMapper;
import com.atsun.dormitory.po.DepartApplication;
import com.atsun.dormitory.service.DepartApplicationService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:39
 **/
@Log4j2
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Service
public class DepartApplicationServiceImpl implements DepartApplicationService {

    private DepartApplicationUserService departApplicationUserService;

    @Autowired
    public void setDepartApplicationUserService(DepartApplicationUserService departApplicationUserService) {
        this.departApplicationUserService = departApplicationUserService;
    }

    @Resource
    private DepartApplicationMapper departApplicationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(String id) {
        return departApplicationMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(DepartApplication record) {
        return departApplicationMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(DepartApplication record) {
        return departApplicationMapper.insertSelective(record);
    }

    @Override
    public DepartApplication selectByPrimaryKey(String id) {
        return departApplicationMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(DepartApplication record) {
        return departApplicationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(DepartApplication record) {
        return departApplicationMapper.updateByPrimaryKey(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveApplication(DepartApplication departApplication) throws TransException {
        departApplicationMapper.saveApplication(departApplication);
    }

    @Override
    public DepartApplication getByDepartApplicationUserId(String departApplicationUserId) throws TransException {
        return departApplicationMapper.getByDepartApplicationUserId(departApplicationUserId);
    }

    @Override
    public PageInfo<DepartApplicationVO> listMy(DepartApplicationPageDTO departApplicationPageDTO, String token) throws TransException {

        PageHelper.startPage(departApplicationPageDTO.getPage(), departApplicationPageDTO.getRows());
        // TODO 查询自己发送审核
        List<DepartApplicationVO> list = departApplicationMapper.listMyApplication(JwtUtil.decode(token));
        list.forEach(departApplication -> {
            String id = departApplication.getId();
            try {
                // TODO 根据is_agree 属性的数量判断是否结束
                departApplication.setIsFinished(departApplicationUserService.countFlowAgreeIsNotNull(id) == 0);

                DepartApplicationUser applicationUser = departApplicationUserService.getLastFlow(id);
                if (applicationUser == null) {
                    // TODO 暂时不使用 后续对学生表添加一个离校字段，离校不对数据直接进行删除，使用
                    departApplication.setIsPass(true);
                } else {
                    departApplication.setIsPass(applicationUser.getIsAgree());
                }
            } catch (TransException e) {
                e.printStackTrace();
            }
        });
        return new PageInfo<>(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String departApplicationId) throws TransException {
        // TODO 判断departuser中审Agree数不是空的数量
        if (departApplicationUserService.countFlowAgreeIsNotNull(departApplicationId)==0){
            throw new TransException(TransCode.CUSTOM_EXCEPTION_MSG,"审核已完成，无法撤销");
        }else if (departApplicationUserService.countFlowAgreeIsNull(departApplicationId) > 0){
            throw new TransException(TransCode.CUSTOM_EXCEPTION_MSG,"审核已开始，无法撤销");
        }else {
            // TODO 判断departuser中审Agree全为空，还没有进行审核
            departApplicationUserService.deleteFlow(departApplicationId);
            departApplicationMapper.deleteApplication(departApplicationId);
        }
    }

}

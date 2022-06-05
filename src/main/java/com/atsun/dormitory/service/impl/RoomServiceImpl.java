package com.atsun.dormitory.service.impl;

import com.atsun.dormitory.convert.Impl.RoomConvent;
import com.atsun.dormitory.dao.StudentMapper;
import com.atsun.dormitory.dao.UserMapper;
import com.atsun.dormitory.dto.RoomDTO;
import com.atsun.dormitory.dto.RoomPageDTO;
import com.atsun.dormitory.enums.TransCode;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.service.BuildingService;
import com.atsun.dormitory.service.StudentService;
import com.atsun.dormitory.utils.JwtUtil;
import com.atsun.dormitory.utils.SnowFlake;
import com.atsun.dormitory.vo.RoomVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.atsun.dormitory.po.Room;
import com.atsun.dormitory.dao.RoomMapper;
import com.atsun.dormitory.service.RoomService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
@Log4j2
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Service
public class RoomServiceImpl implements RoomService {

    private BuildingService buildingService;
    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired
    public void setBuildingService(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @Resource
    private RoomMapper roomMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private StudentMapper studentMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(String id) throws TransException {
        return roomMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(Room record) throws TransException {
        return roomMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(Room record) throws TransException {
        return roomMapper.insertSelective(record);
    }

    @Override
    public Room selectByPrimaryKey(String id) throws TransException {
        return roomMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(Room record) throws TransException {
        return roomMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(Room record) throws TransException {
        return roomMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<RoomVO> list(RoomPageDTO roomPageDTO, String token) throws TransException {

        String building = userMapper.findBuilding(JwtUtil.decode(token));

        List<String> byParentIds = buildingService.getIdsByParentId(building);

        List<String> selectBid = null;
        // TODO 宿舍楼不为空
        if (!StringUtils.isBlank(roomPageDTO.getBuildingId())) {
            selectBid = buildingService.getIdsByParentId(roomPageDTO.getBuildingId());
        }
        // TODO 先求出总的条数 判断是否是本页的嘴和一条数据
        int count = roomMapper.count(roomPageDTO.getIsFull(), roomPageDTO.getNumber(), byParentIds, selectBid);
        if ((int) Math.ceil((double) count / roomPageDTO.getRows()) < roomPageDTO.getPage()) {
            roomPageDTO.setPage(roomPageDTO.getPage() - 1);
        }
        PageHelper.startPage(roomPageDTO.getPage(), roomPageDTO.getRows());
        List<RoomVO> room = roomMapper.list(roomPageDTO.getIsFull(), roomPageDTO.getNumber(), byParentIds, selectBid);
        return new PageInfo<>(room);
    }

    @Override
    public RoomVO query(String roomId) throws TransException {
        return roomMapper.query(roomId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String roomId) throws TransException {

        // TODO 先查询该间宿舍有无学生
        if (studentService.countByRoomId(roomId) > 0) {
            throw new TransException(TransCode.CUSTOM_EXCEPTION_MSG, "该宿舍还存在学生");
        }

        roomMapper.deleteByPrimaryKey(roomId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(RoomDTO roomDTO) throws TransException {

        Room room = RoomConvent.INSTANCE.dtoToPo(roomDTO);
        if (StringUtils.isBlank(roomDTO.getId())) {

            room.setId(String.valueOf(SnowFlake.nextId()));
            room.setElectricity("0");
            roomMapper.insert(room);
        }else {
            // TODO 判断当前人数是否小于修改后的最大容量
            if (studentMapper.countByRoomId(roomDTO.getId()) >roomDTO.getMaxCapacity()){
                throw new TransException(TransCode.CUSTOM_EXCEPTION_MSG, "寝室人数大于容量");
            }
            // TODO  查询修改前电费
            room.setElectricity(roomMapper.selectByPrimaryKey(room.getId()).getElectricity());
            roomMapper.updateByPrimaryKey(room);
        }
    }

    @Override
    public List<RoomVO> listAll(String token) throws TransException {
        String userId = JwtUtil.decode(token);
        return roomMapper.list(null, null,
                buildingService.getIdsByParentId(userMapper.selectByPrimaryKey(userId).getBuildingId()), null);
    }

    @Override
    public int getMaxCapacity(String roomId) throws TransException {
        return roomMapper.getMaxCapacity(roomId);
    }

    @Override
    public int count(String buildingId) throws TransException {
        List<String> bids = buildingService.getIdsByParentId(buildingId);
        return roomMapper.roomCountByBuilding(bids);
    }

    @Override
    public Room selectByNumber(String number) throws TransException {
        return roomMapper.selectByNumber(number);
    }

}

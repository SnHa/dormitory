package com.atsun.dormitory.controller;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Room;
import com.atsun.dormitory.po.User;
import com.atsun.dormitory.service.BuildingService;
import com.atsun.dormitory.service.RoomService;
import com.atsun.dormitory.service.UserService;
import com.atsun.dormitory.vo.DataResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: SH
 * @create: 2021-12-08 13:08
 **/
@Api(tags = "首页")
@RequestMapping("/home")
@RestController
public class HomeController extends BaseController {

    private UserService userService;
    private RoomService roomService;
    private BuildingService buildingService;

    @Autowired
    public void setBuildingService(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/statistics")
    public DataResponse<?> statistics(@RequestHeader("_ut") String token) throws TransException {
        Map<String, Object> result = new HashMap<>(5);
        User user = userService.findUser(token);
        result.put("building", user.getBuildingId() == null ? "无" : buildingService.query(user.getBuildingId()).getName());
        int roomNum = roomService.count(user.getBuildingId());
        result.put("roomNum", roomNum);
        return ok(result);
    }
}

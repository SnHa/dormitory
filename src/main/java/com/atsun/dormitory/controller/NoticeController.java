package com.atsun.dormitory.controller;

import com.atsun.dormitory.dto.NoticeDTO;
import com.atsun.dormitory.dto.NoticePageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Notice;
import com.atsun.dormitory.service.NoticeService;
import com.atsun.dormitory.service.NoticeUserService;
import com.atsun.dormitory.vo.DataResponse;
import com.atsun.dormitory.vo.NoDataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: TODO(通知)
 * @Author SH
 * @Date 2022/2/9 21:11
 */
@Api(tags = "通知")
@RequestMapping("/notice")
@RestController
public class NoticeController extends BaseController {

    private NoticeService noticeService;
    private NoticeUserService noticeUserService;

    @Autowired
    public void setNoticeService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @Autowired
    public void setNoticeUserService(NoticeUserService noticeUserService) {
        this.noticeUserService = noticeUserService;
    }

    @ApiOperation(value = "发送消息给多个用户")
    @RequiresPermissions("home:send:msg")
    @PostMapping("/sendToBuilding/{id}")
    public NoDataResponse sendToBuilding(@RequestBody NoticeDTO noticeDTO,
                                         @PathVariable("id") String id,
                                         @RequestHeader("_ut") String token) throws TransException {
        noticeService.sendToBuilding(noticeDTO, id, token);
        return ok();
    }

    @ApiOperation(value = "自己发送的通知")
    @PostMapping("/listSend")
    public DataResponse<?> listSend(@RequestHeader("_ut") String token, @RequestBody NoticePageDTO noticePageDTO) throws TransException {
        return ok(noticeService.listSend(token, noticePageDTO));
    }

    @ApiOperation(value = "根据用户id 获取所有通知")
    @PostMapping("/list")
    public DataResponse<?> list(@RequestHeader("_ut") String token, @RequestBody NoticePageDTO noticePageDTO) throws TransException {
        return ok(noticeService.list(token, noticePageDTO));
    }

    @ApiOperation("/查看单条通知")
    @GetMapping("/query/{id}")
    public DataResponse<?> query(@PathVariable("id") String id, @RequestHeader("_ut") String token) throws TransException {
        return ok(noticeService.query(id, token));
    }

    @ApiOperation(value = "删除收到通知")
    @GetMapping("/deleteReceive/{id}")
    public NoDataResponse deleteReceive(@PathVariable("id") String id, @RequestHeader("_ut") String token) throws TransException {
        noticeUserService.deleteReceive(id, token);
        return ok();
    }

    @ApiOperation(value = "删除自己发送的消息")
    @GetMapping("/deleteSend/{id}")
    public NoDataResponse deleteSend(@PathVariable("id") String id, @RequestHeader("_ut") String token) throws TransException {
        noticeService.deleteSend(id);
        return ok();
    }

    @ApiOperation(value = "查询自己发送通知")
    @GetMapping("/querydetail/{id}")
    public DataResponse<?> querydetail(@PathVariable("id") String id) throws TransException{
        return ok(noticeService.queryWithReceiver(id));
    }


    @ApiOperation(value = "给用户发信息")
    @PostMapping("/sendToUser/{id}")
    public NoDataResponse sendToUser(@PathVariable("id") String id,@RequestHeader("_ut") String token, @RequestBody NoticePageDTO noticePageDTO) throws TransException {
        noticeService.sendToUser(id,token, noticePageDTO);
        return ok();
    }
}

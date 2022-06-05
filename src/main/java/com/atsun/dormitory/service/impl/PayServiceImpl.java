package com.atsun.dormitory.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atsun.dormitory.convert.Impl.PayConvent;
import com.atsun.dormitory.dto.PayDTO;
import com.atsun.dormitory.dto.PayPageDTO;
import com.atsun.dormitory.enums.TransCode;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.play.AliPlay;
import com.atsun.dormitory.po.Room;
import com.atsun.dormitory.service.*;
import com.atsun.dormitory.utils.JwtUtil;
import com.atsun.dormitory.utils.SnowFlake;
import com.atsun.dormitory.vo.PayVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.atsun.dormitory.dao.PayMapper;
import com.atsun.dormitory.po.Pay;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/3/3 14:09
 */
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Log4j2
@Service
public class PayServiceImpl implements PayService {

    private UserService userService;
    private RoomService roomService;
    private RoleService roleService;
    private StudentService studentService;
    private UserRoleService userRoleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }


    @Resource
    private PayMapper payMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(String id) throws TransException {
        return payMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(Pay record) throws TransException {
        return payMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(Pay record) throws TransException {
        return payMapper.insertSelective(record);
    }

    @Override
    public Pay selectByPrimaryKey(String id) throws TransException {
        return payMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(Pay record) throws TransException {
        return payMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(Pay record) throws TransException {
        return payMapper.updateByPrimaryKey(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String notifyByPay(HttpServletRequest request) throws TransException {
        log.info("===================================支付宝异步通知接口");
        if (AliPlay.signVerified(request)) {
            log.info("===================================支付宝签名验证失败");
            return "fail";
        }
        // TODO 签名验证成功
        //商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        //支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        //交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        //交易金额
        String total_amount = new String(request.getParameter("total_amount").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        if (StringUtils.isBlank(out_trade_no)) {
            log.info("===================================商户订单号为空");
            return "fail";
        }
        if (StringUtils.isBlank(trade_no)) {
            log.info("===================================支付宝交易号为空");
            return "fail";
        }
        if (StringUtils.isBlank(trade_status)) {
            log.info("===================================交易状态为空");
            return "fail";
        }
        if (StringUtils.isBlank(total_amount)) {
            log.info("===================================交易金额为空");
            return "fail";
        }
        // TODO 表示参数都合法
        Pay pay = this.selectByPrimaryKey(out_trade_no);
        if (pay == null) {
            log.info("===================================订单编号不存在");
            return "fail";
        }
        if (pay.getPayAmount().equals(total_amount)) {
            log.info("===================================支付金额不同相等");
            return "fail";
        }
        Room room = roomService.selectByPrimaryKey(pay.getRoomId());
        room.setElectricity(pay.getPayAmount());
        roomService.updateByPrimaryKeySelective(room);
        pay.setStatus("支付成功");
        this.updateByPrimaryKeySelective(pay);
        return "success";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject pay(PayDTO payDTO) throws TransException {
        if (StringUtils.isBlank(payDTO.getBody()) || StringUtils.isBlank(payDTO.getNumber()) ||
                StringUtils.isBlank(payDTO.getPayAmount()) || StringUtils.isBlank(payDTO.getSubject())) {
            throw new TransException(TransCode.CUSTOM_EXCEPTION_MSG, "参数不能为空");
        }
        Room room = roomService.selectByNumber(payDTO.getNumber());
        String out_trade_no = String.valueOf(SnowFlake.nextId());
        JSONObject jsonObject = new JSONObject();
        //
        Pay pay = PayConvent.INSTANCE.dtoToPo(payDTO);
        pay.setId(out_trade_no);
        pay.setRoomId(room.getId());
        this.insert(pay);
        //
        String s = AliPlay.generatePcPlayHtml(out_trade_no, pay.getPayAmount(), pay.getSubject(), pay.getBody());
        log.info("====================电费缴纳=================url" + s);
        StringBuffer aftAction = new StringBuffer(s);
        aftAction.reverse();
        String midAction = aftAction.substring(68);
        aftAction = new StringBuffer(midAction).reverse();
        aftAction.append(" width: 200px;  padding:8px;  background-color: #428bca;  border-color: #357ebd; color: #fff;  -moz-border-radius: 10px;  -webkit-border-radius: 10px;  border-radius: 10px;  -khtml-border-radius: 10px;text-align: center;  vertical-align: middle;  border: 1px solid transparent;  font-weight: 900;  font-size:125% \"> </form>");
        jsonObject.put("formaction", aftAction);


        //内网失败临时处理

        Room room1 = roomService.selectByPrimaryKey(pay.getRoomId());
        int money=Integer.valueOf(room1.getElectricity())+Integer.valueOf(pay.getPayAmount());
        room1.setElectricity(String.valueOf(money));
        roomService.updateByPrimaryKeySelective(room1);
        pay.setStatus("支付成功");
        this.updateByPrimaryKeySelective(pay);


        return jsonObject;
    }

    @Override
    public PageInfo<PayVO> list(PayPageDTO payPageDTO, String token) throws TransException {

        PageHelper.startPage(payPageDTO.getPage(), payPageDTO.getRows());
        // TODO 判断角色
        String userId = JwtUtil.decode(token);
        List<String> roleIds = userRoleService.findRoleIds(userId);
        ArrayList<String> roleNames = new ArrayList<>(roleService.RoleNames(roleIds));
        boolean jieguo=false;
        for (int i=0;i<roleNames.size();i++){
            if (roleNames.get(i).equals("学生")){
                jieguo=true;
            }
        }
        if (jieguo) {
            // TODO 查询学生学号，查询宿舍id ，查询宿舍名称
            String Number = userService.selectByPrimaryKey(userId).getLoginName();
            String roomId = studentService.selectByNumber(Number);
            return new PageInfo<>(payMapper.query(roomId));
        }

        return new PageInfo<>(payMapper.list());
    }


}

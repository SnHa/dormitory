package com.atsun.dormitory.socket;

import com.atsun.dormitory.dto.Message;
import com.atsun.dormitory.dto.MessageDTO;
import com.atsun.dormitory.enums.TransCode;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.User;
import com.atsun.dormitory.service.UserService;
import com.atsun.dormitory.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author SH
 * @date 2022-01-02
 */
@Component
@ServerEndpoint("/ws/{name}") // TODO ServerEndpoint申明一个websocket服务
public class WebSocket {

    private Session session;

    private String name;

    public final static Map<String,WebSocket> WEB_SOCKET_SET = new ConcurrentHashMap<>();



    @OnOpen
    public void onOpen(Session session, @PathParam(value = "name") String name){
        System.out.println("======================"+"建立webpsocket连接成功"+"============================");
        this.session = session;
        this.name = name;
        WEB_SOCKET_SET.put(name,this);
    }

    @OnClose
    public void onClose(){
        WEB_SOCKET_SET.remove(name);
    }

    @OnMessage
    public void onMessage(String message) throws JsonProcessingException {
        MessageDTO m = new ObjectMapper().readValue(message, MessageDTO.class);
        System.out.println(m);
    }

    /**
     * 发送消息
     * @param userId 目标用户id
     * @param message 消息内容
     * @param  。
     */
    public static boolean sendMessage(String userId, Message message, UserService userService) throws TransException {
        UserVO systemUser =  userService.get(userId);
        if (WEB_SOCKET_SET.containsKey(systemUser.getLoginName())) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 发送消息给客户端 通过session进行消息发送；
                WEB_SOCKET_SET.get(systemUser.getLoginName()).session.getBasicRemote()
                        .sendText(objectMapper.writeValueAsString(message));
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

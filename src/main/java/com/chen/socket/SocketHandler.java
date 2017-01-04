package com.chen.socket;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @desp Socket处理类
 * @author liulichao@ruc.edu.cn
 *
 */
@Service
public class SocketHandler implements WebSocketHandler {

//    private static final Logger logger;
    private static final ArrayList<WebSocketSession> users;
    public static int count = 0;
    static{
        users = new ArrayList<WebSocketSession>();
//        logger = LoggerFactory.getLogger(SocketHandler.class);
    }

    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
        System.out.println("成功建立socket连接");
        users.add(session);
        String username = session.getAttributes().get("user").toString();
        if(username!=null){
            session.sendMessage(new TextMessage("我们已经成功建立soket通信了"));
        }

    }

    public void handleMessage(WebSocketSession session, WebSocketMessage<?> arg1)
            throws Exception {
        // TODO Auto-generated method stub
    }

    public void handleTransportError(WebSocketSession session, Throwable error)
            throws Exception {
        if(session.isOpen()){
            session.close();
        }
        System.out.println("连接出现错误:"+error.toString());
        users.remove(session);
    }

    public void afterConnectionClosed(WebSocketSession session, CloseStatus arg1)
            throws Exception {
        System.out.println("连接已关闭");
        users.remove(session);
    }

    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get("user").equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

}

package com.atdyl.websocket.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Dong YL
 * @version V1.0 2020/1/2 16:11
 */
@Service
@ServerEndpoint("/ws")
@Slf4j
public class WebSocketServiceImpl {

    //静态变量，用来记录当前在线链接数
    private static int onlineCount = 0;

    //concurrent包的线程安全set，用来存放每个客户端对应的WebSocketServiceImpl对象
    private static CopyOnWriteArraySet<WebSocketServiceImpl> webSocketSet = new CopyOnWriteArraySet<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     *
     * @param session -
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        log.info("有新连接加入！当前在线人数为：{}", getOnlineCount());
        try {
            sendMessage("有新的连接加入了");
        } catch (IOException e) {
            log.error("IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        subOnlineCount();
        log.info("有一个连接关闭！当前在线人数：{}", getOnlineCount());
    }

    /**
     * 收到客户端消息调用的方法
     *
     * @param message 客户点发来的消息
     * @param session session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("来自客户端的消息：{}", message);

        //群发消息
        for (WebSocketServiceImpl item : webSocketSet) {

            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("发送错误！ {}", Arrays.toString(throwable.getStackTrace()));
    }

    /**
     * 发送消息
     *
     * @param message 客户端消息
     * @throws IOException 异常
     */
    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    //返回在线人数
    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    //当前人数增加
    private static synchronized void addOnlineCount() {
        WebSocketServiceImpl.onlineCount++;
    }

    //当前人数减少
    private static synchronized void subOnlineCount() {
        WebSocketServiceImpl.onlineCount--;
    }


}

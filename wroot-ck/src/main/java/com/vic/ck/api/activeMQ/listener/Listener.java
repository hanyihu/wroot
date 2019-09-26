package com.vic.ck.api.activeMQ.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 鉴定器demo,根据自身实际情况进行修改,必须实现MessageListener接口,
 */
public class Listener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("订阅者一收到的消息：" + ((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

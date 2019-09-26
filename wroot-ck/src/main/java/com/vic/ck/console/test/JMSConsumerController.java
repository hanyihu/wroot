package com.vic.ck.console.test;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jms.*;

@Controller
@RequestMapping(value = "/console/test/receive")
public class JMSConsumerController {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER; // 默认的连接用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD; // 默认的连接密码
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL; // 默认的连接地址
    ConnectionFactory connectionFactory; // 连接工厂
    Connection connection = null; // 连接
    Session session; // 会话 接受或者发送消息的线程
    Destination destination; // 消息的目的地
    MessageConsumer messageConsumer; // 消息的消费者

    @RequestMapping(value = "/")
    public String receive() {
        // 实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(JMSConsumerController.USERNAME, JMSConsumerController.PASSWORD, JMSConsumerController.BROKEURL);

        try {
            connection = connectionFactory.createConnection();  // 通过连接工厂获取连接
            connection.start(); // 启动连接
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE); // 创建Session
            destination = session.createTopic("FirstTopic2");  // 创建连接的消息主题
            messageConsumer = session.createConsumer(destination); // 创建消息消费者
            messageConsumer.setMessageListener(new Listener()); // 注册消息监听
            return "/console/test/activeMq";
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/close")
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (JMSException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}


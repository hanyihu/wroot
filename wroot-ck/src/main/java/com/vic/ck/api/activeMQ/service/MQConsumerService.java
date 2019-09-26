package com.vic.ck.api.activeMQ.service;

import com.vic.ck.entity.Customer;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Service;

import javax.jms.*;

/**
 * 抢单中心订阅者service
 */
@Service
public class MQConsumerService {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER; // 默认的连接用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD; // 默认的连接密码
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL; // 默认的连接地址
    ConnectionFactory connectionFactory; // 连接工厂
    Connection connection = null; // 连接
    Session session; // 会话 接受或者发送消息的线程
    Destination destination; // 消息的目的地
    MessageConsumer messageConsumer; // 消息的消费者

    /**
     * 开始接收订单信息
     *
     * @param customer 当前用户对象
     * @param listener 消息监听器
     */
    public void receiveMessage(Customer customer, MessageListener listener) {
        // 实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(MQConsumerService.USERNAME, MQConsumerService.PASSWORD, MQConsumerService.BROKEURL);

        try {
            connection = connectionFactory.createConnection();  // 通过连接工厂获取连接
            connection.start(); // 启动连接
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE); // 创建Session
            destination = session.createTopic("order_" + customer.getSendArea());  // 创建连接的消息主题
            messageConsumer = session.createConsumer(destination); // 创建消息消费者
            messageConsumer.setMessageListener(listener); // 注册消息监听
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    /**
     * 关闭连接,停止接收订单消息
     */
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

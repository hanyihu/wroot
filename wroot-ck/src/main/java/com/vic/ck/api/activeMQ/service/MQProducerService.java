package com.vic.ck.api.activeMQ.service;

import com.vic.ck.entity.Merchant;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Service;

import javax.jms.*;

/**
 * 抢单中心发布者service
 */
@Service
public class MQProducerService {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER; // 默认的连接用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD; // 默认的连接密码
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL; // 默认的连接地址

    /**
     * 发送消息
     *
     * @param session
     * @param messageProducer
     * @param orderId         订单号
     * @throws Exception
     */
    public static void sendMessage(Session session, MessageProducer messageProducer, int orderId) throws Exception {
        TextMessage message = session.createTextMessage("订单号:" + orderId);
//            System.out.println("订单号：" + orderId);
        messageProducer.send(message);
    }

    /**
     * 根据商家所在区域向Topic中发布消息
     *
     * @param merchant 商家信息
     * @param orderId  订单id
     * @return 发送状态 1:成功  0:失败
     */
    public boolean sendMessage(Merchant merchant, int orderId) {
        ConnectionFactory connectionFactory; // 连接工厂
        Connection connection = null; // 连接
        Session session; // 会话 接受或者发送消息的线程
        Destination destination; // 消息的目的地
        MessageProducer messageProducer; // 消息生产者

        // 实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(MQProducerService.USERNAME, MQProducerService.PASSWORD, MQProducerService.BROKEURL);

        try {
            connection = connectionFactory.createConnection(); // 通过连接工厂获取连接
            connection.start(); // 启动连接
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE); // 创建Session
            destination = session.createTopic("order_" + merchant.getAreaId());
            messageProducer = session.createProducer(destination); // 创建消息生产者
            sendMessage(session, messageProducer, orderId); // 发送消息
            session.commit();
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
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
}

package com.vic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class ActiveMQTest {
    //默认连接用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //默认连接密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //默认连接地址
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    //发送的消息数量
    private static final int SENDNUM = 10;

    public static void sendMessage(Session session, MessageProducer messageProducer) throws Exception {
        for (int i = 0; i < ActiveMQTest.SENDNUM; i++) {
            //创建一条消息文本
            TextMessage message = session.createTextMessage("ActiveMQ发送消息" + i);
            System.out.println("发送消息:ActiveMQ发送消息" + i);
            //通过消息生产者发送出消息
            messageProducer.send(message);
        }
    }

    @Test
    public void sendTest() {
        //连接工厂
        ConnectionFactory connectionFactory;
        //连接
        Connection connection = null;
        //会话 接收或者发送消息的线程
        Session session;
        //消息的目的地
        Destination destination;
        //消息生产者
        MessageProducer messageProducer;
        //实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(ActiveMQTest.USERNAME, ActiveMQTest.PASSWORD, ActiveMQTest.BROKEURL);
        try {
            //通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            //启动连接
            connection.start();
            //创建session
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            //创建一个名称为HelloWorld的消息队列
            destination = session.createQueue("shangjia1");
            //创建消息生产者
            messageProducer = session.createProducer(destination);
            //发送消息
            sendMessage(session, messageProducer);

            session.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void receiveTest() {
        ConnectionFactory connectionFactory;//连接工厂
        Connection connection = null;//连接

        Session session;//会话 接受或者发送消息的线程
        Destination destination;//消息的目的地

        MessageConsumer messageConsumer;//消息的消费者

        //实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(ActiveMQTest.USERNAME, ActiveMQTest.PASSWORD, ActiveMQTest.BROKEURL);
        try {
            //通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            //启动连接
            connection.start();
            //创建session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建一个连接HelloWorld的消息队列
            destination = session.createQueue("HelloWorld");
            //创建消息消费者
            messageConsumer = session.createConsumer(destination);

//            while (true) {
            TextMessage textMessage = (TextMessage) messageConsumer.receive(10000);
//                if(textMessage != null){
            System.out.println("收到的消息:" + textMessage.getText());
//                }else {
//                    break;
//                }
//            }

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }


}

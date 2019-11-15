package cn.bo.bookdemo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 */
public class MyProducer {
    private static final String EXCHANGE_NAME = "exchange_1";//交换机名称
    private static final String ROUTING_KEY= "routingkey_1";//路由键
    private static final String QUEUE_NAME= "queue_1";//列队名
    private static final String IP_ADDERSS= "127.0.0.1";//rabbit服务器所在ip
    private static final int PORT= 5672;//端口

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(IP_ADDERSS);
        factory.setPort(PORT);
        factory.setUsername("admin");
        factory.setPassword("admin");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",true,false,null);
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);
        String msg = "hello";
        channel.basicPublish(EXCHANGE_NAME,ROUTING_KEY,MessageProperties.PERSISTENT_TEXT_PLAIN,msg.getBytes());
        channel.close();
        connection.close();
    }
}

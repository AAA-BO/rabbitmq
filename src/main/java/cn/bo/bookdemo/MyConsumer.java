package cn.bo.bookdemo;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 */
public class MyConsumer {
    private static final String QUEUE_NAME = "queue_1";//列队名
    private static final String IP_ADDERSS = "127.0.0.1";//rabbit服务器所在ip
    private static final int PORT = 5672;//端口

    public static void main(String[] args) throws IOException, TimeoutException {
        Address[] addresses = new Address[]{new Address(IP_ADDERSS, PORT)};
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("admin");
        Connection connection = factory.newConnection(addresses);
        Channel channel = connection.createChannel();
        channel.basicQos(64);
        DefaultConsumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
            }
        };

    }

}

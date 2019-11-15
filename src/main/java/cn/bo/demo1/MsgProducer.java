package cn.bo.demo1;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 */
public class MsgProducer {

    /**
     * 发布消息
     * @param exchange 交换器
     * @param exchangeType 交换器类型
     * @param toutingKey 路由规则
     * @param message 消息主体
     * @throws IOException
     * @throws TimeoutException
     */
    public static void publishMsg(String exchange, BuiltinExchangeType exchangeType, String toutingKey, String message) throws IOException, TimeoutException {
        ConnectionFactory factory = RabbitUtil.getConnectionFactory();

        //创建连接
        Connection connection = factory.newConnection();

        //创建信道
        Channel channel = connection.createChannel();

        // 声明exchange中的消息为可持久化，不自动删除
        // 交换器 声明
        channel.exchangeDeclare(exchange, exchangeType, true, false, null);

        // 发布消息
        channel.basicPublish(exchange, toutingKey, null, message.getBytes());

        channel.close();
        connection.close();
    }
}

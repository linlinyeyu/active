package consume;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by linlinyeyu on 2018/1/8.
 */
public abstract class ReceiveMessage {
    private static final String USERNAME= ActiveMQConnection.DEFAULT_USER; // 默认的连接用户名
    private static final String PASSWORD= ActiveMQConnection.DEFAULT_PASSWORD; // 默认的连接密码
    private static final String BROKEURL= ActiveMQConnection.DEFAULT_BROKER_URL; // 默认的连接地址

    protected ActiveMQConnectionFactory connectionFactory; // 连接工厂
    protected Connection connection = null; // 连接
    protected Session session; // 会话 接受或者发送消息的线程
    protected Destination destination; // 消息的目的地
    protected MessageConsumer messageConsumer; // 消息的消费者

    public ReceiveMessage(String queueName){
        connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);
        connectionFactory.setTrustAllPackages(  true);
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(queueName);
            messageConsumer=session.createConsumer(destination);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public abstract void receiveMessage();
}

package provider.base;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import provider.base.ProviderBase;

import javax.jms.*;

/**
 * Created by linlinyeyu on 2018/1/9.
 */
public class TopicPerProvider implements ProviderBase {
    private static final String USERNAME= ActiveMQConnection.DEFAULT_USER; // 默认的连接用户名
    private static final String PASSWORD= ActiveMQConnection.DEFAULT_PASSWORD; // 默认的连接密码
    private static final String BROKEURL= ActiveMQConnection.DEFAULT_BROKER_URL; // 默认的连接地址

    private ActiveMQConnectionFactory connectionFactory; // 连接工厂
    private Connection connection = null; // 连接
    private Session session; // 会话 接受或者发送消息的线程
    private Destination destination; // 消息的目的地
    private MessageProducer messageProducer; // 消息的消费者
    
    public TopicPerProvider(String topic){
        this.connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);
        connectionFactory.setTrustAllPackages(true);
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        redeliveryPolicy.setUseExponentialBackOff(true);
        redeliveryPolicy.setMaximumRedeliveries(3);
        redeliveryPolicy.setInitialRedeliveryDelay(10000);
        redeliveryPolicy.setBackOffMultiplier(2);
        redeliveryPolicy.setMaximumRedeliveryDelay(5000);
        connectionFactory.setRedeliveryPolicy(redeliveryPolicy);
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic(topic);
            messageProducer = session.createProducer(destination);
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    @Override
    public <T> boolean sendMessage(T obj) {
        try {
            TextMessage textMessage = session.createTextMessage((String)obj);
            messageProducer.send(textMessage);
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return false;
    }
}

package consume;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by linlinyeyu on 2018/1/9.
 */
public abstract class TopicReceiveMessage {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;

    protected ActiveMQConnectionFactory connectionFactory;
    protected Connection connection = null;
    protected Session session;
    protected Destination destination;
    protected MessageConsumer messageConsumer;
    protected Topic topic;
    protected TopicSubscriber topicSubscriber;

    public TopicReceiveMessage(String topic){
        connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);
        try {
            connection = connectionFactory.createConnection();
            connection.setClientID("ybliu");
            session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
            //destination = session.createTopic(topic);
            this.topic = session.createTopic(topic);
            this.topicSubscriber = session.createDurableSubscriber(this.topic,"t1");
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public abstract boolean receiveMessage();
}

package provider;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by linlinyeyu on 2018/1/3.
 */
public abstract class SendMessage {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static final int SENDNUM = 10;
    protected ConnectionFactory connectionFactory;
    protected Connection connection = null;
    protected Session session;
    protected Destination destination;
    protected MessageProducer messageProducer;

    protected SendMessage(String queueName) {
        this.connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);
        try {
            this.connection.start();
            this.session = this.connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
            this.destination=this.session.createQueue(queueName);
            this.messageProducer = this.session.createProducer(this.destination);
            //不持久化
            this.messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public boolean closeSend(){
        try {
            this.connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    protected abstract <T> boolean sendMessage(T obj);
}

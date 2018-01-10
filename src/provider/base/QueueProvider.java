package provider.base;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import provider.base.ProviderBase;

import javax.jms.*;

/**
 * Created by linlinyeyu on 2018/1/3.
 */
public class QueueProvider implements ProviderBase {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;

    protected ActiveMQConnectionFactory connectionFactory;
    protected Connection connection = null;
    protected Session session;
    protected Destination destination;
    protected MessageProducer messageProducer;

    public QueueProvider(String queueName) {
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
            this.connection = connectionFactory.createConnection();
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

    public <T> boolean sendMessage(T obj){return false;};
}

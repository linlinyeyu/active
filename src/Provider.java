

import org.apache.activemq.*;

import javax.jms.*;

/**
 * Created by linlinyeyu on 2017/12/29.
 */
public class Provider {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static final int SENDNUM = 10;

    public static void main(String args[]){
        ConnectionFactory connectionFactory;
        Connection connection = null;
        Session session;
        Destination destination;
        MessageProducer messageProducer;

        connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);

        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
            destination=session.createQueue("FirstQueue1");
            messageProducer = session.createProducer(destination);
            sendMessage(session,messageProducer);
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if (connection!= null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void sendMessage(Session session,MessageProducer messageProducer) throws JMSException {
        ObjectMessage message = session.createObjectMessage();
        Person person = new Person("aa",11,"男");
        message.setObject(person);
        for (int i = 0;i<SENDNUM;i++){
            TextMessage textMessage = session.createTextMessage("测试消息"+i);
            messageProducer.send(message);
        }
    }
}

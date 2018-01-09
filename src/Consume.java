import consume.impl.PersonReceiveMessage;
import consume.impl.TopicPersonReceiveMessage;
import listener.Listener;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by linlinyeyu on 2017/12/29.
 */
public class Consume {

    public static void main(String []args){
        TopicPersonReceiveMessage receiveMessage = new TopicPersonReceiveMessage("first_topic");
        receiveMessage.receiveMessage();
    }
}

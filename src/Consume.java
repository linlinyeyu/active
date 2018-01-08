import consume.impl.PersonReceiveMessage;
import listener.Listener;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by linlinyeyu on 2017/12/29.
 */
public class Consume {

    public static void main(String []args){
        PersonReceiveMessage receiveMessage = new PersonReceiveMessage("first_queue");
        receiveMessage.receiveMessage();
    }
}

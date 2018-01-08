package provider.impl;

import provider.SendMessage;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.io.Serializable;

/**
 * Created by linlinyeyu on 2018/1/8.
 */
public class ObjectSendMessage extends SendMessage {
    public ObjectSendMessage(String queueName){
        super(queueName);
    }

    @Override
    public  <T> boolean sendMessage(T obj) {
        try {
            ObjectMessage objectMessage = session.createObjectMessage((Serializable) obj);
            messageProducer.send(objectMessage);
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

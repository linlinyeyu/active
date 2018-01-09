package provider.impl;

import provider.QueueProvider;

import javax.jms.JMSException;
import javax.jms.MapMessage;

/**
 * Created by linlinyeyu on 2018/1/8.
 */
public class MapQueueProvider extends QueueProvider {
    public MapQueueProvider(String queueName){
        super(queueName);
    }

    @Override
    public  <T> boolean sendMessage(T obj) {
        try {
            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString("message",(String) obj);
            messageProducer.send(mapMessage);
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

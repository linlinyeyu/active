package provider.impl;


import provider.SendMessage;

import javax.jms.JMSException;
import javax.jms.StreamMessage;

/**
 * Created by linlinyeyu on 2018/1/8.
 */
public class StreamSendMessage extends SendMessage {
    public StreamSendMessage(String queueName){
        super(queueName);
    }

    @Override
    public  <T> boolean sendMessage(T obj) {
        try {
            StreamMessage streamMessage = session.createStreamMessage();
            streamMessage.writeObject((Object)obj);
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return false;
    }
}

package provider.impl;

import provider.SendMessage;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * Created by linlinyeyu on 2018/1/3.
 */
public class StringSendMessage extends SendMessage {
    public StringSendMessage(String queueName){
        super(queueName);
    }

    @Override
    protected <T> boolean sendMessage(T obj) {
        try {
            TextMessage textMessage = this.session.createTextMessage((String) obj);
            this.messageProducer.send(textMessage);
            this.session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

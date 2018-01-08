package provider.impl;

import provider.SendMessage;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

/**
 * Created by linlinyeyu on 2018/1/8.
 */
public class BytesSendMessage extends SendMessage {
    BytesSendMessage(String queueName){
        super(queueName);
    }

    @Override
    protected <T> boolean sendMessage(T obj) {
        try {
            BytesMessage bytesMessage = session.createBytesMessage();
            bytesMessage.writeBytes((byte[]) obj);
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return false;
    }
}

package provider.impl.queue;

import provider.base.QueueProvider;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * Created by linlinyeyu on 2018/1/3.
 */
public class StringQueueProvider extends QueueProvider {
    public StringQueueProvider(String queueName){
        super(queueName);
    }

    @Override
    public  <T> boolean sendMessage(T obj) {
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

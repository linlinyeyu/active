package consume.impl;

import consume.TopicReceiveMessage;
import listener.PersonListener;

import javax.jms.JMSException;

/**
 * Created by linlinyeyu on 2018/1/9.
 */
public class TopicPersonReceiveMessage extends TopicReceiveMessage {
    public TopicPersonReceiveMessage(String topic,String clientId){
        super(topic,clientId);
    }
    @Override
    public boolean receiveMessage() {
        try {
            topicSubscriber.setMessageListener(new PersonListener());
        } catch (JMSException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

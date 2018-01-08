package consume.impl;

import consume.ReceiveMessage;
import listener.PersonListener;

import javax.jms.JMSException;

/**
 * Created by linlinyeyu on 2018/1/8.
 */
public class PersonReceiveMessage extends ReceiveMessage {
    public PersonReceiveMessage(String queue){
        super(queue);
    }

    @Override
    public void receiveMessage() {
        try {
            messageConsumer.setMessageListener(new PersonListener());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

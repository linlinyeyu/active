package impl;

import javax.jms.MessageProducer;
import javax.jms.Session;

/**
 * Created by linlinyeyu on 2018/1/3.
 */
public class StringSendMessage implements SendMessage {
    @Override
    public <T> void sendMessage(Session session, MessageProducer messageProducer, T message) {

    }
}

package listener;

import domain.Person;

import javax.jms.*;

/**
 * Created by linlinyeyu on 2018/1/8.
 */
public class PersonListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println(textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

package listener;

import domain.Person;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Created by linlinyeyu on 2018/1/8.
 */
public class PersonListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage)message;
        try {
            Person person = (Person) objectMessage.getObject();
            System.out.println(person.getName());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

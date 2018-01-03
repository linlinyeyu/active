import javax.jms.*;

/**
 * Created by linlinyeyu on 2017/12/29.
 */
public class Listener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage){
                ObjectMessage objectMessage = (ObjectMessage)message;
                Person person = (Person) objectMessage.getObject();
                System.out.println(person.getName()+person.getAge()+person.getSex());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

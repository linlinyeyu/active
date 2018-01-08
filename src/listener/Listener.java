package listener;

import domain.Person;

import javax.jms.*;

/**
 * Created by linlinyeyu on 2017/12/29.
 */
public class Listener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage){
                TextMessage textMessage = (TextMessage)message;
                String news = textMessage.getText();
                System.out.println(news);
            }
            if (message instanceof MapMessage){
                MapMessage mapMessage = (MapMessage)message;
                System.out.println(mapMessage.getString("message"));
            }
            if (message instanceof ObjectMessage){
                ObjectMessage objectMessage = (ObjectMessage)message;
                Person person = (Person) objectMessage.getObject();
                System.out.println(person.getName()+person.getAge()+person.getSex());
            }
            if (message instanceof BytesMessage){
                byte[] b = new byte[1024];
                int len = -1;
                BytesMessage bytesMessage = (BytesMessage)message;
                while ((len=bytesMessage.readBytes(b)) != -1){
                    System.out.println(new String(b,0,len));
                }
            }

            if (message instanceof StreamMessage){
                StreamMessage streamMessage = (StreamMessage)message;
                System.out.println(streamMessage.readString());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

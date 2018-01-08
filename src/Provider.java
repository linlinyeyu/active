

import domain.Person;
import org.apache.activemq.*;
import provider.SendMessage;
import provider.impl.ObjectSendMessage;

import javax.jms.*;

/**
 * Created by linlinyeyu on 2017/12/29.
 */
public class Provider {

    public static void main(String args[]){
        SendMessage sendMessage = new ObjectSendMessage("first_queue");
            Person person = new Person("shizhuang",12,"女");
            sendMessage.sendMessage(person);
    }
}

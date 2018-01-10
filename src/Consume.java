import consume.impl.TopicPersonReceiveMessage;

/**
 * Created by linlinyeyu on 2017/12/29.
 */
public class Consume {

    public static void main(String []args){
        TopicPersonReceiveMessage receiveMessage = new TopicPersonReceiveMessage("first_topic","ybliu");
        receiveMessage.receiveMessage();
    }
}

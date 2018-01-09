import provider.TopicProvider;
/**
 * Created by linlinyeyu on 2017/12/29.
 */
public class Provider {

    public static void main(String args[]){
        TopicProvider topicProvider = new StringTopicSendMessage("first_provider");
            topicProvider.persistantSend("测试消息");
    }
}

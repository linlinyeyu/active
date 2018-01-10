package provider.factory;

import provider.base.ProviderBase;
import provider.base.QueueProvider;
import provider.base.TopicPerProvider;
import provider.base.TopicProvider;

/**
 * Created by linlinyeyu on 2018/1/10.
 */
public class ProviderFactoryImpl implements ProviderFactory {
    private QueueProvider queueProvider;
    private TopicProvider topicProvider;
    private TopicPerProvider topicPerProvider;

    public ProviderFactoryImpl(){};

    @Override
    public ProviderBase getQueueProviderInstance(String queueName) {
        if (queueProvider == null){
            synchronized (ProviderFactoryImpl.class){
                if (queueProvider == null){
                    queueProvider = new QueueProvider(queueName);
                }
            }
        }
        return queueProvider;
    }

    @Override
    public ProviderBase getTopicUnpersisInstance(String topicName) {
        if (topicProvider == null){
            synchronized (this){
                if (topicProvider == null){
                    topicProvider = new TopicProvider(topicName);
                }
            }
        }
        return topicProvider;
    }

    @Override
    public ProviderBase getTopicPersistantInstance(String topicName) {
        if (topicPerProvider == null){
            synchronized (this){
                if (topicPerProvider == null){
                    topicPerProvider = new TopicPerProvider(topicName);
                }
            }
        }
        return topicPerProvider;
    }
}

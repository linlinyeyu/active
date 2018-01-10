package provider.factory;

import provider.base.ProviderBase;

/**
 * Created by linlinyeyu on 2018/1/9.
 */
public interface ProviderFactory {
    ProviderBase getQueueProviderInstance(String queueName);

    ProviderBase getTopicUnpersisInstance(String topicName);

    ProviderBase getTopicPersistantInstance(String topicName);
}

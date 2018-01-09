package provider;

/**
 * Created by linlinyeyu on 2018/1/9.
 */
public interface ProviderFactory {
    ProviderBase getQueueProviderInstance();

    ProviderBase getTopicUnpersisInstance();

    ProviderBase getTopicPersistantInstance();
}

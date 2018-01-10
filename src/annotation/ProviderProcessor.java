package annotation;

import provider.factory.ProviderFactory;

import java.lang.reflect.Field;

/**
 * Created by linlinyeyu on 2018/1/9.
 */
public class ProviderProcessor {
    ProviderFactory providerFactory;
    public void parseMethod(Class<?> clazz){
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields) {
            Provider provider = null;
            if((provider = field.getAnnotation(Provider.class)) != null){
                field.setAccessible(true);
                switch (provider.value()){
                    case "QueueProvider":
                        try {
                            field.set(clazz,providerFactory.getQueueProviderInstance(provider.topicName()));
                            break;
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    case "TopicUnper":
                        try {
                            field.set(clazz,providerFactory.getTopicUnpersisInstance(provider.topicName()));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    case "TopicPer":
                        try {
                            field.set(clazz,providerFactory.getTopicPersistantInstance(provider.topicName()));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                }
            }
        }
    }
}

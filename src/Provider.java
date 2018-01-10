import provider.base.ProviderBase;
import provider.factory.ProviderFactory;
import provider.factory.ProviderFactoryImpl;

/**
 * Created by linlinyeyu on 2017/12/29.
 */
public class Provider {
    public static void main(String args[]){
        ProviderFactory providerFactory = new ProviderFactoryImpl();
        ProviderBase providerBase = providerFactory.getTopicPersistantInstance("first_queue");
        providerBase.sendMessage("测试数据");
    }
}

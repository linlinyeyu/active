package provider;

/**
 * Created by linlinyeyu on 2018/1/9.
 */
public interface ProviderBase {
    <T> boolean sendMessage(T obj);
}

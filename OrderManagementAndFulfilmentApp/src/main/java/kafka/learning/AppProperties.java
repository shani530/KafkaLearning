package kafka.learning;

public class AppProperties {
    public static final String managementApplicationId = "OrderManagementId";
    public static final String fulfilmentApplicationId = "OrderFulfilmentId";
    public static final String bootStrapServers = "192.168.99.100:29092";
    public static final String orderTopic = "order-topic";
    public static final String orderStatusTopic = "orderStatus-topic";
    public static final String groupID = "OrderFulfilmentGroup";
    public static final String truststore_location = "certs/client.truststore.jks";
    public static final String truststore_password = "test1234";
    public static final String keystore_location = "certs/client.keystore.jks";
    public static final String keystore_password = "test1234";
    public static final String key_password = "test1234";

}
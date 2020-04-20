package aot.technologies.test;

import aot.technologies.test.datagenerator.OrderGenerator;
import aot.technologies.test.serdes.JsonDeserializer;
import aot.technologies.test.serdes.JsonSerializer;
import aot.technologies.test.types.OrderItem;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;


public class OrderManagementApp {
    private static final Logger logger= LogManager.getLogger();
    public static void main (String[] args) throws IOException, InterruptedException {
         /*
            Producer Configuration for Order topic
         */
        Properties producerProperties=new Properties();
        producerProperties.put(ProducerConfig.CLIENT_ID_CONFIG,AppProperties.managementApplicationId);
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,AppProperties.bootStrapServers);
        producerProperties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,"SSL");
        producerProperties.put("ssl.endpoint.identification.algorithm","");
        producerProperties.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG,AppProperties.truststore_location);
        producerProperties.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG,AppProperties.truststore_password);
        producerProperties.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG,AppProperties.keystore_location);
        producerProperties.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG,AppProperties.keystore_password);
        producerProperties.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG,AppProperties.key_password);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        KafkaProducer<String, OrderItem> producer=new KafkaProducer<String, OrderItem>(producerProperties);
        logger.info("Sending the messages to topic :order-topic");
        OrderGenerator generator=new OrderGenerator();
        OrderItem[] items =generator.getOrderItems();

        for(OrderItem item:items) {
            logger.info(item);
            ProducerRecord<String, OrderItem> record = new ProducerRecord<String, OrderItem>(AppProperties.orderTopic, "", item);
            producer.send(record);
        }
        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
                    producer.close();
                    logger.info("Closing the kafka broker");
                })
        );

        /*

        Consumer Configuration for OrderStatus topic
         */
        Properties consumerProperties = new Properties();
        consumerProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, AppProperties.managementApplicationId);
        consumerProperties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,"SSL");
        consumerProperties.put("ssl.endpoint.identification.algorithm","");
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppProperties.bootStrapServers);
        consumerProperties.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG,AppProperties.truststore_location);
        consumerProperties.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG,AppProperties.truststore_password);
        consumerProperties.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG,AppProperties.keystore_location);
        consumerProperties.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG,AppProperties.keystore_password);
        consumerProperties.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG,AppProperties.key_password);
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG,AppProperties.groupID);
        consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        KafkaConsumer<String, OrderItem> consumer = new KafkaConsumer<String, OrderItem>(consumerProperties);
        consumer.subscribe(Arrays.asList(AppProperties.orderStatusTopic));
        logger.info("Receiving the messages from topic :orderStatus-topic");
        while (true) {
            ConsumerRecords<String, OrderItem> records = consumer.poll(1000);
            for (ConsumerRecord<String, OrderItem> record : records) {
                logger.info(record.value());
            }

        }

    }
}

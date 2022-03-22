package kafka.learning;

import kafka.learning.types.OrderItem;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookOrderFulfilment {
    private static final Logger logger = LogManager.getLogger();
    ConsumerRecord<String, OrderItem> record;
    KafkaProducer<String, OrderItem> producer;

    BookOrderFulfilment(KafkaProducer<String, OrderItem> producer, ConsumerRecord<String, OrderItem> record) {
        this.producer = producer;
        this.record = record;
    }

    public void sendBookOrders() throws InterruptedException {
        logger.info("Receiving the Book messages from topic :order-topic");
        logger.info(record.value());
        record.value().setProductOrderStatus("NEW");
        producer.send(new ProducerRecord<String, OrderItem>(AppProperties.orderStatusTopic, "", record.value()));
        logger.info("Sending the Book messages to topic :orderStatus-topic");
        logger.info(record.value());
        Thread.sleep(5000);
        record.value().setProductOrderStatus("SHIPPED");
        producer.send(new ProducerRecord<String, OrderItem>(AppProperties.orderStatusTopic, "", record.value()));
        Thread.sleep(5000);
        logger.info(record.value());
        record.value().setProductOrderStatus("DELIVERED");
        producer.send(new ProducerRecord<String, OrderItem>(AppProperties.orderStatusTopic, "", record.value()));
        Thread.sleep(5000);
        logger.info(record.value());
    }

}


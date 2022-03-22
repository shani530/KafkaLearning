package kafka.learning.datagenerator;

import kafka.learning.types.OrderItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class OrderGenerator {
    private static final Logger logger = LogManager.getLogger();
    OrderItem[] orderItems;
    String DATAFILE = "src/main/resources/data/order.json";
    ObjectMapper mapper = new ObjectMapper();
    File file = new File(DATAFILE);

    public OrderItem[] getOrderItems() throws IOException {
        orderItems = mapper.readValue(file, OrderItem[].class);
        return orderItems;
    }
   /*
   // Testing OrderGenerator class
    public static void main (String[] args) throws IOException {

        OrderGenerator generator= new OrderGenerator();
       OrderItem[] items=generator.getOrderItems();
       for( OrderItem item:items){
           logger.info(item);
       }
    }

    */
}

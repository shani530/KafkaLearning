package kafka.learning.serdes;

import kafka.learning.types.OrderItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class JsonDeserializer<T> implements Deserializer<T> {
    private ObjectMapper objectMapper = new ObjectMapper();

    public JsonDeserializer() {

    }


    @Override
    public void configure(Map<String, ?> props, boolean isKey) {

    }


    @Override
    public T deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        try {
            return (T) objectMapper.readValue(data, OrderItem.class);
        } catch (Exception e) {
            throw new SerializationException(e);
        }
    }

    @Override
    public void close() {
        //nothing to close
    }
}

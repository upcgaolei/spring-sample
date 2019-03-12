package com.github.xiaozhong.msg;

import com.github.xiaozhong.component.MyComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhougaolei
 * @date At 2019/3/7
 */
public class Consumer {

    private static final Log logger = LogFactory.getLog(Consumer.class);

    private String topic;

    private String groupId;

    private KafkaMessageListenerContainer<Integer, String> container;

    @Resource
    private MyComponent myComponent;

    private long i = 1;

    private void start() {
        try {
            ContainerProperties containerProps = new ContainerProperties(topic);
            containerProps.setMessageListener(new MessageListener<Integer, String>() {
                @Override
                public void onMessage(ConsumerRecord<Integer, String> message) {
                    logger.info("received: " + message + ", " + i);
                    myComponent.dealMessage(message.value(), i);
                    i++;
                }
            });
            container = createContainer(containerProps);
            container.start();
            logger.info(this.getClass().getName() + "- - - initializing bean");
        } catch (Exception e) {
            logger.error("consume message error", e);
        }
    }

    private void close() throws Exception {
        try {
            container.stop();
            logger.info(this.getClass().getName() + "- - - destroying bean");
        } catch (Exception e) {
            logger.error("consumer close error", e);
        }
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    private KafkaMessageListenerContainer<Integer, String> createContainer(
            ContainerProperties containerProps) {
        Map<String, Object> props = consumerProps();
        DefaultKafkaConsumerFactory<Integer, String> cf =
                new DefaultKafkaConsumerFactory<Integer, String>(props);
        KafkaMessageListenerContainer<Integer, String> container =
                new KafkaMessageListenerContainer<>(cf, containerProps);
        return container;
    }

    private Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }
}

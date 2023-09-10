package com.example.serieservice.queue;

import com.example.serieservice.model.Serie;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SerieSender {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topicExchange;

    private final String routingKey = "quick.serie.rabbit";

    private static final Logger LOGGER = LoggerFactory.getLogger(SerieSender.class);

    public void sendMsg(Serie serie) {
        LOGGER.info(String.format("Json message sent to only match routing key-> %s", serie.toString()));
        rabbitTemplate.convertAndSend(topicExchange.getName(), routingKey, serie);
    }

}

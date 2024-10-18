package com.manager.services;

import com.manager.models.Message;
import com.manager.repositories.MessageRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.lang.annotation.Documented;
import java.net.MalformedURLException;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final WebClient webClient;

    @Value("${spring.rabbitmq.queue}")
    private String queueName;

    private static final Logger LOGGER = LoggerFactory.getLogger(Documented.class);

    @Autowired
    public MessageService(WebClient webClient) {
        this.webClient = webClient;
    }


    public List<Message> getAll(){
        return messageRepository.findAll();
    }

    public void delete(Integer id) {
        messageRepository.deleteById(id);
    }

    public Message getById(Integer id) {
        return messageRepository.getReferenceById(id);
    }

    public Message sendMessage(Message message) throws Exception {
//        rabbitTemplate.convertAndSend(queueName,message.getMenssage());
       Message answer = buscaResp(message).block();
        return messageRepository.save(answer);
    }


    public Mono<Message> buscaResp (Message message) {
        return webClient.post().uri("/query").
                contentType(MediaType.APPLICATION_JSON)
                .bodyValue(message).retrieve().bodyToMono(Message.class);
    }
}

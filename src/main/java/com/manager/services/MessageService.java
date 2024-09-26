package com.manager.services;

import com.manager.models.Message;
import com.manager.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll(){
        return messageRepository.findAll();
    }

    public void delete(Integer id) {
        messageRepository.deleteById(id);
    }

    public Message update(Message message) {
        return messageRepository.save(message);
    }

    public Message getById(Integer id) {
        return messageRepository.getReferenceById(id);
    }

    public Message save(Message message) {
        return messageRepository.save(message);
    }
}

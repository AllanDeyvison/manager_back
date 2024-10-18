package com.manager.controllers;

import com.manager.models.Message;
import com.manager.repositories.MessageRepository;
import com.manager.services.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<Message> post(@Valid @RequestBody Message message) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.sendMessage(message));
    }

    @GetMapping
    public ResponseEntity<List<Message>> getAll(){
        return ResponseEntity.ok(messageService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getById(@PathVariable Integer id){
        return ResponseEntity.ok(messageService.getById(id));
    }

    @DeleteMapping("/{id}")
    public  void delete(@PathVariable Integer id){
        messageService.delete(id);
    }
}

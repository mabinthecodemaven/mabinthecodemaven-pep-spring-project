package com.example.service;
import java.util.*;
import com.example.entity.*;
import com.example.exception.MessageException;
import com.example.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    private AccountService accountService;

    public MessageService(MessageRepository messageRepository, AccountService accountService){
        this.messageRepository = messageRepository;
        this.accountService = accountService;
    }

    public Message newMessage(Message message){
        accountService.findByAccountId(message.getPostedBy());
        String messageText = message.getMessageText();
        if(messageText.isBlank() || messageText.length() == 0){
            throw new MessageException.InvalidMessageException("Message is blank");
        }
        if(messageText.length()>255){
            throw new MessageException.InvalidMessageException("Message exceeds 255 characters");
        }
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessage(int messageId){
        return messageRepository.findByMessageId(messageId);
    }

    public Integer deleteMessage(int messageId){
        if(messageRepository.existsById(messageId)){
            messageRepository.deleteById(messageId);
            return 1;
        }
        return null;
    }

    public Integer updateMessage(int messageId, String newText){
        Message message = messageRepository.findByMessageId(messageId);

        if(message == null){
            throw new MessageException.InvalidMessageException("Message ID not found");
        }
        if(newText == null || newText.isBlank() || newText.length() == 0){
            throw new MessageException.InvalidMessageException("Blank message");
        }
        if(newText.length() > 255){
            throw new MessageException.InvalidMessageException("Message exceeds 255 characters");
        }
        message.setMessageText(newText);
        messageRepository.save(message);
        return 1;
    }

    public List<Message> getMessagesByAccountId(Integer accountId){
        return messageRepository.findByPostedBy(accountId);
    }
}

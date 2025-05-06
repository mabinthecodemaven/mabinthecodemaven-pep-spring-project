package com.example.controller;
import org.springframework.web.bind.annotation.*;
import org.jboss.logging.Messages;
import org.springframework.http.*;
import com.example.entity.*;
import com.example.exception.AccountException;
import com.example.exception.MessageException;
import com.example.service.*;

import java.util.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
 public class SocialMediaController {
    private AccountService accountService;
    private MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }
 
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account) {
        try{
            Account newAccount = accountService.register(account);
            return ResponseEntity.ok(newAccount);
        }
        catch(AccountException.InvalidAccountException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch(AccountException.DuplicateUsernameException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }        
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }
 
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        try{
            Account fetchedAcc = accountService.login(account);
            return ResponseEntity.ok(fetchedAcc);
        }
        catch(AccountException.InvalidLoginException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
        }
    }
 
    @PostMapping("/messages")
    public ResponseEntity<?> createMessage(@RequestBody Message message) {
        try{
            return ResponseEntity.ok(messageService.newMessage(message));
        }
        catch(AccountException.InvalidAccountException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch(MessageException.InvalidMessageException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
        }
    }
 
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }
 
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<?> getMessageById(@PathVariable int messageId) {        
        Message message = messageService.getMessage(messageId);
        return message == null ? ResponseEntity.ok().body("") : ResponseEntity.ok(message);        
    }
 
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable int messageId) {
        return messageService.deleteMessage(messageId) == null ? ResponseEntity.ok("") : ResponseEntity.ok(1);
    }
 
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<?> updateMessage(@PathVariable int messageId, @RequestBody Map<String, String> body) {
        String newText = body.get("messageText");
        try{            
            return ResponseEntity.status(HttpStatus.OK).body(messageService.updateMessage(messageId, newText));
        }
        catch(MessageException.InvalidMessageException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
 
    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getMessagesByUser(@PathVariable Integer accountId) {
        List<Message> messages = messageService.getMessagesByAccountId(accountId);
        return ResponseEntity.ok(messages);
    }
 }

package com.example.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import com.example.entity.*;
import java.util.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
 public class SocialMediaController {
 
     @PostMapping("/register")
     public ResponseEntity<Account> register(@RequestBody Account account) {
         // logic here
     }
 
     @PostMapping("/login")
     public ResponseEntity<Account> login(@RequestBody Account account) {
         // logic here
     }
 
     @PostMapping("/messages")
     public ResponseEntity<Message> createMessage(@RequestBody Message message) {
         // logic here
     }
 
     @GetMapping("/messages")
     public List<Message> getAllMessages() {
         // logic here
     }
 
     @GetMapping("/messages/{messageId}")
     public ResponseEntity<Message> getMessageById(@PathVariable int messageId) {
         // logic here
     }
 
     @DeleteMapping("/messages/{messageId}")
     public ResponseEntity<Integer> deleteMessage(@PathVariable int messageId) {
         // logic here
     }
 
     @PatchMapping("/messages/{messageId}")
     public ResponseEntity<Integer> updateMessage(@PathVariable int messageId, @RequestBody Map<String, String> updateBody) {
         // logic here
     }
 
     @GetMapping("/accounts/{accountId}/messages")
     public List<Message> getMessagesByAccount(@PathVariable int accountId) {
         // logic here
     }
 }

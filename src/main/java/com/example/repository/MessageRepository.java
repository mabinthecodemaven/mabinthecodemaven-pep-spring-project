package com.example.repository;
import java.util.*;
import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer>{
   public Message findByMessageId(int messageId);
   public List<Message> findByPostedBy(Integer accountId);
}

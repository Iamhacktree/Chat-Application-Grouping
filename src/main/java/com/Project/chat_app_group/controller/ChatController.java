package com.Project.chat_app_group.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.Project.chat_app_group.model.ChatMessage;

@Controller
public class ChatController {
    
    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Send message to a specific group dynamically
    @MessageMapping("/sendMessage/{groupName}")
    public void sendMessageToGroup(@DestinationVariable String groupName, ChatMessage message) {
        messagingTemplate.convertAndSend("/group/" + groupName, message);
    }

    // Return chat page view
    @GetMapping("/chat")
    public String chat() {
        return "chats";
    }
}

/*
 * SimpMessagingTemplate
Enables sending messages dynamically instead of a fixed topic.
Modified sendMessage() Method

Uses @DestinationVariable String groupName to send messages to specific groups.
Clients can now subscribe to /group/{groupName} instead of /topic/messages.


Modify stompClient.subscribe('/group/' + groupName) in the front end to listen for messages in a selected group.
 * */

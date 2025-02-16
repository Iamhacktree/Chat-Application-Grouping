package com.Project.chat_app_group.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// TODO Auto-generated method stub
		registry.addEndpoint("/chat")
				.setAllowedOrigins("http://localhost:8080")
				.withSockJS(); //this is to add compatibility to the clients who don't use Sock JS, this makes app accessible to more users.
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		/*
		 * This enables a simple in-memory message broker that will handle messages sent
		 * to destinations prefixed with /topic. 
		 * Clients subscribed to /topic/... will receive messages sent to those destinations. 
		 * Example: If a client subscribes to /topic/chat, they will receive messages published to /topic/chat
		 */
		registry.enableSimpleBroker("/topic", "/group"); // Add a new prefix for group chats
		
		/*
		 * This sets a prefix (/app) for messages sent from clients. Any message sent
		 * from a client with a destination like /app/sendMessage will be routed to a
		 * controller method in the back end
		 */
		registry.setApplicationDestinationPrefixes("/app");
		//expect message with /app/sendMessage
		
	}
	//Message broker is the middle to direct message to right place
	
}

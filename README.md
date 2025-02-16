# Real-Time Chat Application with WebSocket and Grouping

## Overview
This project is a real-time chat application that allows users to join different chat groups and exchange messages dynamically. It utilizes **Spring Boot**, **WebSocket (STOMP, SockJS)** for real-time communication, **MongoDB** for storing groups, and a **React or JSP frontend** integrated with **Bootstrap**.

## Features
- **Group-Based Chatting**: Users can join different chat rooms dynamically.
- **Real-Time Messaging**: Messages are instantly delivered using WebSocket with STOMP.
- **Spring Boot Backend**: Handles WebSocket connections and group management.
- **MongoDB Integration**: Stores group names persistently.
- **Bootstrap UI**: Simple and responsive frontend.

## Technologies Used
- **Backend**: Spring Boot, Spring WebSocket, STOMP, SockJS, MongoDB
- **Frontend**: HTML, JavaScript, Bootstrap, SockJS, STOMP.js
- **Database**: MongoDB (Atlas or Local)
- **Build Tool**: Maven

## Project Structure
```
Chat-Application-Grouping/
│── src/
│   ├── main/
│   │   ├── java/com/Project/chat_app/
│   │   │   ├── controller/
│   │   │   │   ├── ChatController.java
│   │   │   │   ├── GroupController.java
│   │   │   ├── model/
│   │   │   │   ├── ChatMessage.java
│   │   │   │   ├── Group.java
│   │   │   ├── repository/
│   │   │   │   ├── GroupRepository.java
│   │   │   ├── service/
│   │   │   │   ├── GroupService.java
│   │   │   ├── config/
│   │   │   │   ├── WebSocketConfig.java
│   ├── resources/
│   │   ├── static/js/
│   │   │   ├── chat.js
│   │   ├── templates/
│   │   │   ├── chat.html
│── pom.xml
│── README.md
```

## Setup Instructions
### 1. Clone the Repository
```sh
git clone https://github.com/your-repo/chat-app.git
cd chat-app
```

### 2. Configure MongoDB
Make sure MongoDB is running and update `application.properties`:


### 3. Build and Run the Application
```sh
mvn spring-boot:run
```

### 4. Open the Chat UI
Go to:
```
http://localhost:8080/chat
```

## API Endpoints
| Endpoint         | Method | Description |
|-----------------|--------|-------------|
| `/chat`         | GET    | Returns the chat page |
| `/ws`           | WS     | WebSocket connection |
| `/app/sendMessage/{group}` | POST  | Sends a message to a specific group |

## Future Enhancements
- User authentication and login
- Private messaging between users
- Message history storage

## Contributing
Feel free to open an issue or submit a pull request if you'd like to contribute!




# Real-Time Chat Application

This project is a real-time chat application developed using Spring Boot, WebSocket, and Redis. It demonstrates efficient and scalable real-time communication by combining WebSocket with Redis for message broadcasting.

## Features
- **User Authentication**: Users can enter a username to join the chat.
- **Real-Time Messaging**: Messages are instantly broadcast to all connected clients via WebSocket.
- **Redis Integration**: Messages are handled and broadcast using Redis to ensure quick and scalable delivery.
- **Join and Leave Notifications**: Notifications are sent when users join or leave the chat.

## Technologies Used

### Backend
- **Spring Boot**: The core framework for building scalable Java applications.
- **Spring WebSocket**: Provides WebSocket support for real-time communication.
- **Spring Data Redis**: Integrates Redis for efficient message broadcasting and persistence.
- **Spring Messaging**: Handles messaging protocols (STOMP) for WebSocket communication.
- **Redis**: An in-memory data store used for message publishing and subscribing.

## Key Components
- **WebSocketConfig**: Configures WebSocket endpoints and message broker for client-server communication.
- **RedisConfig**: Sets up Redis for message publishing and subscribing.
- **ChatController**: Manages user interactions such as joining the chat and sending messages.
- **RedisMessageSubscriber**: Listens for Redis messages and broadcasts them to WebSocket clients.
- **WebSocketEventListener**: Handles WebSocket connection and disconnection events, broadcasting notifications when users join or leave.
- **ChatMessage**: Data Transfer Object (DTO) for transferring chat messages between clients and the server.

## How It Works

1. **User Connection**:  
   Users connect to the WebSocket server, and a JOIN message is broadcasted to notify all connected clients.
   
2. **Message Broadcasting**:  
   - Messages sent by users are broadcast to all connected clients in real-time through WebSocket.  
   - Redis acts as the message broker, ensuring quick and reliable delivery to all clients.

3. **User Disconnection**:  
   When a user disconnects, a LEAVE message is broadcasted to notify other clients.

## Prerequisites
- Java 21 or later
- Spring Boot 3.0+
- Redis (for message broadcasting)

## Running the Application

1. **Clone the repository**:
    
    ```bash
    git clone https://github.com/git-mini/chat-app.git
    ```

2. **Navigate to the project directory**:
    
    ```bash
    cd chat-app
    ```

3. **Start the Redis server** (if not already running).

4. **Run the Spring Boot application**:
    
    ```bash
    ./mvn spring-boot:run -Dspring-boot.run.profiles=instance1
    ```
    
    ```bash
    ./mvn spring-boot:run -Dspring-boot.run.profiles=instance2
    ```
    
    ```bash
    ./mvn spring-boot:run -Dspring-boot.run.profiles=instance3
    ```

5. **Access the application on different instances**:
   - [http://localhost:8080](http://localhost:8080)
   - [http://localhost:8081](http://localhost:8081)
   - [http://localhost:8082](http://localhost:8082)

## Contributing

Feel free to fork the repository, open issues, and submit pull requests for bug fixes or improvements.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

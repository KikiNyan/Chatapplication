/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatapplication;

/**
 *
 * @author ryanf
 */
import java.io.*;        // Input and output classes
import java.net.*;       // Networking classes
import java.util.*;      // Utility classes for Set and HashSet

// Main class to handle the chat server
public class ChatServer {
    // A Set to store all the connected clients
    private static Set<ClientHandler> clientHandlers = new HashSet<>();

    // Main method - starts the server and waits for clients
    public static void main(String[] args) throws IOException {
        // Create a server socket on port 12345
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Chat server started on port 12345...");

        // Infinite loop to keep accepting new client connections
        while (true) {
            // Wait for a new client to connect
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client connected.");

            // Create a new handler for the connected client
            ClientHandler clientHandler = new ClientHandler(clientSocket);

            // Add the new client handler to the set of active clients
            clientHandlers.add(clientHandler);

            // Start a new thread for the client handler so it can run in parallel
            new Thread(clientHandler).start();
        }
    }

    // Method to broadcast a message to all clients except the sender
    public static void broadcastMessage(String message, ClientHandler sender) {
        // Iterate through all connected clients
        for (ClientHandler clientHandler : clientHandlers) {
            // Send the message to everyone except the sender
            if (clientHandler != sender) {
                clientHandler.sendMessage(message);
            }
        }
    }

    // Method to remove a client from the active list when they disconnect
    public static void removeClient(ClientHandler clientHandler) {
        clientHandlers.remove(clientHandler);
    }
}

// Class to handle each client connected to the server
class ClientHandler implements Runnable {
    private Socket socket;         // The client socket connection
    private PrintWriter out;       // To send data to the client
    private BufferedReader in;     // To receive data from the client

    // Constructor to initialize the socket when a client connects
    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    // The run method is executed when the thread starts
    @Override
    public void run() {
        try {
            // Initialize the input stream to read data from the client
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Initialize the output stream to send data to the client
            out = new PrintWriter(socket.getOutputStream(), true);

            String message;

            // Keep reading messages from the client until the client disconnects
            while ((message = in.readLine()) != null) {
                // Print the received message on the server console
                System.out.println("Received: " + message);

                // Broadcast the message to other clients (except the sender)
                ChatServer.broadcastMessage(message, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // When the client disconnects, remove them from the server's client list
            ChatServer.removeClient(this);
            try {
                socket.close();  // Close the socket connection
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to send a message to the client
    public void sendMessage(String message) {
        out.println(message);  // Send the message to the client using PrintWriter
    }
}
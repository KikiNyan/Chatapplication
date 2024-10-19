/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatapplication;

/**
 *
 * @author ryanf
 */
import java.io.*;        // Input and output classes for handling communication
import java.net.*;       // Networking classes for managing the client-server connection

// Main class for the chat client
public class ChatClient {
    private Socket socket;            // Socket for connecting to the server
    private BufferedReader in;        // Input stream to receive messages from the server
    private PrintWriter out;          // Output stream to send messages to the server

    // Constructor to connect to the server and set up input/output streams
    public ChatClient(String serverAddress, int serverPort) throws IOException {
        // Create a new socket to connect to the server at the specified address and port
        socket = new Socket(serverAddress, serverPort);

        // Set up the input stream to receive data from the server
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Set up the output stream to send data to the server
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    // Method to start the client, allowing the user to send and receive messages
    public void start() {
        // Start a new thread to handle incoming messages from the server
        new Thread(new IncomingMessagesHandler()).start();

        // Set up BufferedReader to read input from the keyboard (user input)
        BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));

        try {
            String message;
            // Continuously read messages from the keyboard and send them to the server
            while ((message = keyboardInput.readLine()) != null) {
                out.println(message);  // Send the message to the server
            }
        } catch (IOException e) {
            e.printStackTrace();  // Handle any exceptions related to input/output
        }
    }

    // Inner class to handle receiving messages from the server in a separate thread
    private class IncomingMessagesHandler implements Runnable {
        @Override
        public void run() {
            String message;
            try {
                // Continuously read messages from the server and display them
                while ((message = in.readLine()) != null) {
                    System.out.println("Message from server: " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();  // Handle any exceptions during receiving messages
            }
        }
    }

    // Main method to run the client
    public static void main(String[] args) throws IOException {
        // Create a ChatClient object to connect to the server at localhost on port 12345
        ChatClient client = new ChatClient("localhost", 12345);

        // Start the client and begin communication
        client.start();
    }
}
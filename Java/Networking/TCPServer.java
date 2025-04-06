import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started. Waiting for clients...");


            // Wait for client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");


            // Step 2: Read data sent by the client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientMessage = in.readLine();
            System.out.println("Received from client: " + clientMessage);


            // Step 3: Send a response back to the client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("Hello from the server!");


            // Step 4: Close the client connection
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
import java.io.*;
import java.net.Socket;


public class TCPClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080)) {
            System.out.println("Connected to the server.");


            // Step 1: Send a message to the server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Hello from the client!");


            // Step 2: Read the server's response
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverMessage = in.readLine();
            System.out.println("Received from server: " + serverMessage);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
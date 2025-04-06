import java.net.InetAddress;


public class InetAddressExample {
    public static void main(String[] args) {
        try {
            // Resolving the IP address of google.com
            InetAddress google = InetAddress.getByName("www.google.com");
            System.out.println("Google's IP Address: " + google.getHostAddress());
           
            // Get the hostname from the resolved IP
            System.out.println("Hostname: " + google.getHostName());


            // Getting the local host IP address
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("Local Host Address: " + localHost.getHostAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
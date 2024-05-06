import java.net.*;
import java.io.*;
public class NetworkingServer {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket Client;

        // Default port number
        // Both 80 and 8080 are common port numbers for local host services
        int portNumber = 8080;

        if(args.length >= 1)
        {
            portNumber = Integer.parseInt(args[0]);
        }

        try {
            server = new ServerSocket(portNumber);
        }
        catch (IOException ie) {
            System.out.println("Cannot open socket." + ie);
        }

        System.out.println("Serversocket is created " + server);
    }
}
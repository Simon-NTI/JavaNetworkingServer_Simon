import java.net.*;
import java.io.*;
public class NetworkingServer {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket client;

        // Default port number
        // Both 80 and 8080 are common port numbers for local host services
        int portNumber = 8080;

        if(args.length >= 1)
        {
            portNumber = Integer.parseInt(args[0]);
        }

        try {
            server = new ServerSocket(portNumber);
        } catch (IOException ie) {
            System.out.println("Cannot open socket." + ie);
        }
        System.out.println("Serversocket is created " + server);

        // Wait for the data from the client and reply
        try{
            /* Listens for a connection to be made to the socket and accepts it
            This method blocks until a connection is made */
            System.out.println("Waiting for connect request...");
            client = server.accept();

            System.out.println("Connect request is accepted...");
            String clientHost = client.getInetAddress().getHostAddress();

            int clientPort = client.getPort();
            System.out.println("Client host = " + clientHost + "Client port = " + clientPort);

            // Read data from the client
            InputStream clientIn = client.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientIn));
            String msgFromClient = bufferedReader.readLine();
            System.out.println("Message recieved from client = " + msgFromClient);

            // Send response to the client
            if (msgFromClient != null && !msgFromClient.equalsIgnoreCase("bye"))
            {
                OutputStream clientOut = client.getOutputStream();
                PrintWriter printWriter = new PrintWriter(clientOut, true);
                String ansMsg = "Hello, " + msgFromClient;
                printWriter.println(ansMsg);
            }

            // Close sockets
            if (msgFromClient != null && msgFromClient.equalsIgnoreCase("bye"))
            {
                server.close();
                client.close();
            }
        } catch (IOException ie) {
            System.out.println("Unable to connect to client");
        }
    }
}

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.*;
import java.io.*;

public class ChatServer {
    private InetAddress ip; 
    private ServerSocket serverSocket;

    public ChatServer(String name) throws IOException {
        this.ip = InetAddress.getLocalHost();
        this.serverSocket = new ServerSocket(1234, 1, ip);
        System.out.printf("You have created a server at %s on port %d\n", ip.getHostAddress(), serverSocket.getLocalPort());
        try {
            while(!serverSocket.isClosed()) {
                System.out.println("Waiting for connection...");
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected!");
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            //
        }
    }

    public void closeServerSocket() {
        try {
            if(serverSocket!=null){
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

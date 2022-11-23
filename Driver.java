import java.util.Scanner;
import javax.sound.sampled.Port;
import java.net.Socket;

import java.io.IOException;
import java.net.*;

public class Driver {
    
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to BattleShip (Computer Network Final Project)");
        System.out.print("Please enter your name: ");
        String name = scan.nextLine();
        System.out.println("Welcome " + name);
        boolean isHost = false;
        boolean isChat = false;
        
        while (true) {
            System.out.print("Would you like to play or chat? (Enter 'play' or 'chat'): ");
            String type = scan.nextLine(); 
            if (type.equals("play")) {
                System.out.print("Would you like to host or connect to a game? (Enter 'host' or 'connect'): ");
                type = scan.nextLine(); 
                if (type.equals("host")) {
                    isHost = true;
                    break;
                } else if (type.equals("connect")) {
                    break;
                } else {
                    System.out.println("Error - please enter a valid option");
                }
            } else if (type.equals("chat")) {
                isChat = true;
                System.out.print("Would you like to host or connect to a chat? (Enter 'host' or 'connect'): ");
                type = scan.nextLine(); 
                if (type.equals("host")) {
                    isHost = true;
                    break;
                } else if (type.equals("connect")) {
                    break;
                } else {
                    System.out.println("Error - please enter a valid option");
                }
            } else {
                System.out.println("Error - please enter a valid option");
            }
            
        }

        if (isHost) {
            if (!isChat) {
                Server s = new Server(name);
            } else {
                try {
                    ChatServer server = new ChatServer(name);
                } catch (IOException e) {
                    //
                }
            }
        } else {
            if (!isChat) {
                System.out.print("Please enter the information for the game you wish to connect to (ip:port): ");
                String[] temp = scan.nextLine().split(":");
                String ip = temp[0];
                int port = Integer.parseInt(temp[1]);
                Client c = new Client(ip, port, name); 
            } else {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter your username for the group chat: ");
                String username = scanner.nextLine();
                System.out.print("Please enter the information for the game you wish to connect to (ip:port): ");
                String[] temp = scan.nextLine().split(":");
                String ip = temp[0];
                int port = Integer.parseInt(temp[1]);
                try {
                    Socket socket = new Socket(ip, port);
                    ClientChat client = new ClientChat(socket, username);
                    client.listenForMessage();
                    client.sendMessages();
                } catch (IOException e) {
                    //
                }
            }
            
        }    

        scan.close();
    }
}
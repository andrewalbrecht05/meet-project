import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientChatHandler implements Runnable {
    private ChatService chatService;

    private Scanner inMessage;

    private PrintWriter outMessage;

    private static int client_count = 0;

    private Socket clientSocket = null;
    public ClientChatHandler(Socket clientSocket, ChatService chatService) {
        try {
            client_count++;
            this.clientSocket = clientSocket;
            this.chatService = chatService;
            this.outMessage = new PrintWriter(clientSocket.getOutputStream());

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(String message){
        outMessage.println(message);
        outMessage.flush();
    }

    @Override
    public void run() {

        while (true) {
            chatService.sendMessagetoall("new client " + client_count);
            break;
        }
        while (true) {
            String message = inMessage.nextLine();
            System.out.println(message);
            chatService.sendMessagetoall(message);
        }

    }

    public void closeconnection(){
        chatService.removeClient(this);
        client_count--;
        chatService.sendMessagetoall(""+client_count);
    }

}

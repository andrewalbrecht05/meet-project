import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatService {
    private  int port = 0;
    private ArrayList<ClientChatHandler> clients = new ArrayList<>();
    public ChatService(int port){
        this.port = port;
    }
    public void startserver()throws Exception{
        try {
            if (port <= 1024) {
                throw new UninitializedPortExeception("port value  less than 1024");
            }
        }
        catch(UninitializedPortExeception e){
            e.printStackTrace();
        }

            ServerSocket ChatServer = new ServerSocket(port);
            Socket clientSocket = null;
        try {
            while (true) {
                clientSocket = ChatServer.accept();
                ClientChatHandler client = new ClientChatHandler(clientSocket, this);
                clients.add(client);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            clientSocket.close();
            ChatServer.close();
        }


    }
    public void sendMessagetoall(String message){
        for (ClientChatHandler temp : clients) {
            temp.sendMessage(message);
        }
    }

    public void removeClient(ClientChatHandler a){
        clients.remove(a);

    }

}

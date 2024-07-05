import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args){
       try {
           ServerSocket server = new ServerSocket(4000);
           while (true) {
               Socket socket = server.accept();
                new Thread(new Myserver(socket)).start();
           }

       }catch (IOException e) {
            e.printStackTrace();
       }
    }
}

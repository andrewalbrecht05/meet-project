import java.net.Socket;

public class Myserver implements Runnable{
    private Socket socket;
    public Myserver(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run(){
        System.out.println("hello user");
    }
}

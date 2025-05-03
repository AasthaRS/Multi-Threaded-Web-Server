import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

public Runnable getRunnable(){
    return new Runnable() {
        @Override
        public void run(){
            int port = 8080;
            try{
                InetAddress address = InetAddress.getByName("localhost");
                Socket socket = new Socket(address, port);
                PrintWriter toSever = new PrintWriter(socket.getOutputStream());
                BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                toSever.println("Hello from client");
                String line = fromServer.readLine();
                System.out.println("Response from the server is : " + line);
                toSever.close();
                fromServer.close();
                socket.close();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
    };
}

    public static void main(String[] args) {
        Client client = new Client();
        try{
            for(int i=1; i<100; i++){
                Thread thread = new Thread(client.getRunnable());
                thread.start();
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

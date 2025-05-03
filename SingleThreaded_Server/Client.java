import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public void run() throws UnknownHostException, IOException{
        int port = 8080;
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
    public static void main(String[] args) {
        try{
            Client client = new Client();
            client.run();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

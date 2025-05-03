import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;
import java.util.function.DoubleToIntFunction;

public class Server {

    // functional interface - first class citizen
    public Consumer<Socket> getConsumer(){

        // return new Consumer<Socket>(){
        //     @Override
        //     public void accept(Socket){
        //         try{
        //             PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
        //             toClient.println("Hello from the server");
        //             toClient.close();
        //             clientSocket.close();
    
        //         }
        //         catch(IOException ex){
        //             ex.printStackTrace();
        //         }
        //     }
        // };

        return (clientSocket)->{
            try{
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
                toClient.println("Hello from the server");
                toClient.close();
                clientSocket.close();

            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        };
    }

    public static void main(String[] args) {
        int port = 8080;
        Server server = new Server();
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
            System.out.println("Server is listening on port " + port);
            while(true){
                Socket acceptedConnection = serverSocket.accept();
                System.out.println("Connection accepted from client " + acceptedConnection.getRemoteSocketAddress());
                Thread thread = new Thread(()->server.getConsumer().accept(acceptedConnection));
                thread.start();
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
}

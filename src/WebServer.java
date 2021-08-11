import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    public static void main(String[] args) {

        try {

            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("ServerPort: " + serverSocket.getLocalPort());
            System.out.println("Running on thread: " + Thread.currentThread());
            System.out.println("---Logs---");

            while(!serverSocket.isClosed()) {

                Socket clientSocket = serverSocket.accept();
                System.out.println("Client from: " + clientSocket.getLocalSocketAddress());

               Thread thread = new Thread(new Client(clientSocket));
               thread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

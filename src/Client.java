import java.io.*;
import java.net.Socket;

public class Client implements Runnable {

    private Socket clientSocket;

    public Client(Socket socket) {
        this.clientSocket = socket;
    }

    public void request() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String request = bufferedReader.readLine();
            System.out.println("Running on thread: " + Thread.currentThread());
            System.out.println(request);
            System.out.println("");

            String[] holder = request.split("\\s");

            //makes root request return index page
            if(holder[1].equals("/")) {
                holder[1] = "/index.html";
            }
            
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            dataOutputStream.write(HttpFactory.makeHttp(holder[1]));
            dataOutputStream.write(HttpFactory.sendFile(holder[1]));

            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        request();

    }
}

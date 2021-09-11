package messenger;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream input = null;
    private DataInputStream input1 = null;
    private DataOutputStream outputStream = null;

    public Server (int port){
        try{
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for client ... ");

            socket = server.accept();

            System.out.println("Client accepted");
            input = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream())
            );
            input1 = new DataInputStream(System.in);
            outputStream = new DataOutputStream(socket.getOutputStream());
            String line = "";
            String response = "";
            while(!line.equals("Over")){
                try{
                    line = input.readUTF();
                    System.out.println("Client : " + line);
                    response = input1.readLine();
                    outputStream.writeUTF(response);
                }catch (IOException exception){
                    System.out.println(exception);
                }
            }

            socket.close();
            input.close();
            System.out.println("Closing connection");
        }catch (IOException exception) {
            System.out.println(exception);
        }
    }

    public static void main(String[] args) {
        Server server = new Server(3000);
    }
}

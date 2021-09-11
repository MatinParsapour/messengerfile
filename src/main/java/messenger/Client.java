package messenger;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private Socket socket = null;
    private DataInputStream input = null;
    private DataInputStream input1 = null;
    private DataOutputStream output = null;

    public Client(String address, int port) {
        try{
            socket = new Socket(address,port);
            System.out.println("Connected");

            input = new DataInputStream(System.in);

            input1 = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream())
            );
            output = new DataOutputStream(socket.getOutputStream());
        }catch (UnknownHostException exception){
            System.out.println(exception);
        }catch (IOException exception){
            System.out.println(exception);
        }
        String line = "";
        String serverLine = "";
        while(!line.equals("Over")){
            try{
                line = input.readLine();
                output.writeUTF(line);
                serverLine = input1.readUTF();
                System.out.println("Response : " + serverLine);
            }catch (IOException exception){
                System.out.println(exception);
            }
        }
        try{
            input.close();
            output.close();
            socket.close();
        }catch (IOException exception){
            System.out.println(exception);
        }
    }

    public static void main(String[] args) {
        Client client = new Client("192.168.43.242",3000);
    }
}

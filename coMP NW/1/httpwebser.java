import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import java.net.*;
import java.awt.Desktop;
import java.io.*;
public class SocketClient {
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        try{
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = new Socket(host.getHostName(), 9876);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = null;
        System.out.println("Client is running");
        System.out.println("Sending request to Socket Server");
        System.out.println("Enter the url");
        Scanner a=new Scanner(System.in);
        String s=a.nextLine();
            oos.writeObject(s);
            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Page downloaded by server "+message);
            Desktop.getDesktop().browse(new URL("file:///C:/Users/intel/Desktop/overview/networks/page.html").toURI());
            ois.close();
            oos.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
public class SocketServer {
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        ServerSocket server = new ServerSocket(9876);
        System.out.println("Server is running");
        do{
            System.out.println("Waiting for the client request");
            Socket socket = server.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message Received: " + message);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            download(message);
            oos.writeObject(message);
            ois.close();
            oos.close();
            socket.close();
            if(message.equalsIgnoreCase("exit")) break;
        }while(false);
        System.out.println("Shutting down Socket server!!");
        server.close();
    }
   public static void download(String urlString) throws IOException {
      URL url = new URL(urlString);
      try
      {
         BufferedReader reader =  new BufferedReader(new InputStreamReader(url.openStream()));         
         BufferedWriter writer = new BufferedWriter(new FileWriter("page.html"));       
        {      
         String line;
         while ((line = reader.readLine()) != null) {
            writer.write(line);
         }            
         System.out.println("Page downloaded.");
      }
   }
      catch(Exception e)
      {
         System.out.println(e);
      }
   }
}

import java.util.*;
import java.io.*;
import java.lang.*;
import java.net.*;
class tcpchatserver
{
    public static void main(String args[]) throws IOException
    {
        ServerSocket s = new ServerSocket(1506);
        Socket obj = s.accept();
        DataInputStream din = new DataInputStream(obj.getInputStream());
        DataOutputStream dout = new DataOutputStream(obj.getOutputStream());
        System.out.println("Server is Running...");
        while(true)
        {
           String str=din.readLine();
            if(str.equals("BYE"))
            {
                System.out.println("Terminated...");
                break;
            }
            System.out.println("Client: " + str);
            System.out.print("Server: ");
            Scanner a=new Scanner(System.in);
            String str1=a.nextLine();
            dout.writeBytes(str1+"\n");
        }
    }
}

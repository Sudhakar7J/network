import java.util.*;
import java.io.*;
import java.lang.*;
import java.net.*;
class tcpechoclient
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Socket sc = new Socket("127.0.0.1",156);
        DataInputStream din = new DataInputStream(sc.getInputStream());
        DataOutputStream dout = new DataOutputStream(sc.getOutputStream());
        System.out.println("Client is Running... Type 'BYE' to Quit");
            System.out.print("Client: ");
            Scanner a=new Scanner(System.in);
            String str=a.nextLine();
            dout.writeBytes(str+"\n");
            String str2 = din.readLine();
            System.out.println("Server: " + str2);
        }
    }
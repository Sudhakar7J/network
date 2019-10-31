import java.io.*;
import java.net.*;
class udpchatserver
{
    public static void main(String[] a) throws IOException
    {
        byte buf[] = new byte[1024];
        int cport = 789,sport=790;
        DatagramSocket serversocket = new DatagramSocket(sport);
        DatagramPacket dp = new DatagramPacket(buf,buf.length);
        BufferedReader dis = new BufferedReader(new InputStreamReader(System.in));
        InetAddress ia = InetAddress.getLocalHost();
        System.out.println("Server is Running...");
        while(true)
        {
            serversocket.receive(dp);
            String str = new String(dp.getData(), 0,dp.getLength());
            if(str.equals("BYE"))
            {
                System.out.println("Terminated...");
                break;
            }
            System.out.println("Client: " + str);
            System.out.print("Server: ");
            String str1 = new String(dis.readLine());
            buf = str1.getBytes();
            serversocket.send(new DatagramPacket(buf,str1.length(), ia, cport));
        }
    }
}

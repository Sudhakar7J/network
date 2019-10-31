import java.io.*;
import java.net.*;
class udpchatclient
{
    public static void main(String[] a) throws IOException
    {
        byte buf[] = new byte[1024];
        int cport = 789, sport = 790;
        DatagramSocket clientsocket = new DatagramSocket(cport);
        DatagramPacket dp = new DatagramPacket(buf, buf.length);
        BufferedReader dis = new BufferedReader(new
        InputStreamReader(System.in));
        InetAddress ia = InetAddress.getLocalHost();
        System.out.println("Client is Running... Type 'BYE' to Quit");
        while(true)
        {
            System.out.print("Client: ");
            String str = new String(dis.readLine());
            buf = str.getBytes();
            if(str.equals("BYE"))
            {
                System.out.println("Terminated...");
                clientsocket.send(new DatagramPacket(buf,str.length(), ia,sport));
                break;
            }
            clientsocket.send(new DatagramPacket(buf, str.length(), ia, sport));
            clientsocket.receive(dp);
            String str2 = new String(dp.getData(), 0,
            dp.getLength());
            System.out.println("Server: " + str2);
        }
    }
}


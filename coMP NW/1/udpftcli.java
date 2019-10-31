import java.net.*;
import java.io.*;
import java.util.*;
public class clientfile
{
            public static void main(String args[])throws Exception
            {         
                  try{
                        int s=0;
                        byte b[]=new byte[1024];
                        byte x[]=new byte[1024];
                        String aa;
                        System.out.println("Enter the filename having the content");
                        Scanner a=new Scanner(System.in);
                        String e=a.nextLine();
                        File ff=new File(e);
                         if(!ff.isFile())
                        {
                              b=null;
                              String bb="a";
                              b=bb.getBytes();
                              DatagramSocket dsoc=new DatagramSocket(1000);
                                    dsoc.send(new DatagramPacket(b,0,InetAddress.getLocalHost(),2000));
                        }
                        FileInputStream f=new FileInputStream(ff);
                        DatagramSocket dsoc=new DatagramSocket(1000);
                        if(ff.isFile())
                        {
                             int i=0;
                        while(f.available()!=0)
                        {
                                    s=f.read();
                                    b[i]=(byte)s;
                                    i++;
                        }               
                        f.close();
                        dsoc.send(new DatagramPacket(b,i,InetAddress.getLocalHost(),2000));
                        }
                     
            }
            catch(Exception e)
            {
                  System.out.println("File not exists");
            }
            }

}


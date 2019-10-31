import java.net.*;
import java.io.*;
import java.util.*;
public class serverfile
{
            public static void main(String args[])throws IOException
            {
                  try{
                        byte b[]=new byte[3072];
                        DatagramSocket dsoc=new DatagramSocket(2000);
                        System.out.println("Enter the filename to be copied");
                        Scanner a=new Scanner(System.in);
                        String e=a.nextLine();
                        File x=new File(e);
                        FileOutputStream f=new FileOutputStream(x);  
                        DatagramPacket dp=new DatagramPacket(b,b.length);
                        dsoc.receive(dp);
                        String str=(new String(dp.getData(),0,dp.getLength()));
                        if(!str.equals(""))
                        {
                                    System.out.println("The content in the file is:"+str);                  
                                    f.write(dp.getData());	
                                    f.close();
                                   System.out.println("File Content Copied");      
                        }
                        else
                        {
                                 f.close();
                                 System.out.println("File not exists in client side");
                               }
                         }
                                     catch(Exception e)
                                     {
                                          System.out.println("File Error");
                                     }                          
            }
}

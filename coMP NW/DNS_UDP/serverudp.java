import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.Desktop;
class serverudp {    
   public static void main(String args[]) throws Exception
   {          
      int j=0,i,flag=0;         
      String s=new String();         
      String n=new String();         
      String m="";          
      System.out.println("Domain Name\t\t\t\tIP Address");         
      String[] s1 = {"http://www.google.com", "http://nptel.ac.in", "http://www.ssn.edu.in"};          
      String[] s2 = {"http://173.194.203.106", "http://14.139.160.71", "http://182.75.25.233"};
      for( i=0;i<s1.length;i++){
         System.out.println("\n"+s1[i]+"\t\t\t"+s2[i]);          
      }
      DatagramSocket serverSocket = new DatagramSocket(9876);             
      byte[] receiveData = new byte[1024];            
      byte[] sendData = new byte[1024];
      DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);             
      serverSocket.receive(receivePacket);
      String sentence = new String(receivePacket.getData(),0,receivePacket.getLength());             
      System.out.println("RECEIVED IP/Domain Name: " +sentence);             
      m=sentence;
      for(i=0;i<s1.length;i++){
         s=s1[i];                   
         if(s.equals(sentence)){                    
            m=s2[i];
            flag=1;
         }        
         n=s2[i];               
         if(n.equals(sentence)){                    
            m=s1[i];
            flag=1;
         }
      }
      if(flag==1){
         Desktop.getDesktop().browse(new URL(sentence).toURI());
         InetAddress IPAddress = receivePacket.getAddress();
         int port = receivePacket.getPort();
         sendData = m.getBytes();
         DatagramPacket sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress, port);
         serverSocket.send(sendPacket);
      }
      else{
         InetAddress IPAddress = receivePacket.getAddress();
         int port = receivePacket.getPort();
         m="Not a valid domain name";
         sendData = m.getBytes();
         DatagramPacket sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress, port);
         serverSocket.send(sendPacket);
         System.exit(0);
      }
   }
}

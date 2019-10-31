import java.io.*;
import java.util.*;
import java.net.*;
public class macip{
    public static void main(String[] args){
        try{
            FileWriter ipFile = new FileWriter("ip.txt");
            BufferedWriter ipStream = new BufferedWriter(ipFile);
            FileWriter macFile = new FileWriter("mac.txt");
            BufferedWriter macStream = new BufferedWriter(macFile);
            List<String> ip = new ArrayList<String>();
            List<String> mac = new ArrayList<String>();
            final String alphabet = "0123456789ABCDEF";
            final int N = alphabet.length();
            String ip1="";
            String mac1="";
            Random r1 = new Random();
            for(int i=0;i<10;i++){
                    ip1=(r1.nextInt(256)+"."+r1.nextInt(256)+"."+r1.nextInt(256)+"."+r1.nextInt(256));
                    ip.add(ip1);
mac1=alphabet.charAt(r1.nextInt(N))+""+alphabet.charAt(r1.nextInt(N))+":"+alphabet.charAt(r1.nextInt(N))+""+alphabet.charAt(r1.nextInt(N))+":"+alphabet.charAt(r1.nextInt(N)) +""+alphabet.charAt(r1.nextInt(N))+":"+alphabet.charAt(r1.nextInt(N))+""+alphabet.charAt(r1.nextInt(N))+":"+alphabet.charAt(r1.nextInt(N))+""+alphabet.charAt(r1.nextInt(N))+":"
    +alphabet.charAt(r1.nextInt(N))+""+alphabet.charAt(r1.nextInt(N));
                    mac.add(mac1);
            }
             for (int k = 0; k < 10; k++){
                ipStream.write(ip.get(k)+",0");
                ipStream.newLine();
                macStream.write(mac.get(k)+",0");
                macStream.newLine();
            } 
            ipStream.close();
            macStream.close();
            System.out.println("Addresses Generated.");
        }catch(IOException i){
            System.out.println(i);
        }
    }
}

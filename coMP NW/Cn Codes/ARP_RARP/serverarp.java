import java.io.*;
import java.util.*;
import java.net.*;
import java.nio.file.Files; 
import java.nio.file.Path; 
import java.nio.file.Paths;

public class serverarp{
    public static void main(String args[]){
        try{
            String ip1="",mac1="";        
            FileReader ipFile = new FileReader("ip.txt");
            BufferedReader ipStream = new BufferedReader(ipFile);
            FileReader macFile = new FileReader("mac.txt");
            BufferedReader macStream = new BufferedReader(macFile);                          
            String ip = ipStream.readLine();
            String mac = macStream.readLine();            
            Random random = new Random();
            int randomInt = random.nextInt(10);
            int count=0;
            while(ip != null && mac != null){
                if (count == randomInt) {
                    String[] iwords=ip.split(",");
                    String[] mwords=mac.split(",");
                    ip1=iwords[0];
                    mac1=mwords[0];
                    if(iwords[1].equals("1") || mwords[1].equals("1")){
                        System.out.println("Already alllocated to other Node");
                        System.exit(0);
                    }
                    else{
                        System.out.println("\nAllocated IP Address\tAllocated MAC Address");
                        System.out.println("--------------------\t---------------------\n");
                        System.out.println(iwords[0]+"\t\t"+mwords[0]+"\n");
                        String a = iwords[0]+",1";
                        String b = mwords[0]+",1";                
                        Path ipath = Paths.get("ip.txt");
                        List<String> ilines = Files.readAllLines(ipath);
                        ilines.set(randomInt, a);
                        Files.write(ipath, ilines);                        
                        Path mpath = Paths.get("mac.txt");
                        List<String> mlines = Files.readAllLines(mpath);
                        mlines.set(randomInt, b);
                        Files.write(mpath, mlines);                                                                  
                        break;                            
                    }    
                }                    
                ip = ipStream.readLine();
                mac = macStream.readLine();
                count++;
            }               
           //ARP Simulation
            ServerSocket server = new ServerSocket(156);
            System.out.println("Waiting for client....");
            Socket socket = server.accept();
            System.out.println("Connected");
            while(true){
                DataInputStream din = new DataInputStream(socket.getInputStream());
                DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
                String str = din.readLine();
                if(str.equals(ip1)){
                    dout.writeBytes(mac1+"\n");
                    break;
                }
                else{
                    System.out.println("Address Not Found");
                    System.exit(0);
                }
                socket.close();
            }    
                //RARP Simulation
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            DataInputStream din = new DataInputStream(socket.getInputStream());
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            System.out.println("Enter the Physical address(MAC):");
            String str1=in.readLine();
            dout.writeBytes(str1+'\n');
            String str2=din.readLine();
            System.out.println("\n\n******CLIENT*****");
            System.out.println("\nIP Address\tMAC Address");
            System.out.println("--------------------\t---------------------\n");
            System.out.println(str2+"\t\t"+str1+"\n");
            ipStream.close();
            macStream.close();                        
            }
            catch(Exception e){
                System.out.println("Address Not Found");
                System.out.println(e);
            }
        }
}
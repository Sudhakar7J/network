import java.util.*;
import java.io.*;
import java.lang.*;
import java.net.*;
class tcpserverfile {
	public static void main(String[] args) throws Exception
	{
        try{
                ServerSocket s = new ServerSocket(1566);
                Socket obj = s.accept();
                System.out.println("Enter the filename having content");
                Scanner a=new Scanner(System.in);
                String b=a.nextLine();
				File vv= new File(b);
                FileReader fr1=new FileReader(vv);
                boolean exists = vv.exists();
				
                BufferedReader br1=new BufferedReader(fr1);
                DataInputStream din = new DataInputStream(obj.getInputStream());
                DataOutputStream dout = new DataOutputStream(obj.getOutputStream());
                String cd;
				
                dout.writeBytes(exists+"\n");
                if(exists==true)
                {
					while((cd=br1.readLine())!=null)
					{
						dout.writeBytes(cd+"\n");  
						System.out.println("The Contents in the File :"+cd);
					}
					String x=din.readLine();
					
					System.out.println("File Contents Copied");
					fr1.close();
					br1.close();
					obj.close();
                }
               }
            catch(Exception e)
            {
                  System.out.println("File not exists");
            }
	}
}
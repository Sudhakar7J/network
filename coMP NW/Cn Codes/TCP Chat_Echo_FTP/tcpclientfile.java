import java.util.*;
import java.io.*;
import java.lang.*;
import java.net.*;
class tcpclientfile {
	public static void main(String[] args) throws Exception
	{
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		    Socket sc = new Socket("127.0.0.1",1566);
            DataInputStream din = new DataInputStream(sc.getInputStream());
            DataOutputStream dout = new DataOutputStream(sc.getOutputStream());
            String vv=din.readLine();//content avail file//true
            System.out.println("Enter the filename to be copied");
            Scanner a=new Scanner(System.in);
            String b=a.nextLine();
			
            File zz= new File(b);
            FileWriter fw2 = new FileWriter(zz);
            String gg=din.readLine();
            fw2.write(gg+'\n');
            if(vv.equals(true))
            { 
				dout.writeBytes(gg+"\n");
				sc.close();
				fw2.close();
			}
			else
			{
				sc.close();
				fw2.close();
			}
    }
    catch(Exception e)
{
 System.out.println("File Error");   
	}
}
}

import java.util.*;
import java.io.*;
import java.net.*;

public class clibitstuff{
	public static void main(String[] args){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter message(in binary): ");
			String h = scan.nextLine();
			String dem[] = h.split("");
			int k = dem.length;
			int count=0;
			int i=0;
			String lol = "";
			for(i=0;i<k;i++){
				if(dem[i].equals("0")){
					count=count+1;
					lol+="0";
				}
				else{
					count=0;
					lol+="1";
				}
				if(count==5){
					lol+="1";
					count=0;
				}
			}
			System.out.println("Encoded Msg: "+lol);
			Socket sc = new Socket("127.0.0.1",126);
			DataOutputStream dout = new DataOutputStream(sc.getOutputStream());
			DataInputStream din = new DataInputStream(sc.getInputStream());
			dout.writeBytes(lol+"\n");
			String gg = din.readLine();
			sc.close();
		}catch(Exception e){System.out.println(e);}
}}
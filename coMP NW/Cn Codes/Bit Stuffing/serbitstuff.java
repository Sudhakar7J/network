import java.io.*;
import java.net.*;
import java.util.*;

public class serbitstuff{
	public static void main(String[] args){
		try{
			ServerSocket scl = new ServerSocket(126);
			Socket sc = scl.accept();
			DataInputStream din = new DataInputStream(sc.getInputStream());
			DataOutputStream dout = new DataOutputStream(sc.getOutputStream());
			String k ;
			k = din.readLine();
			System.out.println("Recieved msg"+k);
			String pip[] = k.split("");
			int n = pip.length;
			int count=0;
			int i=0;
			String org="";
			for(i=0;i<n;i++){
				if(pip[i].equals("0")){
					count++;
					org+="0";
				}
				else{
					count=0;
					org+="1";
				}
				if(count==5){
					i++;
				}
			}
			System.out.println("Original Message: "+org);
			dout.writeBytes(k+"\n");
		}catch(Exception e){System.out.println(e);}
}}
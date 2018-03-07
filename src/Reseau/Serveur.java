package Reseau;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
	private int port;
	private ServerSocket ss;
	
	private OutputStream os;
	private InputStream is;
	private Socket client;
	
	public Serveur(){
		try {
			ss=new ServerSocket(80);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void launchServeur() throws IOException{
      client = ss.accept();
      os=client.getOutputStream();
      is=client.getInputStream();
      System.out.println("Serveur start");
	}
	
	public void endServeur(){
		if(client!=null){
			try {
				os.close();
				is.close();
				os=null;
				is=null;
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void writeObject(String message) throws IOException{
		if(client!=null && os!=null){
			os.write(message.getBytes());
		}
	}
	public String readObject() throws IOException{
		byte arrayByte[]=new byte[2048];
		if(client!=null && os!=null){
			is.read(arrayByte);
			return new String(arrayByte);
		}
		return null;
	}
	
}

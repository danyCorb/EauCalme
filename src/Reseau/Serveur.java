package Reseau;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import Data.DataMain;

public class Serveur {
	private int port;
	private ServerSocket ss;
	
	private OutputStream os;
	private InputStream is;
	private Socket client;
	
	private boolean runThread=false;
	
	public Serveur(int port){
		this.port=port;
		try {
			ss=new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void startServeur(){
		runThread=true;
		 System.out.println("Serveur start on port "+port);
		Thread r=new Thread(){
			@Override
			public void run() {
				while(runThread){
					System.out.println("écoute");
					try {
						launchServeur();
						
						String rqt=readObject();
						if(rqt.length()>10){
							String write;
							while( (write=DataMain.getInstance().getFileRequeteGet().poll()) == null){
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
								}
							}
							PrintWriter pwout=new PrintWriter(os);
							pwout.println("HTTP/1.1 200 OK");
							
							writeObject(write);
							
						}
						
						endServeur();
					} catch (IOException e) {
					}
				}
			}
		};	
	}
	
	private void launchServeur() throws IOException{
      client = ss.accept();
      os=client.getOutputStream();
      is=client.getInputStream();
     
	}
	
	private void endServeur(){
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
	
	private void writeObject(String message) throws IOException{
		if(client!=null && os!=null){
			os.write(message.getBytes());
		}
	}
	private String readObject() throws IOException{
		byte arrayByte[]=new byte[2048];
		if(client!=null && os!=null){
			is.read(arrayByte);
			return new String(arrayByte);
		}
		return null;
	}
	
	private void endAll(){
		runThread=false;
		try {
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

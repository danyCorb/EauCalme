package Reseau;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	private Socket client;
	private OutputStream os;
	private InputStream is;
	
	public Client(String ip,int port) throws UnknownHostException, IOException{
		
		client = new Socket(ip, port);
	}
	
	public void closeConnexion() throws IOException{
		is.close();
		os.close();
		client.close();
		is=null;	os=null;	client=null;
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
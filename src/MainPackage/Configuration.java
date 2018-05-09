package MainPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Configuration {
	String ip;
	int portIntern;
	int portExtern;
	String path;
	boolean haveConfig=false;
	
	public static void generateConfigFile(String path,String ip, int portI,int portE){
		try {
			BufferedWriter br=new BufferedWriter(new FileWriter(path));
			br.write(ip+"\n");
			br.write(portI+"\n");
			br.write(portE+"\n");
			br.flush();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public Configuration(String path){
		this.path=path;
		try {
			BufferedReader br=new BufferedReader(new FileReader(path));
			ip=br.readLine();
			portIntern=Integer.valueOf(br.readLine());
			portExtern=Integer.valueOf(br.readLine());
			haveConfig=true;
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			ip=null;
			portIntern=0;
			portExtern=0;
			haveConfig=false;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public String getIp() {
		return ip;
	}
	
	public String getURLCOnnexion(){
		return "http://"+ip+":"+portExtern;
	}


	public int getPortIntern() {
		return portIntern;
	}


	public int getPortExtern() {
		return portExtern;
	}


	public String getPath() {
		return path;
	}


	public boolean isHaveConfig() {
		return haveConfig;
	}
	
	
	

}

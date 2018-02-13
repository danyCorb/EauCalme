package Data;

import InterfaceGraphique.Fenetre;

public class DataMain {
	
	private static DataMain INSTANCE =new DataMain();
	public static DataMain getInstance(){
		return INSTANCE;
    }
	
	public int mouseX;
	public int mouseY;
	
	private DataMain(){
		
	}
	
	public void getMousePosition(int x,int y){
		this.mouseX=x;
		this.mouseY=y;
	}

}

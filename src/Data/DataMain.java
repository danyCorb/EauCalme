package Data;

import InterfaceGraphique.Fenetre;

public class DataMain {
	
	private static DataMain INSTANCE =new DataMain();
	public static DataMain getInstance(){
		return INSTANCE;
    }
	
	int mouseX;
	int mouseY;
	
	private DataMain(){
		
	}

}

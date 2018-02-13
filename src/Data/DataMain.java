package Data;

public class DataMain {
	
	private static DataMain INSTANCE =new DataMain();
	public static DataMain getInstance(){
		return INSTANCE;
    }
	
	private DataKeyborad dataKeyboard;
	
	
	private DataMain(){
		dataKeyboard=new DataKeyborad();
	}


	public DataKeyborad getDataKeyboard() {
		return dataKeyboard;
	}


	public void setDataKeyboard(DataKeyborad dataKeyboard) {
		this.dataKeyboard = dataKeyboard;
	}
	
	
	
	
	
}

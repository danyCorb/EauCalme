package Data;

public class DataMain {
	
	private static DataMain INSTANCE =new DataMain();
	public static DataMain getInstance(){
		return INSTANCE;
    }
	
	private DataKeyborad dataKeyboard;
	private DataMouse dataMouse;
	
	
	private DataMain(){
		dataKeyboard=new DataKeyborad();
		dataMouse=new DataMouse();
	}


	public DataKeyborad getDataKeyboard() {
		return dataKeyboard;
	}


	public void setDataKeyboard(DataKeyborad dataKeyboard) {
		this.dataKeyboard = dataKeyboard;
	}


	public DataMouse getDataMouse() {
		return dataMouse;
	}


	public void setDataMouse(DataMouse dataMouse) {
		this.dataMouse = dataMouse;
	}
	
	
	
	
	
}

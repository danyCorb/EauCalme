package Data;

public class DataMain {
	
	
	private static DataMain INSTANCE =new DataMain();
	public static DataMain getInstance(){
		return INSTANCE;
    }
	
	private DataKeyborad dataKeyboard;
	private DataMouse dataMouse;
	private DataPlateau dataPlateau;
	private DataPionSelectione dataPionSelectione;
	private DataFenetre dataFenetre;
	private DataPlayer dataPlayer;
	
	private DataMain(){
		dataKeyboard=new DataKeyborad();
		dataMouse=new DataMouse();
		dataPlateau=new DataPlateau();
		dataPionSelectione=new DataPionSelectione();
		dataFenetre=new DataFenetre();
		dataPlayer=new DataPlayer();
	}


	public DataKeyborad getDataKeyboard() {
		return dataKeyboard;
	}


	public DataMouse getDataMouse() {
		return dataMouse;
	}



	public DataPlateau getDataPlateau() {
		return dataPlateau;
	}


	public DataPionSelectione getDataPionSelectione() {
		return dataPionSelectione;
	}


	public DataFenetre getDataFenetre() {
		return dataFenetre;
	}


	public DataPlayer getDataPlayer() {
		return dataPlayer;
	}

	
	
	
	
	
	
	
}

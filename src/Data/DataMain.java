package Data;

import java.util.PriorityQueue;
import java.util.Queue;

public class DataMain {


	private static final DataMain INSTANCE =new DataMain();
	public static DataMain getInstance(){
		return INSTANCE;
    }

	private DataKeyborad dataKeyboard;
	private DataMouse dataMouse;
	private DataPlateau dataPlateau;
	private DataPionSelectione dataPionSelectione;
	private DataFenetre dataFenetre;
	private DataPlayer dataPlayer;
	private DataTrolleSelection dataTrolleSelection;
	
	
	private Queue<String> fileRequeteGet;
	
	private DataMain(){
		dataKeyboard=new DataKeyborad();
		dataMouse=new DataMouse();
		dataPlateau=new DataPlateau();
		dataPionSelectione=new DataPionSelectione();
		dataFenetre=new DataFenetre();
		dataPlayer=new DataPlayer();
		dataTrolleSelection=new DataTrolleSelection();
		
		fileRequeteGet=new PriorityQueue<>();
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


	public DataTrolleSelection getDataTrolleSelection() {
		return dataTrolleSelection;
	}


	public Queue<String> getFileRequeteGet() {
		return fileRequeteGet;
	}


	





}

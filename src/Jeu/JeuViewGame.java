package Jeu;

import Data.DataMain;
import Entite.Deplacement;
import Entite.Position;
import MainPackage.EauCalmeMain;

public class JeuViewGame {
	
	private boolean looping=true;
	
	public JeuViewGame(){
		DataMain.getInstance().getDataViewGame().restartTour();
	}
	
	
	public void startJeuViewGame(){
		looping=true;
    	
		DataMain.getInstance().getDataPlateau().writeInitPlateau();
    	DataMain.getInstance().getDataViewGame().restartTour();
		DataMain.getInstance().getDataPartie().setMonTour(true);
	}
	
	
	public void endLoop(){
		EauCalmeMain.setMenuPanel();
	}
	
	public void actualiserPosition(){
		DataMain.getInstance().getDataPlateau().writeInitPlateau();
		int j;
		
		for(j=0;j<DataMain.getInstance().getDataViewGame().getTour();j++){
			Deplacement d=DataMain.getInstance().getDataViewGame().getDeplacementByNo(j);
			
			Position dep=DataMain.getInstance().getDataViewGame().getPositionById(d.getDeplacement_depart());
			Position arr=DataMain.getInstance().getDataViewGame().getPositionById(d.getDeplacement_arrive());
			
			DataMain.getInstance().getDataPlateau().bougerPiece(dep.getX(), dep.getY(), arr.getX(), arr.getY());
		}
		
		
	}
	
	

}

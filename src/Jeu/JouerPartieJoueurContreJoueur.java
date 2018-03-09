package Jeu;

import javax.sound.sampled.DataLine;

import Data.DataMain;
import MainPackage.EauCalmeMain;

public class JouerPartieJoueurContreJoueur {
	private boolean start;
	private int manche;
	
	public JouerPartieJoueurContreJoueur(){
		start=false;
		manche=1;
		DataMain.getInstance().getDataPlateau().writeInitPlateau();
		DataMain.getInstance().getDataPionSelectione().unselectPion();
	}
	
	public boolean startJeu(){
		if(!electionOrdreDeJeu()){
			return false;
		}
		
		return true;
	}
	
	private boolean electionOrdreDeJeu(){
		double rand=Math.random()*2000.0;
		String value = null;
		boolean connected=false;
		long timeStart=System.currentTimeMillis();
		
		DataMain.getInstance().getFileRequeteGet().offer(rand+"");
		
		while(System.currentTimeMillis()-timeStart<10000 && !connected)
		{
			try {
				value = EauCalmeMain.client.sendGet();
				connected=true;
			} catch (Exception e) {
				System.out.println("Connexion Error");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
				}
				//e.printStackTrace();
			}
			
		}
		if(connected){
			if(Double.parseDouble(value) > rand){
				System.out.println("Lautre commence "+Double.parseDouble(value));
				start=false;
			}
			else{
				System.out.println("Je commence "+Double.parseDouble(value));
				start=true;
			}
		}
		return connected;
	}
	
}

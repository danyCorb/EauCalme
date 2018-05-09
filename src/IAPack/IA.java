package IAPack;

import java.util.List;

import Data.DataMain;
import Jeu.Jeu;
import MainPackage.EauCalmeMain;

public class IA extends Thread{
	
	private static int IADelaySleep=30;
	private Jeu jeu;
	private boolean IARun =false;
	private ChooserCase cc;
	
	private int reponseDemandeAbandontAdv=0; // 0:neutre; 1:reponseNegative ;  2:reponsePositive

	public IA(){
		IARun=false;
		
	}
	
	public void startIA(){
		cc=new ChooserCase();
		jeu=(Jeu) EauCalmeMain.jl;
		this.start();
	}
	
	public void endIA(){
		this.destroy();
	}
	
	public void run(){
		IARun=true;
		DataMain dm=DataMain.getInstance();
		
		while(EauCalmeMain.isConnected){
			
			// attente du tour
			this.attendreMonTour(dm);
			
			// action lors de mon tour
			actionMonTour(dm);
			
			// IA sleep
			this.IASleeping();
		}
		IARun=false;
		System.exit(0);
	}
	
	
	
	public boolean setFinDeMancheOuDePartie(DataMain dm){
		boolean ret=dm.getDataPlateau().getPointNain()==0 || dm.getDataPlateau().getPointTrolle() == 0;
		if(dm.getDataPlateau().getPointNain()==0 || dm.getDataPlateau().getPointTrolle() == 0 || dm.getDataPartie().getCoupActuel()>500){ // test si fin
			jeu.startManche2();
		}
		return ret;
	}
	
	public void attendreMonTour(DataMain dm){
		while(!dm.getDataPartie().isMonTour()){
			
			setFinDeMancheOuDePartie(dm);
			
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void IASleeping(){
		try {
			Thread.sleep(IADelaySleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void actionMonTour(DataMain dm){
		while(dm.getDataPartie().isMonTour()){
			
			if(!setFinDeMancheOuDePartie(dm)){
				dm.getDataPionSelectione().unselectPion();
				
				// abandon ou non
				if(reponseDemandeAbandontAdv!=1){
					if(cc.proposerAbandont()){
						jeu.proposerAbandonIA();
						System.out.println("Abandon ajoute à l'envoi");
					}
				}
				
				int deplacement[][]=cc.jouerUneCase(jeu);
				jeu.selectedCase(deplacement[0][0], deplacement[0][1]);
				jeu.selectedCase(deplacement[1][0], deplacement[1][1]);
				
				
				
				
				
				// test troll selection
				if(dm.getDataTrolleSelection().isSelectionMode()){
					int j,k;
					for(j=0;j<dm.getDataPlateau().getPlateauDimension();++j){
						for(k=0;k<dm.getDataPlateau().getPlateauDimension();++k){
							jeu.selectedCase(j, k);
						}
					}
					jeu.endTrollSelection();
				}
			}
		}
	}
	
	public boolean getValideAbandonAdv(){
		boolean ret=false;
		if(cc.getChoixAccepterAbandonAdv()){
			ret=true;
		}
		
		
		if(ret==false){
			reponseDemandeAbandontAdv=1;
		}
		
		return ret;
	}
	
	
	
	public boolean isIARun() {
		return IARun;
	}
	
	public void mettreAJourLesCoef(){
		cc.coefNain.saveCoef();
		cc.coefTroll.saveCoef();
	}

}

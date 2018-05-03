package InterfaceGraphique;

import java.awt.Color;
import java.awt.Graphics;

import Data.DataMain;
import MainPackage.EauCalmeMain;

public class DrawingPanViewGame extends DrawingPan{
	public void paintComponent(Graphics g){
		DataMain dm=DataMain.getInstance();
		dm.getDataFenetre().setFontSize(g.getFontMetrics().getHeight());
		
		AffGrid(g);
		dessinerPionsSurLePlateau(g);
		
		
	
		
		// dessin du score
		dessinerScore(g, dm);
		
		
	}
	
	
	public void dessinerScore(Graphics g,DataMain dm){
		g.setColor(new Color(0,0,0));
		int pointT=dm.getDataPlateau().getPointTrolle();
		int pointN=dm.getDataPlateau().getPointNain();
		int avancement=dm.getDataViewGame().getTour();
		int nbDep=dm.getDataViewGame().getNbDeplacement();
		
		g.drawString("Trolle : "+pointT,0, g.getFontMetrics().getHeight());
		g.drawString("Nain : "+pointN,0, g.getFontMetrics().getHeight()*2);
		g.drawString( (avancement+1)+" / "+nbDep,0, g.getFontMetrics().getHeight()*3);
	}
	
	
}

package InterfaceGraphique;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Data.DataMain;
import Data.Pion;
import Jeu.Jeu;
import MainPackage.EauCalmeMain;

public class DrawingPan extends JPanel{
	
	public void paintComponent(Graphics g){
		DataMain dm=DataMain.getInstance();
		dm.getDataFenetre().setFontSize(g.getFontMetrics().getHeight());
		
		AffGrid(g);
		dessinerPionsSurLePlateau(g);
		dessinerCaseActuel(g,dm.getDataKeyboard().getX(),dm.getDataKeyboard().getY());
		
		
		// dessin des deplacement possibles
		if(EauCalmeMain.jl.checkIsTrayCase(dm.getDataPionSelectione().getX(), dm.getDataPionSelectione().getY()) && !dm.getDataTrolleSelection().isSelectionMode()){
			dessinerPionSelcetionne(g, dm.getDataPionSelectione().getX(), dm.getDataPionSelectione().getY());
			pionMove(g, dm.getDataPionSelectione().getCaseDispoDeplacement()[0], dm.getDataPionSelectione().getCaseDispoDeplacement()[1]);
			dessinerCaseDeplacementSpecial(g,dm.getDataPionSelectione().getCaseDeplacementSpecial());
		}
		else if(dm.getDataTrolleSelection().isSelectionMode()){ // trolle mode
			dessinerTrolleBoutonEtCases(g, dm);
		}
		
		// dessin du score
		dessinerScore(g, dm);
		
		dessinerBoutonAbandonner(g);
		
	}
	
	public void dessinerPionsSurLePlateau(Graphics g){
		for(int j=0;j<15;j++){
			for(int k=0;k<15;k++){
				Pion p=DataMain.getInstance().getDataPlateau().getPionInCase(j, k);
				if(p==Pion.Nain)
					AfficheNain(g, j, k);
				else if(p==Pion.Trolle)
					AfficheTroll(g, j, k);
			}
		}
	}
	
	public void AffGrid(Graphics g){
		double Wscreen = this.getWidth();
		double Hscreen = this.getHeight();
		double Wcube = Wscreen/15.0;
		double Hcube = Hscreen/15.0;
		boolean marTrue = false;
		g.setColor(new Color(0,255,255));	    
		for(int i = 0; i <= 14; i++)
	    {
			for(int k = 0; k <= 14; k++)
			{
				if(marTrue)
				{
					g.setColor(new Color(223,208,176));
					marTrue = false;
				}
				else{
					g.setColor(new Color(254,238,208));
					marTrue = true;
				}
				g.fillRect((int)(i*Wcube), (int)(k*Hcube),(int)Wcube+1, (int)Hcube+1);
				
			}
	    }
		//création variable
		g.setColor(new Color(255,255,255));
		int t = 3;
		double triangex = (Wscreen/15)*5;
		double triangey = (Hscreen/15)*5;
		double triangex2 = (Wscreen/15)*10;
		double triangey2 = (Hscreen/15)*10;
		//triangle 1
		int xt1[] = {0, (int)triangex, 0};
		int yt1[] = {0, 0, (int)triangey};
		g.fillPolygon(xt1, yt1, t);
		//triangle 2
		int xt2[] = {(int)triangex2, (int)Wscreen, (int)Wscreen};
		int yt2[] = {0, 0, (int)triangey};
		g.fillPolygon(xt2, yt2, t);
		//triangle 3
		int xt3[] = {(int)triangex2, (int)Wscreen, (int)Wscreen};
		int yt3[] = {(int)Hscreen, (int)Hscreen, (int)triangey2};
		g.fillPolygon(xt3, yt3, t);
		//triangle 4
		int xt4[] = {0, 0, (int)triangex};
		int yt4[] = {(int)triangey2, (int)Hscreen, (int)Hscreen};
		g.fillPolygon(xt4, yt4, t);
		//création de la pierre de thud
		g.setColor(new Color(0,0,0));
		double pierrex = (Wscreen/15)*7;
		double pierrey = (Hscreen/15)*7;
		double pierrew = Wscreen/15;
		double pierreh = Hscreen/15;
		g.fillOval((int)pierrex, (int)pierrey, (int)pierrew, (int)pierreh);
		
		// dessin d'un rectangle gris si ce n'est pas le tour
		if(!EauCalmeMain.jl.getTour()){
			g.setColor(new Color(128,128,128,128));
			g.fillRect(0,0, (int)Wscreen, (int)Hscreen);
		}
			
		
	}
	public void AfficheTroll (Graphics g, int x, int y){
		g.setColor(new Color(255,0,0));
		double Wscreen = this.getWidth();
		double Hscreen = this.getHeight();
		double trollw = Wscreen/15;
		double trollh = Hscreen/15;
		g.fillOval((int)(x*trollw)+2, (int)(y*trollh)+2, (int)trollw-4, (int)trollh-4);
	}
	public void AfficheNain (Graphics g, int x, int y){
		g.setColor(new Color(0,0,255));
		double Wscreen = this.getWidth();
		double Hscreen = this.getHeight();
		double nainw = Wscreen/15;
		double nainh = Hscreen/15;
		g.fillOval((int)(x*nainw)+2, (int)(y*nainh)+2, (int)nainw-4, (int)nainh-4);
	}
	public void posPion (Graphics g, int x ,int y, Color c){
		g.setColor(c);
		double Wscreen = this.getWidth();
		double Hscreen = this.getHeight();
		double casew = Wscreen/15;
		double caseh = Hscreen/15;
		g.fillRect((int) (x*casew), (int) (y*caseh), (int)casew+1, (int)caseh+1);
	}
	public void pionMove (Graphics g, int x[], int y []){
		for (int i = 0; i<x.length; i++){
			posPion(g, x[i], y[i], new Color(255, 255 ,0, 75));
		}
	}
	
	public void dessinerCaseActuel(Graphics g,int x,int y){
		posPion(g, x, y, new Color(128,128,255,128));
	}
	public void dessinerPionSelcetionne(Graphics g,int x,int y){
		posPion(g, x, y, new Color(255, 0 ,0, 75));
	}
	
	public void dessinerCaseDeplacementSpecial(Graphics g,int cases[][]){
		int j;
		for(j=0;j<cases.length;j++){
			posPion(g,cases[j][0], cases[j][1], new Color(255,128,0,128));
		}
	}
	
	public void dessinerScore(Graphics g,DataMain dm){
		g.setColor(new Color(0,0,0));
		int pointT=dm.getDataPlateau().getPointTrolle();
		int pointN=dm.getDataPlateau().getPointNain();
		g.drawString("Trolle : "+pointT,0, g.getFontMetrics().getHeight());
		g.drawString("Nain : "+pointN,0, g.getFontMetrics().getHeight()*2);
	}
	
	public void dessinerBoutonAbandonner(Graphics g){
		g.setColor(new Color(100,100,100));
		g.fillRect(0, g.getFontMetrics().getHeight()*2+2, 80,  g.getFontMetrics().getHeight()+2);
		g.setColor(new Color(255,255,255));
		g.drawString("Abandonner",0, g.getFontMetrics().getHeight()*3);
	}
	
	public void dessinerTrolleBoutonEtCases(Graphics g, DataMain dm){
		g.setColor(new Color(100,100,100));
		g.fillRect(0, g.getFontMetrics().getHeight()*3+2, 80,  g.getFontMetrics().getHeight()+2);
		g.setColor(new Color(255,255,255));
		g.drawString("Valider la prise",0, g.getFontMetrics().getHeight()*4);
		
		for(int j=0;j<3;j++){
			for(int k=0;k<3;k++){
				if(dm.getDataPlateau().getPionInCase(j+dm.getDataTrolleSelection().getCaseX()-1, k+dm.getDataTrolleSelection().getCaseY()-1)==Pion.Nain){
					posPion(g,j+dm.getDataTrolleSelection().getCaseX()-1 , k+dm.getDataTrolleSelection().getCaseY()-1, new Color(230,128,0,100));
				}
			}
		}
		
		for(int j=0;j<dm.getDataTrolleSelection().getListeCase().size();j++){
			posPion(g,dm.getDataTrolleSelection().getListeCase().get(j)[0] , dm.getDataTrolleSelection().getListeCase().get(j)[1], new Color(230,128,0,128));
		}
		
	}
	
}

package InterfaceGraphique;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Data.DataMain;

public class DrawingPan extends JPanel{
	
	public void paintComponent(Graphics g){
		DataMain dm=DataMain.getInstance();
		AffGrid(g);
		posPion(g, DataMain.getInstance().getDataPionSelectione().getX(), DataMain.getInstance().getDataPionSelectione().getX(), new Color(255, 0 ,0, 75));
		g.setColor(new Color(0,255,255));
		pionMove(g , new int []{1,3,7},  new int []{1,3,7});
		//g.setColor(new Color(0,255,255));
		//g.fillRect(50, 50, this.getWidth(), 50);
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
	}
	public void AfficheTroll (Graphics g, int x, int y){
		g.setColor(new Color(255,0,0));
		double Wscreen = this.getWidth();
		double Hscreen = this.getHeight();
		double trollw = Wscreen/15;
		double trollh = Hscreen/15;
		g.fillOval(x, y, (int)trollw, (int)trollh);
	}
	public void AfficheNain (Graphics g, int x, int y){
		g.setColor(new Color(0,0,255));
		double Wscreen = this.getWidth();
		double Hscreen = this.getHeight();
		double nainw = Wscreen/15;
		double nainh = Hscreen/15;
		g.fillOval(x, y, (int)nainw, (int)nainh);
	}
	public void posPion (Graphics g, int x ,int y, Color c){
		g.setColor(c);
		double Wscreen = this.getWidth();
		double Hscreen = this.getHeight();
		double casew = Wscreen/15;
		double caseh = Hscreen/15;
		g.fillRect((int) (x*casew), (int) (y*caseh), (int)casew, (int)caseh);
	}
	public void pionMove (Graphics g, int x[], int y []){
		for (int i = 0; i<x.length; i++){
			posPion(g, x[i], x[i], new Color(255, 255 ,0, 75));
		}
	}
	
	
}

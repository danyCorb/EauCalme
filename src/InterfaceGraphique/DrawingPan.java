package InterfaceGraphique;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Data.DataMain;

public class DrawingPan extends JPanel{
	
	public void paintComponent(Graphics g){
		DataMain dm=DataMain.getInstance();
		AffGrid(g);
		
		g.setColor(new Color(0,255,255));
		g.fillRect(dm.mouseX, dm.mouseY, 50, 50);
		//g.setColor(new Color(0,255,255));
		//g.fillRect(50, 50, this.getWidth(), 50);
	}
	public void AffGrid(Graphics g){
		double Wscreen = this.getWidth();
		double Hscreen = this.getHeight();
		double Wcube = Wscreen/15.0;
		double Hcube = Hscreen/15.0;
		boolean marTrue = false;
		for(int i = 0; i <= 14; i++)
	    {
			for(int k = 0; k <= 14; k++)
			{
				if(marTrue)
				{
					g.setColor(new Color(0,255,255));
					marTrue = false;
				}
				else{
					g.setColor(new Color(0,255,0));
					marTrue = true;
				}
				g.fillRect((int)(i*Wcube), (int)(k*Hcube),(int)Wcube+1, (int)Hcube+1);
				
			}
	    }

	}
}

package InterfaceGraphique;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Data.DataMain;

public class DrawingPan extends JPanel{
	
	public void paintComponent(Graphics g){
		DataMain dm=DataMain.getInstance();
		
		g.setColor(new Color(0,255,255));
		g.fillRect(dm.mouseX, dm.mouseY, 50, 50);
	}
}

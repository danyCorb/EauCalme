package InterfaceGraphique;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawingPan extends JPanel{
	public void paintComponent(Graphics g){
		g.setColor(new Color(0,255,255));
		g.fillRect(50, 50, 50, 50);
	}
}

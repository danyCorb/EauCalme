package Events;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Data.DataMain;
import Jeu.Jeu;

public class MouseEvent implements MouseListener,MouseMotionListener{
	
	
	/*********************************
	 * Mouse Button
	 *********************************/
	@Override
	public void mouseClicked(java.awt.event.MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent arg0) {
		int selectedCase[] = selectCase(arg0, DataMain.getInstance().getDataFenetre().getW(), DataMain.getInstance().getDataFenetre().getH());
		Jeu.selectedCase(selectedCase[0], selectedCase[1]);
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/************************
	 *	Mouse motion 
	*************************/
	@Override
	public void mouseDragged(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(java.awt.event.MouseEvent arg0) {
		
	}
	
	
	public int[] selectCase(java.awt.event.MouseEvent arg0, int widthScreen, int heightScreen) {
		
		int a;
		int b;
		
		int posX=arg0.getX();
		int posY=arg0.getY();
		
		a = posX*15/widthScreen;
		b = posY*15/heightScreen;
		
		int selectedCase[] = {a, b};
		
		return selectedCase	;
	}
}

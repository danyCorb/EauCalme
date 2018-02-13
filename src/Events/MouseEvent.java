package Events;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Data.DataMain;

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
		System.out.println("click at:"+ arg0.getX()+" "+arg0.getY());
		DataMain.getInstance().getMousePosition( arg0.getX(), arg0.getY());
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
		// TODO Auto-generated method stub
		
	}
	
}

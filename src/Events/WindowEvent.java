package Events;

import java.awt.event.WindowListener;

import InterfaceGraphique.Fenetre;

public class WindowEvent  implements WindowListener{
	Fenetre f;
	public WindowEvent(Fenetre f){
		this.f=f;
	}
	
	@Override
	public void windowActivated(java.awt.event.WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(java.awt.event.WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(java.awt.event.WindowEvent e) {
		f.closeWindow();
	}

	@Override
	public void windowDeactivated(java.awt.event.WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(java.awt.event.WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(java.awt.event.WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(java.awt.event.WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

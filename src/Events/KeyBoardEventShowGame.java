package Events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Data.DataMain;
import MainPackage.EauCalmeMain;

public class KeyBoardEventShowGame implements KeyListener{
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		DataMain donnees = DataMain.getInstance();
		
		switch(arg0.getKeyCode()){
			case 37:
				donnees.getDataViewGame().retourTour();
				EauCalmeMain.jvg.actualiserPosition();
				break;
			case 39:
				donnees.getDataViewGame().nextTour();
				EauCalmeMain.jvg.actualiserPosition();
				break;
			case 32: // espace
				EauCalmeMain.jvg.endLoop();
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
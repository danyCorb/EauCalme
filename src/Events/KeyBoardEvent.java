package Events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Data.DataMain;
import Jeu.Jeu;

public class KeyBoardEvent implements KeyListener{
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		DataMain donnees = DataMain.getInstance();
		
		switch(arg0.getKeyCode()){
			case 37:
				donnees.getDataKeyboard().setX(donnees.getDataKeyboard().getX()-1);
				break;
			case 38:
				donnees.getDataKeyboard().setY(donnees.getDataKeyboard().getY()-1);
				break;
			case 39:
				donnees.getDataKeyboard().setX(donnees.getDataKeyboard().getX()+1);
				break;
			case 40:
				donnees.getDataKeyboard().setY(donnees.getDataKeyboard().getY()+1);
				break;
			case 10:
				Jeu.selectedCase(donnees.getDataKeyboard().getX(), donnees.getDataKeyboard().getY());
				break;
			case 32: // espace
				Jeu.endTrollSelection();
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

package Events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardEvent implements KeyListener{
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println("Key down:"+arg0.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

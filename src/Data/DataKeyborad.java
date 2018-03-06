package Data;

import Jeu.Jeu;

public class DataKeyborad {
	private int x;
	private int y;
	private boolean spacePress;
	
	
	public DataKeyborad(){
		x=5;
		y=0;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		if(Jeu.checkIsTrayCase(x, this.y))
			this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		if(Jeu.checkIsTrayCase(this.x, y))
			this.y = y;
	}
	public boolean isSpacePress() {
		return spacePress;
	}
	public void setSpacePress(boolean spacePress) {
		this.spacePress = spacePress;
	}
	
	
	
	

}

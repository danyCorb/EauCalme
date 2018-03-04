package Data;

public class DataPionSelectione {
	private int x,y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void unselectPion(){x=0; y=0;}

	public boolean selectedPion(){return x!=0 || y!=0;}

}

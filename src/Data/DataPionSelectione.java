package Data;

public class DataPionSelectione {
	private int x,y;
	private int[][] caseDispoDeplacement;
	private int[][] caseDeplacementSpecial;

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

	public void unselectPion(){x=0; y=0; caseDispoDeplacement=null;caseDeplacementSpecial=null;}

	public boolean selectedPion(){return x!=0 || y!=0;}

	public int[][] getCaseDispoDeplacement() {
		return caseDispoDeplacement;
	}

	public void setCaseDispoDeplacement(int[][] caseDispoDeplacement) {
		this.caseDispoDeplacement = caseDispoDeplacement;
	}

	public int[][] getCaseDeplacementSpecial() {
		return caseDeplacementSpecial;
	}

	public void setCaseDeplacementSpecial(int[][] caseDeplacementSpecial) {
		this.caseDeplacementSpecial = caseDeplacementSpecial;
	}
	
	
	
}

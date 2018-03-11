package Data;

public class DataPionSelectione {
	private int x,y;
	private int[][] caseDispoDeplacement; // [2][j]
	private int[][] caseDeplacementSpecial; // [j][2]

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
	
	public boolean estUneCasePourDeplacement(int x,int y){
		if(this.selectedPion()){
			for(int j=0;j<caseDispoDeplacement[0].length;j++){
				if(caseDispoDeplacement[0][j]==x && caseDispoDeplacement[1][j]==y)
					return true;
			}
			for(int j=0;j<caseDeplacementSpecial.length;j++){
				if(caseDeplacementSpecial[j][0]==x && caseDeplacementSpecial[j][1]==y)
					return true;
			}
		}
		return false;
	}
	
	
	
}

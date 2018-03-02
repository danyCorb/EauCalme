package Data;

public class DataPlateau {
	Pion plateau[][];
	
	public DataPlateau(){
		plateau= new Pion[15][];
		plateau[0]=new Pion[5];
		plateau[1]=new Pion[7];
		plateau[2]=new Pion[9];
		plateau[3]=new Pion[11];
		plateau[4]=new Pion[13];
		plateau[5]=new Pion[15];
		plateau[6]=new Pion[15];
		plateau[7]=new Pion[15];
		plateau[8]=new Pion[15];
		plateau[9]=new Pion[15];
		plateau[10]=new Pion[13];
		plateau[11]=new Pion[11];
		plateau[12]=new Pion[9];
		plateau[13]=new Pion[7];
		plateau[14]=new Pion[5];
		
		// set Nain
		for(int j=0;j<15;j++){
			if(j!=7){
				if(j==0||j==14){
					plateau[j][0]=Pion.Nain;
					plateau[j][1]=Pion.Nain;
					plateau[j][plateau[j].length-1]=Pion.Nain;
					plateau[j][plateau[j].length-2]=Pion.Nain;
				}
				else{
					plateau[j][0]=Pion.Nain;
					plateau[j][plateau[j].length-1]=Pion.Nain;					
				}
			}
		}
	}
	
	public void PrintTable(){
		for(int j=0;j<plateau.length;j++){
			for(int k=0;k<plateau[j].length;k++)
			{
				System.out.print(plateau[j][k]+" ");
			}
			System.out.println("");
		}
	}

	public Pion getPionInCase(int x, int y){

		for (int j=0; j<5; j++){
			if (y==j && x<5-j || y==j && x>9+j){
				return Pion.Null;
			}
		}

		for (int j=0; j<5; j++){
			if (y==j+10 && x<j+1 || y==j+10 && x>14-j){
				return Pion.Null;
			}
		}

		if (x==5 && y==0){
			return plateau[0][0];
		}else if (x==6 && y==0){
			return plateau[0][1];
		}else if (x==4 && y==1){
			return plateau[1][0];
		}else if (x==3 && y==2){
			return plateau[2][0];
		}

		for (int j=0; j<5; j++){
			for (int k=0; k<5; k++){
				if (x==j+5 && y==0){
					return plateau[0][j];
				}
			}
		}

	}

}

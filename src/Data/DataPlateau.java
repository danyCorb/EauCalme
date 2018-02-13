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

}

package IAPack;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import MainPackage.EauCalmeMain;

public class Coefficient {
	
	private static double coefTestValueMin=-10;
	private static double coeftestValueIncrement=0.5;
	private static double coeftestValueMax=10;
	
	private double coefRapprochementEnemy=coefTestValueMin;
	private double coefRapprochementAllie=coefTestValueMin;
	private double coefRapprocheDuCentre=coefTestValueMin;
	private double coefNainOuTrollPris=coefTestValueMin;
	private int scoreLimitAbandon=0;
	
	private double coefRapprochementEnemyTest=Math.random()*(coeftestValueMax-coefTestValueMin)+coefTestValueMin;
	private double coefRapprochementAllieTest=Math.random()*(coeftestValueMax-coefTestValueMin)+coefTestValueMin;
	private double coefRapprocheDuCentreTest=Math.random()*(coeftestValueMax-coefTestValueMin)+coefTestValueMin;
	private double coefNainOuTrollPrisTest=Math.random()*(coeftestValueMax-coefTestValueMin)+coefTestValueMin;
	private int scoreLimitAbandonTest=(int) (Math.random()*(20)-10);
	
	private boolean isTestCoef=false;
	
	
	private String path;
	
	public Coefficient(String path){
		this.path=path;
		loadCoef();
		System.out.println(EauCalmeMain.configuration.getPortExtern());
		isTestCoef = EauCalmeMain.configuration.getPortExtern()==654 ? true : false;
	}

	public double getCoefRapprochementEnemy() {
		if(!isTestCoef)
			return coefRapprochementEnemy;
		return coefRapprochementEnemyTest;
	}

	

	public double getCoefRapprochementAllie() {
		if(!isTestCoef)
			return coefRapprochementAllie;
		return coefRapprochementAllieTest;
	}

	

	public double getCoefRapprocheDuCentre() {
		if(!isTestCoef)
			return coefRapprocheDuCentre;
		return coefRapprocheDuCentreTest;
	}

	

	public double getCoefNainOuTrollPris() {
		if(!isTestCoef)
			return coefNainOuTrollPris;
		return coefNainOuTrollPrisTest;
	}


	
	public int getScoreLimitAbandon() {
		if(this.isTestCoef)
			return scoreLimitAbandonTest;
		return scoreLimitAbandon;
	}

	public void saveCoef(){
		if(isTestCoef){
			coefRapprochementEnemy=coefRapprochementEnemyTest;
			coefRapprochementAllie=coefRapprochementAllieTest;
			coefRapprocheDuCentre=coefRapprocheDuCentreTest;
			coefNainOuTrollPris=coefNainOuTrollPrisTest;	
			scoreLimitAbandon=scoreLimitAbandonTest;	
		}
		
			
			
		
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(this.path);
			DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
		    outStream.writeDouble(coefRapprochementEnemy);
		    outStream.writeDouble(coefRapprocheDuCentre);
		    outStream.writeDouble(coefRapprochementAllie);
		    outStream.writeDouble(coefNainOuTrollPris);
		    outStream.writeInt(scoreLimitAbandon);
		    
		    outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	public void loadCoef(){
	    try {
	    	FileInputStream fis = new FileInputStream(this.path);
		    DataInputStream reader = new DataInputStream(fis);
		    
		    coefRapprochementEnemy=reader.readDouble();
			coefRapprocheDuCentre=reader.readDouble();
			coefRapprochementAllie=reader.readDouble();
		    coefNainOuTrollPris=reader.readDouble();
		    scoreLimitAbandon=reader.readInt();
		    
		    
		    
		    reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(this.path);
				DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
			    outStream.writeDouble(coefRapprochementEnemy);
			    outStream.writeDouble(coefRapprocheDuCentre);
			    outStream.writeDouble(coefRapprochementAllie);
			    outStream.writeDouble(coefNainOuTrollPris);
			    outStream.writeInt(scoreLimitAbandon);
			    
			    outStream.close();
			} catch (IOException ep) {
				ep.printStackTrace();
			}  
		}
	}
	
	
	
	
}

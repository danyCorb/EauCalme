package Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataPlayer {
	private String nom;
	private String saveUserFile="Save.txt";
	
	public DataPlayer(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(saveUserFile));
			nom=reader.readLine();
			reader.close();
		} catch (IOException e) {
			nom="";
		}
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	public void saveName()
	{
		try {
			BufferedWriter bf=new BufferedWriter(new FileWriter(saveUserFile));
			bf.write(this.nom);
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}

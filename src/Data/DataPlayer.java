package Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import DAO.JoueurDAO;
import DAO.MainDAO;
import Entite.Joueur;

public class DataPlayer {
	private String nom;
	private String saveUserFile="Save.txt";
	private Joueur jo;
	
	public DataPlayer(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(saveUserFile));
			nom=reader.readLine();
			reader.close();
			System.out.println("get "+nom);
			jo=JoueurDAO.getJoueurByName(nom);
			
		} catch (IOException e) {
			nom="";
			jo=null;
		}
	}

	public String getNom() {
		return nom;
	}
	public int getId(){
		return jo.getId();
	}

	public void setNom(String nom) {
		if(nom.compareTo(this.nom)!=0 || jo==null){
			jo=JoueurDAO.createNewJoueur(nom);
		}
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

	public Joueur getJoueur() {
		return jo;
	}
	

}

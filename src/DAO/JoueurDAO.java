package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import Entite.Joueur;

public class JoueurDAO {
	
	public static void insertJoueur(Joueur j){
		MainDAO.getInstance().insert("joueur", new String[][]{
			{"nom",j.getNom()}			
		});
	}
	public static Joueur getJoueurByName(String name){
		try {
			ResultSet rs=MainDAO.getInstance().getRequestvalue("Select * FROM joueur WHERE nom='"+name+"';");
			if(rs.next()){
				return new Joueur(rs.getInt("id"),rs.getString("nom"));
			}
		} catch (SQLException e) {
		}
		return null;
	}
	
	public static Joueur getJoueurById(int id){
		try {
			ResultSet rs=MainDAO.getInstance().getRequestvalue("Select * FROM joueur WHERE id='"+id+"';");
			if(rs.next()){
				return new Joueur(rs.getInt("id"),rs.getString("nom"));
			}
		} catch (SQLException e) {
		}
		return null;
	}
	
	public static Joueur createNewJoueur(String nom){
		insertJoueur(new Joueur(0,nom));
		return getJoueurByName(nom);
	}
	
	
	public static Joueur getIA(){
		return getJoueurByName("IA");
	}
	
}

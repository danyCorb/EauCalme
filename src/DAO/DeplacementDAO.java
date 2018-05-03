package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entite.Deplacement;
import Entite.Partie;
import Entite.Position;

public class DeplacementDAO {
	
	public static void addDeplacement(Deplacement d){
		MainDAO.getInstance().insert("deplacement ", new String[][]
		{
			{"nb_coup",""+d.getNb_coup()},
			{"deplacement_depart",""+d.getDeplacement_depart()},
			{"deplacement_arrive",""+d.getDeplacement_arrive()}
		});
		
	}
	
	public static Deplacement getDeplacementById(int id){
	int deplacementId=id;
		
		try {
			ResultSet rs=MainDAO.getInstance().getRequestvalue("Select * FROM deplacement WHERE id="+deplacementId+";");
			Deplacement d=null;
			if(rs.next()){
				d=new Deplacement(rs.getInt("id"),rs.getInt("deplacement_depart"),rs.getInt("deplacement_arrive"),rs.getInt("nb_coup"));
			}
			rs.close();
			return d;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public static Deplacement getDeplacementId(Deplacement d){
		try {
			ResultSet rs=MainDAO.getInstance().getRequestvalue("Select * FROM deplacement WHERE nb_coup="+d.getNb_coup()+" AND deplacement_depart="+d.getDeplacement_depart()+" AND deplacement_arrive="+d.getDeplacement_arrive()+" order by nb_coup DESC ;");
			Deplacement ret=null;
			if(rs.next()){
				ret=new Deplacement(rs.getInt("id"),rs.getInt("deplacement_depart"),rs.getInt("deplacement_arrive"),rs.getInt("nb_coup"));
			}
			rs.close();
			return ret;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public static List<Deplacement> getDeplacementFromPartie(Partie p){
		List<Deplacement> ld=new ArrayList<>();
		ResultSet rs;
		try {
			rs = MainDAO.getInstance().getRequestvalue("select * from deplacement_par_partie dpp join deplacement d on d.id=dpp.id_deplacement where id_partie = "+p.getId()+" order by d.nb_coup ASC;");
			while(rs.next()){
				ld.add(new Deplacement(rs.getInt("id"),rs.getInt("deplacement_depart"),rs.getInt("deplacement_arrive"),rs.getInt("nb_coup")));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ld;
	}
	

}

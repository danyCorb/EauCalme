package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entite.Deplacement;
import Entite.Partie;

public class DeplacementParPartieDAO {
	public static void addNewDeplacementParPartie(Deplacement d, Partie p){
		int idDeplacement =d.getId();
		int idPartie =p.getId();
		
		
		MainDAO.getInstance().insert(" deplacement_par_partie ", new String[][]{
			{"id_partie",""+idPartie},
			{"id_deplacement",""+idDeplacement}
		});
	}
	
	
	public static List<Deplacement> getDeplacementByPartieId(Partie p){
		int partieId=p.getId();
		
		try {
			ResultSet rs=MainDAO.getInstance().getRequestvalue("Select * FROM deplacement_par_partie WHERE id_partie="+partieId+";");
			List<Deplacement> deplacements=new ArrayList<>();
			
			while(rs.next()){
				deplacements.add( DeplacementDAO.getDeplacementById(rs.getInt("id_deplacement")) );
			}
			rs.close();
			return deplacements;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
}

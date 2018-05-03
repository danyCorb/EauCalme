package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entite.Deplacement;
import Entite.Position;

public class TrollParDeplacementDAO {
	
	
	public static void addNewTrollParDeplacement(Deplacement d, int[][] trollPosition){
		int idDeplacement =d.getId();
		for(int[] troll : trollPosition ){
			int idPosition = PositionDAO.getPositionId(troll[0], troll[1]);
			
			MainDAO.getInstance().insert("trollpardeplacement ", new String[][]
			{
				{"id_deplacement",""+idDeplacement},
				{"id_position",""+idPosition}
			});
		}
	}
	
	
	public static List<Position> getTrollPositionByDeplacement(Deplacement d){
		int deplacementId=d.getId();
		
		try {
			ResultSet rs=MainDAO.getInstance().getRequestvalue("Select * FROM trollpardeplacement WHERE id_deplacement="+deplacementId+";");
			List<Position> position=new ArrayList<>();
			
			while(rs.next()){
				position.add( PositionDAO.getPositionById(rs.getInt("id_position")) );
			}
			rs.close();
			return position;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
}

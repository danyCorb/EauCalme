package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import Entite.Joueur;
import Entite.Partie;
import Entite.Position;

public class PositionDAO {
	
	
	public static int getPositionId(int x,int y){
		int ret=-1;
		try {
			ResultSet rs=MainDAO.getInstance().getRequestvalue("Select * FROM position WHERE pos_x="+x+" AND pos_y="+y+";");
			while(!rs.next()){
				MainDAO.getInstance().insert("position ", new String[][]
				{
					{"pos_x",""+x},
					{"pos_y",""+y}
				});
				rs.close();
				rs=MainDAO.getInstance().getRequestvalue("Select * FROM position WHERE pos_x="+x+" AND pos_y="+y+";");
			}
			ret=rs.getInt("id");
			rs.close(); 
		} catch (SQLException e) {
		}
		return ret;
	}
	
	public static Position getPositionById(int id){
		try {
			String rqt="Select * FROM position WHERE id="+id+";";
			ResultSet rs=MainDAO.getInstance().getRequestvalue(rqt);
			
			
			if((rs.first())){
				Position ret=new Position(rs.getInt("id"),rs.getInt("pos_x"),rs.getInt("pos_y"));
				rs.close();
				return ret;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

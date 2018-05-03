package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Entite.Joueur;
import Entite.Partie;

public class PartieDAO {
	public static Partie createNewPartie(Joueur j1,Joueur j2, boolean j1Commence){
		long timeStamp=System.currentTimeMillis();
		Partie p=new Partie(0,j1,j2,j1Commence,0,0,timeStamp);
		
		
		MainDAO.getInstance().insert("partie", new String[][]{
			{"joueur1",""+p.getJ1().getId()},
			{"joueur2",""+p.getJ2().getId()},
			{"joueur1_commence",""+(p.isJ1Commence() ? 1 : 0)  },
			{"scoreJ1",""+p.getScoreJ1()  },
			{"scoreJ2",""+p.getScoreJ2()  },
			{"date_joue",""+p.getDate_joue()  },
			
		});
		
		return getPartieByDate(timeStamp);
	}
	
	public static Partie getPartieByDate(long date_joue){
		try {
			ResultSet rs=MainDAO.getInstance().getRequestvalue("Select * FROM partie WHERE date_joue='"+date_joue+"';");
			if(rs.next()){
				return new Partie(
						rs.getInt("id"),
						JoueurDAO.getJoueurById(rs.getInt("joueur1")),
						JoueurDAO.getJoueurById(rs.getInt("joueur2")),
						rs.getInt("joueur1_commence")!=0 ? true : false,
						rs.getInt("scoreJ1"),
						rs.getInt("scoreJ2"),
						rs.getLong("date_joue"));
			}
		} catch (SQLException e) {
		}
		return null;
	}
	
	public static void updatePartie(Partie p){
		
		try {
			MainDAO.getInstance().executeQuery("UPDATE partie SET  scoreJ1="+p.getScoreJ1()+" , scoreJ2="+p.getScoreJ2()+" WHERE id="+p.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static List<String> getPartieForView(){
		List<String> ret=new ArrayList<>();
		try {
			ResultSet parties=MainDAO.getInstance().getRequestvalue("select j.nom AS nom, p.scoreJ1 AS scj1, p.scoreJ2 AS scj2 , p.date_joue AS timest, p.joueur2 AS j2, p.joueur1_commence AS j1commence from partie p join joueur j on j.id = p.joueur1 ;");
			while(parties.next()){
				String nom=parties.getString("nom");
				int sc1=parties.getInt("scj1");
				int sc2=parties.getInt("scj2");
				long date=parties.getLong("timest");
				int j2=parties.getInt("j2");
				int j1commence=parties.getInt("j1commence");
				String adv="";
				String gagner="";
				
				String timeString = new SimpleDateFormat("dd//MM//yyyy HH:mm:ss").format(date);
				if(j2==0){
					adv="Autre joueur";
				}else{
					adv="IA";
				}
				
				if(sc1>sc2){
					gagner="gagné";
				}else{
					gagner="perdu";
				}
				
				
				ret.add(nom+" à "+gagner+" contre "+adv +" le "+timeString);
				
			}
			parties.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	
	public static List<Partie> getListPartie(){
		List<Partie> ret=new ArrayList<>();
		try {
			ResultSet parties=MainDAO.getInstance().getRequestvalue("select id from partie ;");
			while(parties.next()){
				int id=parties.getInt("id");
				
				
				
				ret.add(new Partie(id,null,null,false,0,0,0));
				
			}
			parties.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	

}

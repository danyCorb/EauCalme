package Reseau;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import Data.DataMain;

public class JSonGenerator {
	
	private static int nbGame=1;
	private static int shot=0;
	
	public static String genererDeplacement(int x1,int y1,int x2,int y2,List<int[]> priseTrolle,boolean surrender,String nainPrendTrolle) throws IOException{
		shot+=2;
		
		List<String> nainPrendList= (nainPrendTrolle!=null) ? new ArrayList<>() : null;
		
		JSONObject obj = new JSONObject();
		
		obj.put("pawn",DataMain.getInstance().getDataPlateau().getPionOnCase(x1, y1));
		obj.put("slot_1",getFormatedCase(x1, y1));
		obj.put("slot_2",getFormatedCase(x2, y2));
		obj.put("slot_eat", (nainPrendTrolle==null) ? generateTrollePris(priseTrolle) : nainPrendList);
		obj.put("id_game",nbGame);
		obj.put("id_shot",shot);
		obj.put("surrender",surrender);
		
		StringWriter out = new StringWriter();
		obj.writeJSONString(out);
		  
		String jsonText = out.toString();
		
		
		return jsonText;
	}
	
	private static List<String> generateTrollePris(List<int[]> l){
		List<String> ret=new ArrayList<>();
		
		if(l!=null){
			for(int[] element : l){
				ret.add(DataMain.getInstance().getDataPlateau().getPionOnCase(element[0], element[1]));
			}
		}
		
		for(String elem : ret){
			System.out.println(elem);
		}
		
		return ret;
	}
	
	private static String getFormatedCase(int x,int y){
		String ret="";
		
		char lettre=(char) (x+'A');
		ret+=lettre;
		ret+=(y+1);
		
		return ret;
	}
	
	
	
	public static void setShot(int shot) {
		JSonGenerator.shot = shot;
	}

	public static int getShot() {
		return shot;
	}
	
}

package HRM;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.pmw.tinylog.Logger;

public class Tab3Landmarks {
	
	String x="";
	Map<String,String> map;
	

	ArrayList<String> Tab3Landmarks_arr = new ArrayList<String>();
	ArrayList<List<String>> Tab3Landmarks_table2d = new ArrayList<List<String>>();
	String [] seTab3Landmarks=null;

	public Tab3Landmarks(String x) {
		
		 x=this.x;
	}
	
	public Map<String, String> Tab3LandmarksRegex(String x, Map<String,String> map) {
		Pattern Tab3Landmarks_pattern = Pattern.compile("Landmarks(.*?Hiatal[^\n]*)",Pattern.DOTALL);
		Matcher matcherTab3Landmarks_pattern = Tab3Landmarks_pattern.matcher(x);
		
		while (matcherTab3Landmarks_pattern.find()) {
			seTab3Landmarks=matcherTab3Landmarks_pattern.group(1).split("\\n|\\r");
			//System.out.println("YES");
		}
		
		for (int ise=0;ise<seTab3Landmarks.length;ise++){	
			Tab3Landmarks_arr.add(seTab3Landmarks[ise].trim());
		}
		for (String ss:Tab3Landmarks_arr){
			
		String el[];
		ArrayList<String> tbb = new ArrayList<String>();
		el=ss.split(":");
			for (String e:el){
				if(!e.isEmpty()){
					
		          tbb.add(e.trim());
		         }
		    }
		         if(!tbb.isEmpty()){
		         Tab3Landmarks_table2d.add(tbb);
		         ////System.out.println("ee"+Tab3Landmarks_table2d);
		         for (int ff=0;ff<Tab3Landmarks_table2d.size();ff++){
						//Symptom
		        	 map.put("DetailsTabLandmarks"+Tab3Landmarks_table2d.get(ff).get(0),Tab3Landmarks_table2d.get(ff).get(1));
						  }
		         }
		 }
		seTab3Landmarks=null;
		Tab3Landmarks_arr=null;
		Tab3Landmarks_table2d=null;
		////System.out.println(Tab3Landmarks_table2d);
		return map;
}
}

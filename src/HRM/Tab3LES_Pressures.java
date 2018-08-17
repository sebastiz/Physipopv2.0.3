package HRM;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.pmw.tinylog.Logger;


public class Tab3LES_Pressures {
	String x="";
	Map<String,String> map;

	public Tab3LES_Pressures(String x) {
		  x=this.x;
		
	}
	
	ArrayList<String> Tab3LES_Pressures_arr = new ArrayList<String>();
	ArrayList<List<String>> Tab3LES_Pressures_table2d = new ArrayList<List<String>>();
	String [] seTab3LES_Pressures=null;
	
	
	public Map<String, String> LESPressuresRegex(String x, Map<String,String> map) {
	Pattern Tab3LES_Pressures_pattern = Pattern.compile("LES Pressures(.*?Residual[^\n]*)",Pattern.DOTALL);
	Matcher matcherTab3LES_Pressures_pattern = Tab3LES_Pressures_pattern.matcher(x);
	while (matcherTab3LES_Pressures_pattern.find()) {
		seTab3LES_Pressures=matcherTab3LES_Pressures_pattern.group(1).split("\\n|\\r");
		////System.out.println("GHGHGHGHGGGGGGG");
	}
	
	for (int ise=0;ise<seTab3LES_Pressures.length;ise++){	
		Tab3LES_Pressures_arr.add(seTab3LES_Pressures[ise].trim());
	}
	
	for (String ss:Tab3LES_Pressures_arr){
	String el[];
	ArrayList<String> tbb = new ArrayList<String>();
	el=ss.split(":");
		for (String e:el){
			if(!e.isEmpty()){
	          tbb.add(e.trim());
	         }
	    }
	         if(!tbb.isEmpty()){
	         Tab3LES_Pressures_table2d.add(tbb);
	         for (int ff=0;ff<Tab3LES_Pressures_table2d.size();ff++){
					//Symptom
	        	 String fg=Tab3LES_Pressures_table2d.get(ff).get(0).replace(",", "");
	        	 map.put("DetailTabLESPressure"+fg,Tab3LES_Pressures_table2d.get(ff).get(1));
					  }
	         }
	         seTab3LES_Pressures=null;
	         Tab3LES_Pressures_arr=null;
	         Tab3LES_Pressures_table2d=null;
	 }
	return map;

}
	
}

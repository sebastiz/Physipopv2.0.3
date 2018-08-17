package HRM;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.pmw.tinylog.Logger;

public class Sum_Motility_pattern {
	
	String x="";
	Map<String,String> map;
	

	ArrayList<String> Motility_arr = new ArrayList<String>();
	ArrayList<List<String>> Motility_table2d = new ArrayList<List<String>>();
	String [] seMotility=null;
	
	public Sum_Motility_pattern(String x) {
		
		
		 x=this.x;
	}
	
	public Map<String, String> SumMotilityPatternRegex(String x, Map<String,String> map) {
		Pattern Motility_pattern = Pattern.compile("Motility\\*(.*?Distal contr[^\n]*)",Pattern.DOTALL);
		Matcher matcherMotility_pattern = Motility_pattern.matcher(x);
		
		while (matcherMotility_pattern.find()) {
			seMotility=matcherMotility_pattern.group(1).split("\\n|\\r");
		}
		
		for (int ise=0;ise<seMotility.length;ise++){	
			Motility_arr.add(seMotility[ise]);
		}
		for (String ss:Motility_arr){
		String el[];
		ArrayList<String> tbb = new ArrayList<String>();
		el=ss.split("\\t");
			for (String e:el){
				if(!e.isEmpty()){
		          tbb.add(e.trim());
		          
		         }
		    }
		         if(!tbb.isEmpty()){
		         Motility_table2d.add(tbb);
		         for (int ff=0;ff<Motility_table2d.size();ff++){
						//Symptom
		        	 map.put("SumMotility"+Motility_table2d.get(ff).get(0),Motility_table2d.get(ff).get(1));
						  }
		         }
		 }
		//System.out.println(Motility_table2d);
		Motility_table2d=null;
		Motility_arr=null;
		return map;
	}

}

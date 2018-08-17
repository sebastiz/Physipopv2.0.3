package HRM;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.pmw.tinylog.Logger;

public class TxtExtr_BasalPressure {

	
	String x="";
	Map<String,String> map;

	ArrayList<String> SumBasPress_arr = new ArrayList<String>();
	ArrayList<List<String>> SumBasPress_table2d = new ArrayList<List<String>>();
	String [] se=null;
	public TxtExtr_BasalPressure(String x) {
		
		   x=this.x;
	}
	
	public Map<String, String> BasalRegex(String x, Map<String,String> map) {
		Pattern SumBasPress_pattern = Pattern.compile("Basal Pressures\\*(.*?UES mean[^\n]*)",Pattern.DOTALL);
		Matcher matcherSumBasPress_pattern = SumBasPress_pattern.matcher(x);
		while (matcherSumBasPress_pattern.find()) {
			se=matcherSumBasPress_pattern.group(1).split("\\n|\\r");
		}
		
		for (int ise=0;ise<se.length;ise++){	
			SumBasPress_arr.add(se[ise]);
		}
		for (String ss:SumBasPress_arr){
		String el[];
		ArrayList<String> tbb = new ArrayList<String>();
		el=ss.split("\\t");
			for (String e:el){
				if(!e.isEmpty()){
		          tbb.add(e.trim());
		         }
		    }
		         if(!tbb.isEmpty()){
		         SumBasPress_table2d.add(tbb);
		         for (int ff=0;ff<SumBasPress_table2d.size();ff++){
						//Symptom
		        	 String dd=SumBasPress_table2d.get(ff).get(0).replaceAll(", ", "_");
		        	 map.put("SumBasalPress"+dd,SumBasPress_table2d.get(ff).get(1));
						  }	
		         }
		 }
		se=null;
		SumBasPress_arr=null;
		SumBasPress_table2d=null;
		return map;
	}

}

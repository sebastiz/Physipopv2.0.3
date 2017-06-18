package HRM;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.pmw.tinylog.Logger;

public class Tab3_UES_pattern {
	String x="";
	Map<String,String> map;
	

	ArrayList<String> Tab3_UES_arr = new ArrayList<String>();
	ArrayList<List<String>> Tab3_UES_table2d = new ArrayList<List<String>>();
	String [] seTab3_UES=null;

	public Tab3_UES_pattern(String x) {
		// TODO Auto-generated constructor stub
		 x=this.x;
	}
	
	
	public Map<String, String> Tab3_UES_Regex(String x, Map<String,String> map) {
		Pattern Tab3_UES_pattern = Pattern.compile("Upper Esophageal Sphincter(.*?Mean residual[^\n]*)",Pattern.DOTALL);
		Matcher matcherTab3_UES_pattern = Tab3_UES_pattern.matcher(x);
		while (matcherTab3_UES_pattern.find()) {
			seTab3_UES=matcherTab3_UES_pattern.group(1).split("\\n|\\r");
		}
		
		for (int ise=0;ise<seTab3_UES.length;ise++){	
			Tab3_UES_arr.add(seTab3_UES[ise].trim());
		}
		for (String ss:Tab3_UES_arr){
		String el[];
		ArrayList<String> tbb = new ArrayList<String>();
		el=ss.split(":");
			for (String e:el){
				if(!e.isEmpty()){
		          tbb.add(e.trim());
		         }
		    }
		         if(!tbb.isEmpty()){
		         Tab3_UES_table2d.add(tbb);
		         for (int ff=0;ff<Tab3_UES_table2d.size();ff++){
						//Symptom
		        	 String dd=Tab3_UES_table2d.get(ff).get(0).replace(",", "_");
		        	 map.put("DetailsTabUESMeanResid"+dd,Tab3_UES_table2d.get(ff).get(1));
						  }
		         }
		         seTab3_UES=null;
		         Tab3_UES_arr=null;
		         Tab3_UES_table2d=null;
		 }
		return map;
	}

}

package HRM;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.pmw.tinylog.Logger;

public class Tab3_ChicagoClass {
	
	String x="";
	Map<String,String> map;
	ArrayList<List<String>> Tab3_ChicagoClass_table2d = new ArrayList<List<String>>();
	ArrayList<String> Tab3_ChicagoClass_arr = new ArrayList<String>();

	String [] seTab3_ChicagoClass=null;

	public Tab3_ChicagoClass(String x) {
		// TODO Auto-generated constructor stub
		x=this.x;
	}
	
	public Map<String, String> tab3ChicagoClassRegex(String x, Map<String,String> map) {
		Pattern Tab3_ChicagoClass_pattern = Pattern.compile("Chicago Classification\\:(.*?\\% small[^\n]*)",Pattern.DOTALL);
		Matcher matcherTab3_ChicagoClass_pattern = Tab3_ChicagoClass_pattern.matcher(x);
		
		
		while (matcherTab3_ChicagoClass_pattern.find()) {
			seTab3_ChicagoClass=matcherTab3_ChicagoClass_pattern.group(1).split("\\n|\\r");
		}
		
		for (int ise=0;ise<seTab3_ChicagoClass.length;ise++){	
			Tab3_ChicagoClass_arr.add(seTab3_ChicagoClass[ise].trim());
		}
		for (String ss:Tab3_ChicagoClass_arr){
		String el[];
		ArrayList<String> tbb = new ArrayList<String>();
		 el=ss.split(":");
		 	for (String e:el){
		 		if(!e.isEmpty()){
		           tbb.add(e);
		          }
		     }
		          if(!tbb.isEmpty()){
		          Tab3_ChicagoClass_table2d.add(tbb);
		          for (int ff=0;ff<Tab3_ChicagoClass_table2d.size();ff++){
						//Symptom
		        	 map.put("DetailsTabChicagoClass"+Tab3_ChicagoClass_table2d.get(ff).get(0),Tab3_ChicagoClass_table2d.get(ff).get(1));
						  }
		          }
		          Tab3_ChicagoClass_table2d=null;
		          Tab3_ChicagoClass_arr=null;
		          seTab3_ChicagoClass=null;
		  }
           return map;
}
	
}

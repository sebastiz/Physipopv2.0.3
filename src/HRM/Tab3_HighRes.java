package HRM;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.pmw.tinylog.Logger;

public class Tab3_HighRes {
	
	String x="";
	Map<String,String> map;
	ArrayList<String> Tab3_HighRes_arr = new ArrayList<String>();
	ArrayList<List<String>> Tab3_HighRes_table2d = new ArrayList<List<String>>();
	String [] seTab3_HighRes=null;

	public Tab3_HighRes(String x) {
    x=this.x;
    }
	
	public Map<String, String> tab3HighResRegex(String x, Map<String,String> map) {
		////System.out.println("HERE IS THE ANSWER"+x);
	Pattern Tab3_HighRes_pattern = Pattern.compile("High Resolution Parameters:\\cG(.*?Intrabolus pressure)",Pattern.DOTALL);
	Matcher matcherTab3_HighRes_pattern = Tab3_HighRes_pattern.matcher(x);
	

	while (matcherTab3_HighRes_pattern.find()) {
		////System.out.println("NOTHING HERE");
		seTab3_HighRes=matcherTab3_HighRes_pattern.group(1).split("\\n|\\r");
	}
	
	//System.out.println(seTab3_HighRes);
	
	for (int ise=0;ise<seTab3_HighRes.length;ise++){	
		////System.out.println("hjkhjkh"+seTab3_HighRes);
		Tab3_HighRes_arr.add(seTab3_HighRes[ise].trim());
	}
	
	for (String ss:Tab3_HighRes_arr){
	String el[];
	ArrayList<String> tbb = new ArrayList<String>();
	 el=ss.split(":");
	 	for (String e:el){
	 		if(!e.isEmpty()){
	           tbb.add(e);
	          }
	     }
	          if(!tbb.isEmpty()){
	          Tab3_HighRes_table2d.add(tbb);
	          //System.out.println("Tab3_HighRes_table2d"+Tab3_HighRes_table2d);
	          for (int ff=0;ff<Tab3_HighRes_table2d.size();ff++){
					//Symptom
	        	 map.put("DetTabHighResParam"+Tab3_HighRes_table2d.get(ff).get(0),Tab3_HighRes_table2d.get(ff).get(1));
					  }
	          }
	  }
	seTab3_HighRes=null;
	Tab3_HighRes_arr=null;
	Tab3_HighRes_table2d=null;
	//System.out.println("Tab3_HighRes_table2d"+Tab3_HighRes_table2d);
	return map;
	}

}

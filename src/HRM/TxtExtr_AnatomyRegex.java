package HRM;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.pmw.tinylog.Logger;

public class TxtExtr_AnatomyRegex {
	String x="";
	Map<String,String> map;
	
	 public TxtExtr_AnatomyRegex(String x) {
		    
		    x=this.x;

		  }
	ArrayList<String> Anatomy_arr = new ArrayList<String>();
	ArrayList<List<String>> Anatomy_table2d = new ArrayList<List<String>>();
	String [] seAnatomy=null;
	
	public Map<String, String> AnatomyRegex(String x, Map<String,String> map) {
		Pattern Anatomy_pattern = Pattern.compile("Anatomy\\*(.*?Hiatal hernia[^\n]*)",Pattern.DOTALL);
		Matcher matcherAnatomy_pattern = Anatomy_pattern.matcher(x);
		
	while (matcherAnatomy_pattern.find()) {
		////System.out.println("GHGHGHGHGHGHGHG"+ x);
		seAnatomy=matcherAnatomy_pattern.group(1).split("\\n|\\r");
	}
	
	for (int ise=0;ise<seAnatomy.length;ise++){	
		Anatomy_arr.add(seAnatomy[ise]);
	}
	for (String ss:Anatomy_arr){
	String el[];
	ArrayList<String> tbb = new ArrayList<String>();
	el=ss.split("\\t");
		for (String e:el){
			if(!e.isEmpty()){
	          tbb.add(e.trim());
	         }
	    }
	         if(!tbb.isEmpty()){
	         Anatomy_table2d.add(tbb);
	         for (int ff=0;ff<Anatomy_table2d.size();ff++){
					//Symptom
	        	 map.put("SumAnatomy"+Anatomy_table2d.get(ff).get(0),Anatomy_table2d.get(ff).get(1));
					  }
	         }
	 }
	seAnatomy=null;
	Anatomy_arr=null;
	Anatomy_table2d=null;
	return map;
	}

}

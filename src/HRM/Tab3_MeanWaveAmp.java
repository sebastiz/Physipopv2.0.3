package HRM;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.pmw.tinylog.Logger;

public class Tab3_MeanWaveAmp {
	
	String x="";
	Map<String,String> map;
	 ArrayList<String> Tab3_MeanWaveAmp_arr = new ArrayList<String>();
	 String [] seTab3_MeanWaveAmp=null;

	public Tab3_MeanWaveAmp(String x) {
		
		x=this.x;
		
	}

	public Map<String, String> tab3MeanWaveAmpRegex(String x, Map<String,String> map) {
		
		Pattern Tab3_MeanWaveAmp_pattern = Pattern.compile("Mean wave amp.*");
		 Matcher matcherTab3_MeanWaveAmp_pattern = Tab3_MeanWaveAmp_pattern.matcher(x);
		 
		 
	 while (matcherTab3_MeanWaveAmp_pattern.find()) {
	      seTab3_MeanWaveAmp=matcherTab3_MeanWaveAmp_pattern.group(0).split(":");
	 }
	 for (int ise=0;ise<seTab3_MeanWaveAmp.length;ise++){	
	     Tab3_MeanWaveAmp_arr.add(seTab3_MeanWaveAmp[ise].trim());
	 }
		map.put("DetailsTabMeanWaveAmp"+Tab3_MeanWaveAmp_arr.get(0).replace(".", ""), Tab3_MeanWaveAmp_arr.get(1));
		//System.out.println(Tab3_MeanWaveAmp_arr);
		seTab3_MeanWaveAmp=null;
		Tab3_MeanWaveAmp_arr=null;
		
		return map;
	}
}

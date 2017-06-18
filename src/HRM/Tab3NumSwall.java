package HRM;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.pmw.tinylog.Logger;

public class Tab3NumSwall {
	
	String x="";
	Map<String,String> map;
	ArrayList<String> Tab3NumSwall_arr = new ArrayList<String>();
	String [] seTab3NumSwall=null;

	public Tab3NumSwall(String x) {
		// TODO Auto-generated constructor stub
	}
	
	public Map<String, String> Tab3NumSwallRegex(String x, Map<String,String> map) {

	Pattern Tab3NumSwall_pattern = Pattern.compile("Number of swallows.*");
	Matcher matcherTab3NumSwall_pattern = Tab3NumSwall_pattern.matcher(x);
	
	while (matcherTab3NumSwall_pattern.find()) {
		////System.out.println("YESDDSDS");
		seTab3NumSwall=matcherTab3NumSwall_pattern.group(0).split(":");
	}
	
	for (int ise=0;ise<seTab3NumSwall.length;ise++){	
		Tab3NumSwall_arr.add(seTab3NumSwall[ise].trim());
		////System.out.println("Tab3NumSwall_arr"+Tab3NumSwall_arr);
	}
	map.put("DetailsTabNumSwallOesophMotil"+Tab3NumSwall_arr.get(0), Tab3NumSwall_arr.get(1));
	seTab3NumSwall=null;
	Tab3NumSwall_arr=null;
	return map;
	
}
	
}


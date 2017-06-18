package HRM;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.pmw.tinylog.Logger;

public class HRM_SwallowTableExtractor {
	String x;
	String getthis;
	ArrayList<String> myArray = new ArrayList<String>();

	public HRM_SwallowTableExtractor(String x,String pattern) {
		this.x=x;
		this.getthis=pattern;
	}

	public ArrayList<String> SwallwTabExtract(String x,String pattern) {


		Pattern patj = Pattern.compile(getthis);
		Matcher matcher_pattern = patj.matcher(x);
		//System.out.println("HERE IS IT" + pattern);
		while (matcher_pattern.find()) {

			String p=matcher_pattern.group(1).trim();
			String [] arr=p.split("\\s");
			for (int i=0;i<arr.length;i++){
				myArray.add(arr[i]);
			}
		}
		return myArray;
	}


}

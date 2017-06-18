



	//Need to go through each table
	//If table has two columns the just key value them
	//Deal with by iterating through s

	package Bravo;

	import java.io.File;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
	import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hwpf.HWPFDocument;
	import org.apache.poi.poifs.filesystem.POIFSFileSystem;
	import org.apache.tika.Tika;
	public class BravoNewDoc {

	public BravoNewDoc(String s) throws IOException, SQLException {


		//Need to go through each table
		//If table has two columns the just key value them
		//Deal with by iterating through section
		//Then deal with per table- there should be five tables per section here

//Firstly chop it up into Days
	       Pattern Day_pattern = Pattern.compile("Day.* Probability that symptom and reflux are not associated solely by chance");
	       Matcher matcherDay_pattern = Day_pattern.matcher(s);
	       while (matcherDay_pattern.find()) {
	    	   //Send it up for chopping to the IntraDayChopper
	    	   IntraDayChopper(matcherDay_pattern.group(0));

	       }


	}
	public String IntraDayChopper(String f){
		Pattern Day_pattern = Pattern.compile("Day.* Probability that symptom and reflux are not associated solely by chance");
	       Matcher matcherDay_pattern = Day_pattern.matcher(f);




		return f;

	}
	}


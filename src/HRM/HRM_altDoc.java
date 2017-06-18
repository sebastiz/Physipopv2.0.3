package HRM;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;
import org.pmw.tinylog.Logger;


public class HRM_altDoc {
	String pdfname;
	String mypdf;
	String pattern;
	String rh;
	String HospNum;
	String filename;
	String s=null;
	static Path p;
	Map<String,String> mapAllReport= new LinkedHashMap<String,String>();

	public HRM_altDoc(String pdfname) {
		// TODO Auto-generated constructor stub
		this.pdfname=pdfname;
	}


	public String ExtractPDF (String pdfname) throws IOException, SAXException, TikaException, ClassNotFoundException, SQLException, URISyntaxException{
				 BodyContentHandler handler = new BodyContentHandler();
	     Metadata metadata = new Metadata();
	     FileInputStream inputstream = new FileInputStream(new File(pdfname));
	     ParseContext pcontext = new ParseContext();
	     PDFParser pdfparser = new PDFParser();
	     pdfparser.parse(inputstream, handler, metadata,pcontext);
	     String s=handler.toString();
	     s=s.replaceAll("\\s\\s+", "\n");
	     PatternPDF(s);
	     inputstream.close();
	     PDFont.clearResources();
	     System.gc();
		return s;

	}

	//This takes the
	public String PatternPDF (String s) throws IOException, ClassNotFoundException, SQLException, URISyntaxException {
		ArrayList<String>arr=new ArrayList<String>();


		BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.class.getResourceAsStream("/Files/Fields2Extract.txt")),2048);
		String line = null;
		//Reading the files from the logger so they can be avoided
		while((line = reader.readLine()) != null) {
			arr.add(line);
		}





		    HospNum=HospNum(s);
			OrganisePDF(pattern,s);
		   	DBConnectorHRM ConnectMeUp = new DBConnectorHRM(mapAllReport,HospNum);
		   	Statement st=ConnectMeUp.Connector(HospNum);
		   	String first=ConnectMeUp.StringInsertKeyPreparer(mapAllReport);
		   	String second=ConnectMeUp.StringInsertValuePreparer(mapAllReport);
		   	ConnectMeUp.Inserter(st,HospNum,first,second);
		   	//mapAllReport=null;
		return pattern;
	}

	StringBuilder builder = new StringBuilder();

	public Map<String,String> OrganisePDF (String pattern,String s) throws URISyntaxException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.class.getResourceAsStream("/Files/Fields2Extract.txt")),2048);
		String lines = null;
		 //p = Paths.get(filename.toURI());
		try {
			List<String> f=Files.readAllLines(p);
			while((lines = reader.readLine()) != null) {
				f.add(lines);
			}
			for (String line :f){
			Pattern patternorganise = Pattern.compile(line);
			Matcher matcherpatternorganise_pattern = patternorganise.matcher(s);
			String h="";
			while (matcherpatternorganise_pattern.find()){
				////System.out.println("YES");
				 h=matcherpatternorganise_pattern.group(0);
					if(h.contains("Distal latency")||h.contains("% failed (Chicago Classification)")||h.contains("% panesophageal pressurization")||h.contains("% premature contraction")||h.contains("% rapid contraction")||h.contains("% large breaks")||h.contains("% small breaks")){
					builder.append(h+"\n");
					////System.out.println(builder);
					}

					if(h.contains("LES midpoint")|h.contains("Proximal LES")|h.contains("Distal LES")|h.contains("LES length")|h.contains("Esophageal length")|h.contains("PIP")|h.contains("Intraabdominal LES length")|h.contains("Hiatal hernia?")){
						builder.append(h+"\n");
					}
					if(h.contains("Basal (respiratory min")|h.contains("Basal (respiratory mean")|h.contains("Residual (mean)(mmHg)")|h.contains("Residual (highest)(mmHg)")){
			        	   builder.append(h+"\n");
			        }
					if(h.contains("Location")|h.contains("Mean basal pressure")|h.contains("Mean residual pressure")|h.contains("Relaxation time-to-nadir")|h.contains("Relaxation duration")|h.contains("Recovery time")){
			            	builder.append(h+"\n");
			        }
			        if(h.contains("Distal contractile integral")|h.contains("Distal contractile integral")|h.contains("Contractile front velocity")|h.contains("Intrabolus pressure (@LESR)(mmHg)")|h.contains("Intrabolus pressure (avg max)(mmHg)")){
			            	builder.append(h+"\n");
			        }
			        if(h.contains("Number of swallows evaluated")|h.contains("Mean wave amplitude")){
			            	builder.append(h+"\n");
			        }
			        if(h.contains("No. swallows evaluated")|h.contains("Mean peak pressure")){
			        	builder.append(h+"\n");
			    }

			}
			}
			reader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Create titles for each section so can then be processed by the Main HRMExtractor class:


        String rh=builder.toString();
        //System.out.println("Mother"+rh);
        Map<String,String>newmap=Preparer(rh);
		return newmap;
	}

	public Map<String,String> Preparer(String rh){
		rh=rh.replaceAll("\\(Chicago Classification\\)","").trim();
		rh=rh.replaceAll("Evaluated \\@ 3\\.0 & N\\/A above UES","").trim();
		rh=rh.replaceAll("% failed","Chicago Classification:\n% failed").trim();
		rh=rh.replaceAll("Distal contractile integral\\(highest\\)","High Resolution Parameters:\nDistal contractile integral\\(highest\\)").trim();
		rh=rh.replaceAll("Location","Upper Esophageal Sphincter:\nLocation").trim();
		rh=rh.replaceAll("LES midpoint","Landmarks:\nLES midpoint").trim();
		rh=rh.replaceAll("Basal \\(respiratory min","LES Pressures:\nBasal \\(respiratory min").trim();
		rh=rh.replaceAll("(\\W)\\s+?([+-]?[0-9])", "$1:$2").trim();
		rh=rh.replaceAll("([a-z])\\s+?(\\d)", "$1:$2").trim();
		//Special case hiatus hernia
		rh=rh.replace("Hiatal hernia?","Hiatal hernia?:").trim();
		rh=rh.replace(",","").trim();
		rh=rh.replace("", "");

System.out.println("DO I EVEN COME HERE TO HRMALTDOC?");
		try {
 			Tab3Landmarks Tab3LandMarks = new Tab3Landmarks(rh);
        	//System.out.println("MY GREAT Tab3LandMarks CLASS"+Tab3LandMarks.Tab3LandmarksRegex(rh,mapAllReport));


		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
 		//---------------------------------------------
 		try {
 			//Tab3LES_Pressures Tab3Anat = new Tab3LES_Pressures(rh);
        	////System.out.println("MY GREAT Tab3LES_Pressures CLASS"+Tab3Anat.LESPressuresRegex(rh,mapAllReport));
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}


 		try {
 			//Tab3_UES_pattern Tab3UES = new Tab3_UES_pattern(rh);
        	////System.out.println("MY GREAT Tab3_UES_pattern CLASS"+Tab3UES.Tab3_UES_Regex(rh,mapAllReport));

		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

 		try {
          	Tab3NumSwall tab3NumSwall = new Tab3NumSwall(rh);
        	tab3NumSwall.Tab3NumSwallRegex(rh,mapAllReport);
        	//System.out.println("MY GREAT tab3NumSwall CLASS"+tab3NumSwall);

		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        //---------------------------------------------
         try {
        	 Tab3_MeanWaveAmp tab3MeanWaveAmp = new Tab3_MeanWaveAmp(rh);
        	 tab3MeanWaveAmp.tab3MeanWaveAmpRegex(rh,mapAllReport);

		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
         //---------------------------------------------
  		try {

       	 Tab3_HighRes tab3HighRes = new Tab3_HighRes(rh);
       	 tab3HighRes.tab3HighResRegex(rh,mapAllReport);
     	////System.out.println("MY GREAT tab3HighRes CLASS"+mapAllReport);


		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
  		//---------------------------------------------------------------
  		try {
  	     	 Tab3_ChicagoClass tab3ChicagoClass = new Tab3_ChicagoClass(rh);
  	     	tab3ChicagoClass.tab3ChicagoClassRegex(rh,mapAllReport);
  	     	////System.out.println("MY GREAT Tab3_ChicagoClass CLASS"+mapAllReport);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        rh=null;
		return mapAllReport;

	}

//------------------------------------------------------ Connecting to the database	------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------

	public String HospNum(String s) {
		Pattern HospNum_pattern = Pattern.compile("(.*) Procedure");
		Matcher matcherHospNum_pattern = HospNum_pattern.matcher(s);

		if (matcherHospNum_pattern.find()){
			HospNum=matcherHospNum_pattern.group(1).trim();
		}
		return HospNum;

		}







}



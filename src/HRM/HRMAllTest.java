package HRM;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.Test;
import org.xml.sax.SAXException;




public class HRMAllTest {
	File child = new File("//Users/sebastianzeki//Documents//PhysJava//BugFolder - Copy/BugFolder - Copy//Phys_HRM_STEKELENBURG,.doc");
	String child_str=child.getPath();
	public String GetHRMFile(String child) throws IOException, SAXException, TikaException{

		AutoDetectParser parser = new AutoDetectParser();
		BodyContentHandler handler = new BodyContentHandler(-1);
		Metadata metadata = new Metadata();
		FileInputStream inputstream = new FileInputStream(new File(child_str));
		ParseContext context = new ParseContext();
		parser.parse(inputstream, handler, metadata, context);
		String s=null;
	    s =handler.toString();
	    //System.out.println(s);
	    handler=null;
	    context=null;
	    inputstream.close();
	    PDFont.clearResources();
	    System.gc();
	    return s;
	}



	@Test
	public void testHRMAll_MainMapAllReport() throws IOException, SAXException, TikaException, SQLException {
		 Map<String,String> mapAllReportReference= new LinkedHashMap<String,String>();
		 mapAllReportReference.put("HospNum_Id","X2392420");
		 mapAllReportReference.put("FName","");
		 mapAllReportReference.put("SName","STEKELENBURG, Joan");
		 mapAllReportReference.put("VisitDate","03_21_2016");
		 mapAllReportReference.put("DOBAge","08/30/2052");
		 mapAllReportReference.put("Gender","Female");
		 mapAllReportReference.put("ReferringPhysician","");
		 mapAllReportReference.put("Physician","Wong Terry Dr");
		 mapAllReportReference.put("Operator","DR JAFAR JAFARI");
		 mapAllReportReference.put("LES,respiratorymean(mmHg)","19.2");
		 mapAllReportReference.put("UESmean(mmHg)","41.3");
		 mapAllReportReference.put("LESproximal(cm)","40.2");
		 mapAllReportReference.put("LESintraabdominal(cm)","0.0");
		 mapAllReportReference.put("Esophageallength(cm)","22.0");
		 mapAllReportReference.put("Hiatalhernia","Yes");
		 mapAllReportReference.put("Dist.waveamplitude(mmHg)","50.6");
		 mapAllReportReference.put("Wavedur.@LES-3.0&7.0(s)","3.1");
		 mapAllReportReference.put("Percentperistaltic(%)","40");
		 mapAllReportReference.put("Percentsimultaneous(%)","20");
		 mapAllReportReference.put("Percentfailed(%)","40");
		 mapAllReportReference.put("LES(mean)(mmHg)","10.4");
		 mapAllReportReference.put("UES(mean)(mmHg)","-2.2");
		 mapAllReportReference.put("Numberofswallowsevaluated","10");
		 mapAllReportReference.put("ProximalLES(fromnares)(cm)","40.2");
		 mapAllReportReference.put("LESlength(cm)","2.6");
		 mapAllReportReference.put("Peristaltic(velocity≤6.25cm/s)(%)","40");
		 mapAllReportReference.put("Esophageallength(LESUEScenters)(cm)","22.0");
		 mapAllReportReference.put("IntraabdominalLESlength(cm)","0.0");
		 mapAllReportReference.put("Failed(%)","40");
		 mapAllReportReference.put("Meanwaveamplitude(mmHg)","50.6");
		 mapAllReportReference.put("Meanwaveduration(s)","3.1");
		 mapAllReportReference.put("Basal(respiratorymin.)(mmHg)","13.8");
		 mapAllReportReference.put("Double-peakedwaves(%)","0");
		 mapAllReportReference.put("Basal(respiratorymean)(mmHg)","19.2");
		 mapAllReportReference.put("Triple-peakedwaves(%)","0");
		 mapAllReportReference.put("Residual(mean)(mmHg)","10.4");
		 mapAllReportReference.put("Distalcontractileintegral(mean)(mmHg-cm-s)","369.9");
		 mapAllReportReference.put("Contractilefrontvelocity(cm/s)","4.9");
		 mapAllReportReference.put("Intraboluspressure(@LESR)(mmHg)","1.1");
		 mapAllReportReference.put("Intraboluspressure(avgmax)(mmHg)","14.6");
		 mapAllReportReference.put("Distallatency","5.1");
		 mapAllReportReference.put("%panesophagealpressurization","0");
		 mapAllReportReference.put("%prematurecontraction","30");
		 mapAllReportReference.put("%rapidcontraction","20");
		 mapAllReportReference.put("%largebreaks","10");
		 mapAllReportReference.put("%smallbreaks","60");
		 mapAllReportReference.put("Meanbasalpressure(mmHg)","41.3");
		 mapAllReportReference.put("Meanresidualpressure(mmHg)","-2.2");
		 mapAllReportReference.put("Meanpeakpressure(mmHg)","10.6");
		Map<String,String>  mapAllReportTest = new LinkedHashMap<String,String>();

		String s=GetHRMFile(child_str);

		mapAllReportTest=HRMAll.MainHRMExtractor(s, child_str);
		System.out.println("mapAllReportReference "+mapAllReportReference);
		System.out.println("mapAllReportTest "+mapAllReportTest);
		assertEquals(mapAllReportTest,mapAllReportReference);

	}

	@Test
	public void testHRMAll_SwallowExtractor() throws IOException, SAXException, TikaException, SQLException {

		Map<String,String>  mapAllSwallowExtractorTest = new LinkedHashMap<String,String>();
		Map<String,String>  mapAllSwallowExtractorRefe = new LinkedHashMap<String,String>();
		mapAllSwallowExtractorRefe.put("LES residual pressure (mmHg)MapSwallowsNum1","10.4");
		mapAllSwallowExtractorRefe.put("LES residual pressure (mmHg)MapSwallowsNum2","14.9");
		mapAllSwallowExtractorRefe.put("LES residual pressure (mmHg)MapSwallowsNum3","12.1");
		mapAllSwallowExtractorRefe.put("LES residual pressure (mmHg)MapSwallowsNum4","9.8");
		mapAllSwallowExtractorRefe.put("LES residual pressure (mmHg)MapSwallowsNum5","10.9");
		mapAllSwallowExtractorRefe.put("LES residual pressure (mmHg)MapSwallowsNum6","8.8");
		mapAllSwallowExtractorRefe.put("LES residual pressure (mmHg)MapSwallowsNum7","8.0");
		mapAllSwallowExtractorRefe.put("LES residual pressure (mmHg)MapSwallowsNum8","10.2");
		mapAllSwallowExtractorRefe.put("LES residual pressure (mmHg)MapSwallowsNum9","10.7");
		mapAllSwallowExtractorRefe.put("LES residual pressure (mmHg)MapSwallowsNum10","8.9");
		mapAllSwallowExtractorRefe.put("LES residual pressure (mmHg)MapSwallowsNum11","10.1");
		mapAllSwallowExtractorRefe.put("Wave amplitude (mean, 3.0 & 7.0 above LES) (mmHg)MapSwallowsNum1","50.6");
		mapAllSwallowExtractorRefe.put("Wave amplitude (mean, 3.0 & 7.0 above LES) (mmHg)MapSwallowsNum2","42.7");
		mapAllSwallowExtractorRefe.put("Wave amplitude (mean, 3.0 & 7.0 above LES) (mmHg)MapSwallowsNum3","39.0");
		mapAllSwallowExtractorRefe.put("Wave amplitude (mean, 3.0 & 7.0 above LES) (mmHg)MapSwallowsNum4","28.6");
		mapAllSwallowExtractorRefe.put("Wave amplitude (mean, 3.0 & 7.0 above LES) (mmHg)MapSwallowsNum5","73.9");
		mapAllSwallowExtractorRefe.put("Wave amplitude (mean, 3.0 & 7.0 above LES) (mmHg)MapSwallowsNum6","38.5");
		mapAllSwallowExtractorRefe.put("Wave amplitude (mean, 3.0 & 7.0 above LES) (mmHg)MapSwallowsNum7","N/A");
		mapAllSwallowExtractorRefe.put("Wave amplitude (mean, 3.0 & 7.0 above LES) (mmHg)MapSwallowsNum8","86.2");
		mapAllSwallowExtractorRefe.put("Wave amplitude (mean, 3.0 & 7.0 above LES) (mmHg)MapSwallowsNum9","N/A");
		mapAllSwallowExtractorRefe.put("Wave amplitude (mean, 3.0 & 7.0 above LES) (mmHg)MapSwallowsNum10","32.5");
		mapAllSwallowExtractorRefe.put("Wave amplitude (mean, 3.0 & 7.0 above LES) (mmHg)MapSwallowsNum11","63.2");
		mapAllSwallowExtractorRefe.put("Wave duration (mean at 3.0 & 7.0 above LES) (sec)MapSwallowsNum1","3.1");
		mapAllSwallowExtractorRefe.put("Wave duration (mean at 3.0 & 7.0 above LES) (sec)MapSwallowsNum2","3.2");
		mapAllSwallowExtractorRefe.put("Wave duration (mean at 3.0 & 7.0 above LES) (sec)MapSwallowsNum3","4.2");
		mapAllSwallowExtractorRefe.put("Wave duration (mean at 3.0 & 7.0 above LES) (sec)MapSwallowsNum4","2.8");
		mapAllSwallowExtractorRefe.put("Wave duration (mean at 3.0 & 7.0 above LES) (sec)MapSwallowsNum5","3.2");
		mapAllSwallowExtractorRefe.put("Wave duration (mean at 3.0 & 7.0 above LES) (sec)MapSwallowsNum6","2.6");
		mapAllSwallowExtractorRefe.put("Wave duration (mean at 3.0 & 7.0 above LES) (sec)MapSwallowsNum7","N/A");
		mapAllSwallowExtractorRefe.put("Wave duration (mean at 3.0 & 7.0 above LES) (sec)MapSwallowsNum8","3.3");
		mapAllSwallowExtractorRefe.put("Wave duration (mean at 3.0 & 7.0 above LES) (sec)MapSwallowsNum9","N/A");
		mapAllSwallowExtractorRefe.put("Wave duration (mean at 3.0 & 7.0 above LES) (sec)MapSwallowsNum10","3.2");
		mapAllSwallowExtractorRefe.put("Wave duration (mean at 3.0 & 7.0 above LES) (sec)MapSwallowsNum11","2.8");
		mapAllSwallowExtractorRefe.put("% peristaltic (between 11.0 & 3.0 above LES)MapSwallowsNum1","40");
		mapAllSwallowExtractorRefe.put("% peristaltic (between 11.0 & 3.0 above LES)MapSwallowsNum2","1");
		mapAllSwallowExtractorRefe.put("% peristaltic (between 11.0 & 3.0 above LES)MapSwallowsNum3","0");
		mapAllSwallowExtractorRefe.put("% peristaltic (between 11.0 & 3.0 above LES)MapSwallowsNum4","0");
		mapAllSwallowExtractorRefe.put("% peristaltic (between 11.0 & 3.0 above LES)MapSwallowsNum5","1");
		mapAllSwallowExtractorRefe.put("% peristaltic (between 11.0 & 3.0 above LES)MapSwallowsNum6","1");
		mapAllSwallowExtractorRefe.put("% peristaltic (between 11.0 & 3.0 above LES)MapSwallowsNum7","0");
		mapAllSwallowExtractorRefe.put("% peristaltic (between 11.0 & 3.0 above LES)MapSwallowsNum8","1");
		mapAllSwallowExtractorRefe.put("% peristaltic (between 11.0 & 3.0 above LES)MapSwallowsNum9","0");
		mapAllSwallowExtractorRefe.put("% peristaltic (between 11.0 & 3.0 above LES)MapSwallowsNum10","0");
		mapAllSwallowExtractorRefe.put("% peristaltic (between 11.0 & 3.0 above LES)MapSwallowsNum11","0");
		mapAllSwallowExtractorRefe.put("% simultaneous (between 11.0 & 3.0 above LES)MapSwallowsNum1","20");
		mapAllSwallowExtractorRefe.put("% simultaneous (between 11.0 & 3.0 above LES)MapSwallowsNum2","0");
		mapAllSwallowExtractorRefe.put("% simultaneous (between 11.0 & 3.0 above LES)MapSwallowsNum3","1");
		mapAllSwallowExtractorRefe.put("% simultaneous (between 11.0 & 3.0 above LES)MapSwallowsNum4","0");
		mapAllSwallowExtractorRefe.put("% simultaneous (between 11.0 & 3.0 above LES)MapSwallowsNum5","0");
		mapAllSwallowExtractorRefe.put("% simultaneous (between 11.0 & 3.0 above LES)MapSwallowsNum6","0");
		mapAllSwallowExtractorRefe.put("% simultaneous (between 11.0 & 3.0 above LES)MapSwallowsNum7","0");
		mapAllSwallowExtractorRefe.put("% simultaneous (between 11.0 & 3.0 above LES)MapSwallowsNum8","0");
		mapAllSwallowExtractorRefe.put("% simultaneous (between 11.0 & 3.0 above LES)MapSwallowsNum9","0");
		mapAllSwallowExtractorRefe.put("% simultaneous (between 11.0 & 3.0 above LES)MapSwallowsNum10","0");
		mapAllSwallowExtractorRefe.put("% simultaneous (between 11.0 & 3.0 above LES)MapSwallowsNum11","1");
		mapAllSwallowExtractorRefe.put("% failed (between 11.0 & 3.0 above LES)MapSwallowsNum1","40");
		mapAllSwallowExtractorRefe.put("% failed (between 11.0 & 3.0 above LES)MapSwallowsNum2","0");
		mapAllSwallowExtractorRefe.put("% failed (between 11.0 & 3.0 above LES)MapSwallowsNum3","0");
		mapAllSwallowExtractorRefe.put("% failed (between 11.0 & 3.0 above LES)MapSwallowsNum4","1");
		mapAllSwallowExtractorRefe.put("% failed (between 11.0 & 3.0 above LES)MapSwallowsNum5","0");
		mapAllSwallowExtractorRefe.put("% failed (between 11.0 & 3.0 above LES)MapSwallowsNum6","0");
		mapAllSwallowExtractorRefe.put("% failed (between 11.0 & 3.0 above LES)MapSwallowsNum7","1");;
		mapAllSwallowExtractorRefe.put("% failed (between 11.0 & 3.0 above LES)MapSwallowsNum8","0");
		mapAllSwallowExtractorRefe.put("% failed (between 11.0 & 3.0 above LES)MapSwallowsNum9","1");
		mapAllSwallowExtractorRefe.put("% failed (between 11.0 & 3.0 above LES)MapSwallowsNum10","1");
		mapAllSwallowExtractorRefe.put("% failed (between 11.0 & 3.0 above LES)MapSwallowsNum11","0");
		mapAllSwallowExtractorRefe.put("Double peaked swallows (% of Swallows)MapSwallowsNum1","0");
		mapAllSwallowExtractorRefe.put("Double peaked swallows (% of Swallows)MapSwallowsNum2","0");
		mapAllSwallowExtractorRefe.put("Double peaked swallows (% of Swallows)MapSwallowsNum3","0");
		mapAllSwallowExtractorRefe.put("Double peaked swallows (% of Swallows)MapSwallowsNum4","0");
		mapAllSwallowExtractorRefe.put("Double peaked swallows (% of Swallows)MapSwallowsNum5","0");
		mapAllSwallowExtractorRefe.put("Double peaked swallows (% of Swallows)MapSwallowsNum6","0");
		mapAllSwallowExtractorRefe.put("Double peaked swallows (% of Swallows)MapSwallowsNum7","N/A");
		mapAllSwallowExtractorRefe.put("Double peaked swallows (% of Swallows)MapSwallowsNum8","0");
		mapAllSwallowExtractorRefe.put("Double peaked swallows (% of Swallows)MapSwallowsNum9","N/A");
		mapAllSwallowExtractorRefe.put("Double peaked swallows (% of Swallows)MapSwallowsNum10","0");
		mapAllSwallowExtractorRefe.put("Double peaked swallows (% of Swallows)MapSwallowsNum11","0");
		mapAllSwallowExtractorRefe.put("Triple peaked swallows (% of Swallows)MapSwallowsNum1","0");
		mapAllSwallowExtractorRefe.put("Triple peaked swallows (% of Swallows)MapSwallowsNum2","0");
		mapAllSwallowExtractorRefe.put("Triple peaked swallows (% of Swallows)MapSwallowsNum3","0");
		mapAllSwallowExtractorRefe.put("Triple peaked swallows (% of Swallows)MapSwallowsNum4","0");
		mapAllSwallowExtractorRefe.put("Triple peaked swallows (% of Swallows)MapSwallowsNum5","0");
		mapAllSwallowExtractorRefe.put("Triple peaked swallows (% of Swallows)MapSwallowsNum6","0");
		mapAllSwallowExtractorRefe.put("Triple peaked swallows (% of Swallows)MapSwallowsNum7","N/A");
		mapAllSwallowExtractorRefe.put("Triple peaked swallows (% of Swallows)MapSwallowsNum8","0");
		mapAllSwallowExtractorRefe.put("Triple peaked swallows (% of Swallows)MapSwallowsNum9","N/A");
		mapAllSwallowExtractorRefe.put("Triple peaked swallows (% of Swallows)MapSwallowsNum10","0");
		mapAllSwallowExtractorRefe.put("Triple peaked swallows (% of Swallows)MapSwallowsNum11","0");
		mapAllSwallowExtractorRefe.put("Distal contractile integral (mmHg-cm-s)MapSwallowsNum1","369.9");
		mapAllSwallowExtractorRefe.put("Distal contractile integral (mmHg-cm-s)MapSwallowsNum2","235.4");
		mapAllSwallowExtractorRefe.put("Distal contractile integral (mmHg-cm-s)MapSwallowsNum3","169.3");
		mapAllSwallowExtractorRefe.put("Distal contractile integral (mmHg-cm-s)MapSwallowsNum4","103.2");
		mapAllSwallowExtractorRefe.put("Distal contractile integral (mmHg-cm-s)MapSwallowsNum5","691.5");
		mapAllSwallowExtractorRefe.put("Distal contractile integral (mmHg-cm-s)MapSwallowsNum6","218.6");
		mapAllSwallowExtractorRefe.put("Distal contractile integral (mmHg-cm-s)MapSwallowsNum7","N/A");
		mapAllSwallowExtractorRefe.put("Distal contractile integral (mmHg-cm-s)MapSwallowsNum8","857.7");
		mapAllSwallowExtractorRefe.put("Distal contractile integral (mmHg-cm-s)MapSwallowsNum9","N/A");
		mapAllSwallowExtractorRefe.put("Distal contractile integral (mmHg-cm-s)MapSwallowsNum10","202.0");
		mapAllSwallowExtractorRefe.put("Distal contractile integral (mmHg-cm-s)MapSwallowsNum11","481.8");
		mapAllSwallowExtractorRefe.put("Intrabolus pressure (@LESR,mmHg)MapSwallowsNum1","1.1");
		mapAllSwallowExtractorRefe.put("Intrabolus pressure (@LESR,mmHg)MapSwallowsNum2","1.1");
		mapAllSwallowExtractorRefe.put("Intrabolus pressure (@LESR,mmHg)MapSwallowsNum3","4.0");
		mapAllSwallowExtractorRefe.put("Intrabolus pressure (@LESR,mmHg)MapSwallowsNum4","1.4");
		mapAllSwallowExtractorRefe.put("Intrabolus pressure (@LESR,mmHg)MapSwallowsNum5","1.9");
		mapAllSwallowExtractorRefe.put("Intrabolus pressure (@LESR,mmHg)MapSwallowsNum6","0.6");
		mapAllSwallowExtractorRefe.put("Intrabolus pressure (@LESR,mmHg)MapSwallowsNum7","N/A") ;
		mapAllSwallowExtractorRefe.put("Intrabolus pressure (avg max,mmHg)MapSwallowsNum1","14.6");
		mapAllSwallowExtractorRefe.put("Intrabolus pressure (avg max,mmHg)MapSwallowsNum2","15.7");
		mapAllSwallowExtractorRefe.put("Intrabolus pressure (avg max,mmHg)MapSwallowsNum3","16.0");
		mapAllSwallowExtractorRefe.put("Intrabolus pressure (avg max,mmHg)MapSwallowsNum4","16.7");
		mapAllSwallowExtractorRefe.put("Intrabolus pressure (avg max,mmHg)MapSwallowsNum5","14.1");
		mapAllSwallowExtractorRefe.put("Intrabolus pressure (avg max,mmHg)MapSwallowsNum6","13.1");
		mapAllSwallowExtractorRefe.put("Intrabolus pressure (avg max,mmHg)MapSwallowsNum7","N/A");
		mapAllSwallowExtractorRefe.put("Intrabolus pressure (avg max,mmHg)MapSwallowsNum8","14.5");
		mapAllSwallowExtractorRefe.put("Intrabolus pressure (avg max,mmHg)MapSwallowsNum9","N/A");
		mapAllSwallowExtractorRefe.put("Intrabolus pressure (avg max,mmHg)MapSwallowsNum10","12.9");
		mapAllSwallowExtractorRefe.put("Intrabolus pressure (avg max,mmHg)MapSwallowsNum11","13.9");
		mapAllSwallowExtractorRefe.put("Distal latency (sec)MapSwallowsNum1","5.1");
		mapAllSwallowExtractorRefe.put("Distal latency (sec)MapSwallowsNum2","4.5");
		mapAllSwallowExtractorRefe.put("Distal latency (sec)MapSwallowsNum3","3.8");
		mapAllSwallowExtractorRefe.put("Distal latency (sec)MapSwallowsNum4","4.0");
		mapAllSwallowExtractorRefe.put("Distal latency (sec)MapSwallowsNum5","5.6");
		mapAllSwallowExtractorRefe.put("Distal latency (sec)MapSwallowsNum6","5.9");
		mapAllSwallowExtractorRefe.put("Distal latency (sec)MapSwallowsNum7","N/A");
		mapAllSwallowExtractorRefe.put("Distal latency (sec)MapSwallowsNum8","5.9");
		mapAllSwallowExtractorRefe.put("Distal latency (sec)MapSwallowsNum9","N/A");
		mapAllSwallowExtractorRefe.put("Distal latency (sec)MapSwallowsNum10","5.5");
		mapAllSwallowExtractorRefe.put("% failed (Chicago Classification)MapSwallowsNum1","20");
		mapAllSwallowExtractorRefe.put("% failed (Chicago Classification)MapSwallowsNum2","0");
		mapAllSwallowExtractorRefe.put("% failed (Chicago Classification)MapSwallowsNum3","0");
		mapAllSwallowExtractorRefe.put("% failed (Chicago Classification)MapSwallowsNum4","0");
		mapAllSwallowExtractorRefe.put("% failed (Chicago Classification)MapSwallowsNum5","0");
		mapAllSwallowExtractorRefe.put("% failed (Chicago Classification)MapSwallowsNum6","0");
		mapAllSwallowExtractorRefe.put("% failed (Chicago Classification)MapSwallowsNum7","1");
		mapAllSwallowExtractorRefe.put("% failed (Chicago Classification)MapSwallowsNum8","0");
		mapAllSwallowExtractorRefe.put("% failed (Chicago Classification)MapSwallowsNum9","1");
		mapAllSwallowExtractorRefe.put("% failed (Chicago Classification)MapSwallowsNum10","0");
		mapAllSwallowExtractorRefe.put("% failed (Chicago Classification)MapSwallowsNum11","0");
		mapAllSwallowExtractorRefe.put("% panesophageal pressurizationMapSwallowsNum1","0");
		mapAllSwallowExtractorRefe.put("% panesophageal pressurizationMapSwallowsNum2","0");
		mapAllSwallowExtractorRefe.put("% panesophageal pressurizationMapSwallowsNum3","0");
		mapAllSwallowExtractorRefe.put("% panesophageal pressurizationMapSwallowsNum4","0");
		mapAllSwallowExtractorRefe.put("% panesophageal pressurizationMapSwallowsNum5","0");
		mapAllSwallowExtractorRefe.put("% panesophageal pressurizationMapSwallowsNum6","0");
		mapAllSwallowExtractorRefe.put("% panesophageal pressurizationMapSwallowsNum7","0");
		mapAllSwallowExtractorRefe.put("% panesophageal pressurizationMapSwallowsNum8","0");
		mapAllSwallowExtractorRefe.put("% panesophageal pressurizationMapSwallowsNum9","0");
		mapAllSwallowExtractorRefe.put("% panesophageal pressurizationMapSwallowsNum10","0");
		mapAllSwallowExtractorRefe.put("% panesophageal pressurizationMapSwallowsNum11","0");
		mapAllSwallowExtractorRefe.put("% premature contractionMapSwallowsNum1","30");
		mapAllSwallowExtractorRefe.put("% premature contractionMapSwallowsNum2","1");
		mapAllSwallowExtractorRefe.put("% premature contractionMapSwallowsNum3","1");
		mapAllSwallowExtractorRefe.put("% premature contractionMapSwallowsNum4","1");
		mapAllSwallowExtractorRefe.put("% premature contractionMapSwallowsNum5","0");
		mapAllSwallowExtractorRefe.put("% premature contractionMapSwallowsNum6","0");
		mapAllSwallowExtractorRefe.put("% premature contractionMapSwallowsNum7","0");
		mapAllSwallowExtractorRefe.put("% premature contractionMapSwallowsNum8","0");
		mapAllSwallowExtractorRefe.put("% premature contractionMapSwallowsNum9","0");
		mapAllSwallowExtractorRefe.put("% premature contractionMapSwallowsNum10","0");
		mapAllSwallowExtractorRefe.put("% premature contractionMapSwallowsNum11","0");
		mapAllSwallowExtractorRefe.put("% rapid contractionMapSwallowsNum1","20");
		mapAllSwallowExtractorRefe.put("% rapid contractionMapSwallowsNum2","0");
		mapAllSwallowExtractorRefe.put("% rapid contractionMapSwallowsNum3","1");
		mapAllSwallowExtractorRefe.put("% rapid contractionMapSwallowsNum4","0");
		mapAllSwallowExtractorRefe.put("% rapid contractionMapSwallowsNum5","0");
		mapAllSwallowExtractorRefe.put("% rapid contractionMapSwallowsNum6","0");
		mapAllSwallowExtractorRefe.put("% rapid contractionMapSwallowsNum7","0");
		mapAllSwallowExtractorRefe.put("% rapid contractionMapSwallowsNum8","0");
		mapAllSwallowExtractorRefe.put("% rapid contractionMapSwallowsNum9","0");
		mapAllSwallowExtractorRefe.put("% rapid contractionMapSwallowsNum10","1");
		mapAllSwallowExtractorRefe.put("% rapid contractionMapSwallowsNum11","0");
		mapAllSwallowExtractorRefe.put("% large breaksMapSwallowsNum1","10");
		mapAllSwallowExtractorRefe.put("% large breaksMapSwallowsNum2","0");
		mapAllSwallowExtractorRefe.put("% large breaksMapSwallowsNum3","0");
		mapAllSwallowExtractorRefe.put("% large breaksMapSwallowsNum4","1");
		mapAllSwallowExtractorRefe.put("% large breaksMapSwallowsNum5","0");
		mapAllSwallowExtractorRefe.put("% large breaksMapSwallowsNum6","0");
		mapAllSwallowExtractorRefe.put("% large breaksMapSwallowsNum7","0");
		mapAllSwallowExtractorRefe.put("% large breaksMapSwallowsNum8","0");
		mapAllSwallowExtractorRefe.put("% large breaksMapSwallowsNum9","0");
		mapAllSwallowExtractorRefe.put("% large breaksMapSwallowsNum10","0");
		mapAllSwallowExtractorRefe.put("% large breaksMapSwallowsNum11","0");
		mapAllSwallowExtractorRefe.put("% small breaksMapSwallowsNum1","60");
		mapAllSwallowExtractorRefe.put("% small breaksMapSwallowsNum2","1");
		mapAllSwallowExtractorRefe.put("% small breaksMapSwallowsNum3","1");
		mapAllSwallowExtractorRefe.put("% small breaksMapSwallowsNum4","0");
		mapAllSwallowExtractorRefe.put("% small breaksMapSwallowsNum5","1");
		mapAllSwallowExtractorRefe.put("% small breaksMapSwallowsNum6","1");
		mapAllSwallowExtractorRefe.put("% small breaksMapSwallowsNum7","0");
		mapAllSwallowExtractorRefe.put("% small breaksMapSwallowsNum8","0");
		mapAllSwallowExtractorRefe.put("% small breaksMapSwallowsNum9","0");
		mapAllSwallowExtractorRefe.put("% small breaksMapSwallowsNum10","1");
		mapAllSwallowExtractorRefe.put("% small breaksMapSwallowsNum11","1");
		mapAllSwallowExtractorRefe.put("High-ResolutionEsophagealMotilityStudyMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("GivenImagingMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("TechnicalSupportMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("LosAngeles,CAMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("SampleSiteInfoDescriptionMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Patient:MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("STEKELENBURG,JoanMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("XMapSwallowsNum1","2392420");
		mapAllSwallowExtractorRefe.put("Gender:MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("FemaleMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Physician:MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("WongTerryDrMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("DOB/Age:MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Operator:MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("DRJAFARJAFARIMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Height:MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("ReferringPhysician:MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Procedure:MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("OesophagealManometryMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("ExaminationDate:MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("SwallowComposite(meanof10swallows)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("RestingPressureProfile&AnatomyMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("BasalPressures*MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("LES,respiratorymean(mmHg)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("UESmean(mmHg)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Anatomy*MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("LESproximal(cm)MapSwallowsNum1","40.2");
		mapAllSwallowExtractorRefe.put("LESintraabdominal(cm)MapSwallowsNum1","0.0");
		mapAllSwallowExtractorRefe.put("Esophageallength(cm)MapSwallowsNum1","22.0");
		mapAllSwallowExtractorRefe.put("HiatalherniaMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Yes,MapSwallowsNum1","0.9");
		mapAllSwallowExtractorRefe.put("Motility*MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Dist.waveamplitude(mmHg)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Wavedur.@LES-3.0&7.0(s)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Onsetvel.(LES-11.0to-3.0)(cm/s)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Percentperistaltic(%)MapSwallowsNum1","40");
		mapAllSwallowExtractorRefe.put("Percentsimultaneous(%)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Percentfailed(%)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Distalcontr.integral(mmHg-cm-s)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("ResidualPressures*MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("LES(mean)(mmHg)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("UES(mean)(mmHg)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Notes.Motilityvaluesaremeanamongswallows;Normalvaluesin(xxx.x):Simultaneouscontractions:Velocity>8.0cm/s;eSlv:eSleeve;3SN,IRP,DCI,IBP-SeemanualdefinitionsMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("LowerEsophagealSphincterRegionMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("NormalMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("EsophagealMotilityMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("LandmarksMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("NumberofswallowsevaluatedMapSwallowsNum1","10");
		mapAllSwallowExtractorRefe.put("ProximalLES(fromnares)(cm)MapSwallowsNum1","40.2");
		mapAllSwallowExtractorRefe.put("Evaluated@3.0-11.0aboveLESMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("LESlength(cm)MapSwallowsNum1","2.6");
		mapAllSwallowExtractorRefe.put("Peristaltic(velocity≤6.25cm/s)(%)MapSwallowsNum1","40");
		mapAllSwallowExtractorRefe.put("Esophageallength(LES-UEScenters)(cm)MapSwallowsNum1","22.0");
		mapAllSwallowExtractorRefe.put("Simultaneous(vel.≥6.25cm/s)(%)MapSwallowsNum1","20");
		mapAllSwallowExtractorRefe.put("IntraabdominalLESlength(cm)MapSwallowsNum1","0.0");
		mapAllSwallowExtractorRefe.put("Failed(%)MapSwallowsNum1","40");
		mapAllSwallowExtractorRefe.put("Hiatalhernia?MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Evaluated@3.0&7.0aboveLESMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("LESPressuresMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Meanwaveamplitude(mmHg)MapSwallowsNum1","50.6");
		mapAllSwallowExtractorRefe.put("Pressuremeas.methodMapSwallowsNum1", "");
		mapAllSwallowExtractorRefe.put("eSleeve,IRPMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Meanwaveduration(s)MapSwallowsNum1","3.1");
		mapAllSwallowExtractorRefe.put("Basal(respiratorymin.)(mmHg)MapSwallowsNum1","13.8");
		mapAllSwallowExtractorRefe.put("Double-peakedwaves(%)MapSwallowsNum1","0");
		mapAllSwallowExtractorRefe.put("Basal(respiratorymean)(mmHg)MapSwallowsNum1","19.2");
		mapAllSwallowExtractorRefe.put("Triple-peakedwaves(%)MapSwallowsNum1","0");
		mapAllSwallowExtractorRefe.put("Residual(mean)(mmHg)MapSwallowsNum1","10.4");
		mapAllSwallowExtractorRefe.put("Velocity(11.0-3.0aboveLES)(cm/s)MapSwallowsNum1","21.1");
		mapAllSwallowExtractorRefe.put("HighResolutionParametersMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Distalcontractileintegral(mean)(mmHg-cm-s)MapSwallowsNum1","369.9");
		mapAllSwallowExtractorRefe.put("Contractilefrontvelocity(cm/s)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Intraboluspressure(@LESR)(mmHg)MapSwallowsNum1","1.1");
		mapAllSwallowExtractorRefe.put("Intraboluspressure(avgmax)(mmHg)MapSwallowsNum1","14.6");
		mapAllSwallowExtractorRefe.put("ChicagoClassificationMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("DistallatencyMapSwallowsNum1","5.1");
		mapAllSwallowExtractorRefe.put("failed(ChicagoClassification)MapSwallowsNum1","20");
		mapAllSwallowExtractorRefe.put("panesophagealpressurizationMapSwallowsNum1","0");
		mapAllSwallowExtractorRefe.put("prematurecontractionMapSwallowsNum1","30");
		mapAllSwallowExtractorRefe.put("rapidcontractionMapSwallowsNum1","20");
		mapAllSwallowExtractorRefe.put("largebreaksMapSwallowsNum1","10");
		mapAllSwallowExtractorRefe.put("smallbreaksMapSwallowsNum1","60");
		mapAllSwallowExtractorRefe.put("UpperEsophagealSphincterMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Pharyngeal/UESMotilityMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Meanbasalpressure(mmHg)MapSwallowsNum1","41.3");
		mapAllSwallowExtractorRefe.put("No.swallowsevaluatedMapSwallowsNum1","10");
		mapAllSwallowExtractorRefe.put("Meanresidualpressure(mmHg)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Evaluated@3.0&N/AaboveUESMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Meanpeakpressure(mmHg)MapSwallowsNum1","10.6");
		mapAllSwallowExtractorRefe.put("ProcedureMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("IndicationsMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Interpretation/FindingsMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("ImpressionsMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Group/MeanMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Swal.MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("ESOPHAGEALMOTILITYMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("LESresidualpressure(mmHg)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("ClassicManoViewMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Waveamplitude(mean,3.0&7.0aboveLES)(mmHg)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("N/AMapSwallowsNum1","14.5");
		mapAllSwallowExtractorRefe.put("N/AMapSwallowsNum2","N/A");
		mapAllSwallowExtractorRefe.put("N/AMapSwallowsNum3","12.9");
		mapAllSwallowExtractorRefe.put("N/AMapSwallowsNum4","13.9");
		mapAllSwallowExtractorRefe.put("Waveduration(meanat3.0&7.0aboveLES)(sec)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Onsetvelocity(between11.0&3.0aboveLES)(cm/s)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("peristaltic(between11.0&3.0aboveLES)MapSwallowsNum1","40");
		mapAllSwallowExtractorRefe.put("peristaltic(between11.0&3.0aboveLES)MapSwallowsNum2","1");
		mapAllSwallowExtractorRefe.put("peristaltic(between11.0&3.0aboveLES)MapSwallowsNum3","0");
		mapAllSwallowExtractorRefe.put("peristaltic(between11.0&3.0aboveLES)MapSwallowsNum4","0");
		mapAllSwallowExtractorRefe.put("peristaltic(between11.0&3.0aboveLES)MapSwallowsNum5","1");
		mapAllSwallowExtractorRefe.put("peristaltic(between11.0&3.0aboveLES)MapSwallowsNum6","1");
		mapAllSwallowExtractorRefe.put("peristaltic(between11.0&3.0aboveLES)MapSwallowsNum7","0");
		mapAllSwallowExtractorRefe.put("peristaltic(between11.0&3.0aboveLES)MapSwallowsNum8","1");
		mapAllSwallowExtractorRefe.put("peristaltic(between11.0&3.0aboveLES)MapSwallowsNum9","0");
		mapAllSwallowExtractorRefe.put("peristaltic(between11.0&3.0aboveLES)MapSwallowsNum10","0");
		mapAllSwallowExtractorRefe.put("peristaltic(between11.0&3.0aboveLES)MapSwallowsNum11","0");
		mapAllSwallowExtractorRefe.put("simultaneous(between11.0&3.0aboveLES)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("failed(between11.0&3.0aboveLES)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Doublepeakedswallows(%ofSwallows)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Triplepeakedswallows(%ofSwallows)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Distalcontractileintegral(mmHg-cm-s)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Intraboluspressure(@LESR,mmHg)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Intraboluspressure(avgmax,mmHg)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("Distallatency(sec)MapSwallowsNum1","5.1");
		mapAllSwallowExtractorRefe.put("Distallatency(sec)MapSwallowsNum2","4.5");
		mapAllSwallowExtractorRefe.put("Distallatency(sec)MapSwallowsNum3","3.8");
		mapAllSwallowExtractorRefe.put("Distallatency(sec)MapSwallowsNum4","4.0");
		mapAllSwallowExtractorRefe.put("Distallatency(sec)MapSwallowsNum5","5.6");
		mapAllSwallowExtractorRefe.put("Distallatency(sec)MapSwallowsNum6","5.9");
		mapAllSwallowExtractorRefe.put("Distallatency(sec)MapSwallowsNum7","N/A");
		mapAllSwallowExtractorRefe.put("Distallatency(sec)MapSwallowsNum8","5.9");
		mapAllSwallowExtractorRefe.put("Distallatency(sec)MapSwallowsNum9","N/A");
		mapAllSwallowExtractorRefe.put("Distallatency(sec)MapSwallowsNum10","5.5");
		mapAllSwallowExtractorRefe.put("failed(ChicagoClassification)MapSwallowsNum2","0");
		mapAllSwallowExtractorRefe.put("failed(ChicagoClassification)MapSwallowsNum3","0");
		mapAllSwallowExtractorRefe.put("failed(ChicagoClassification)MapSwallowsNum4","0");
		mapAllSwallowExtractorRefe.put("failed(ChicagoClassification)MapSwallowsNum5","0");
		mapAllSwallowExtractorRefe.put("failed(ChicagoClassification)MapSwallowsNum6","0");
		mapAllSwallowExtractorRefe.put("failed(ChicagoClassification)MapSwallowsNum7","1");
		mapAllSwallowExtractorRefe.put("failed(ChicagoClassification)MapSwallowsNum8","0");
		mapAllSwallowExtractorRefe.put("failed(ChicagoClassification)MapSwallowsNum9","1");
		mapAllSwallowExtractorRefe.put("failed(ChicagoClassification)MapSwallowsNum10","0");
		mapAllSwallowExtractorRefe.put("failed(ChicagoClassification)MapSwallowsNum11","0");
		mapAllSwallowExtractorRefe.put("panesophagealpressurizationMapSwallowsNum2","0");
		mapAllSwallowExtractorRefe.put("panesophagealpressurizationMapSwallowsNum3","0");
		mapAllSwallowExtractorRefe.put("panesophagealpressurizationMapSwallowsNum4","0");
		mapAllSwallowExtractorRefe.put("panesophagealpressurizationMapSwallowsNum5","0");
		mapAllSwallowExtractorRefe.put("panesophagealpressurizationMapSwallowsNum6","0");
		mapAllSwallowExtractorRefe.put("panesophagealpressurizationMapSwallowsNum7","0");
		mapAllSwallowExtractorRefe.put("panesophagealpressurizationMapSwallowsNum8","0");
		mapAllSwallowExtractorRefe.put("panesophagealpressurizationMapSwallowsNum9","0");;
		mapAllSwallowExtractorRefe.put("panesophagealpressurizationMapSwallowsNum10","0");
		mapAllSwallowExtractorRefe.put("panesophagealpressurizationMapSwallowsNum11","0");
		mapAllSwallowExtractorRefe.put("prematurecontractionMapSwallowsNum2","1");
		mapAllSwallowExtractorRefe.put("prematurecontractionMapSwallowsNum3","1");
		mapAllSwallowExtractorRefe.put("prematurecontractionMapSwallowsNum4","1");
		mapAllSwallowExtractorRefe.put("prematurecontractionMapSwallowsNum5","0");
		mapAllSwallowExtractorRefe.put("prematurecontractionMapSwallowsNum6","0");
		mapAllSwallowExtractorRefe.put("prematurecontractionMapSwallowsNum7","0");
		mapAllSwallowExtractorRefe.put("prematurecontractionMapSwallowsNum8","0");
		mapAllSwallowExtractorRefe.put("prematurecontractionMapSwallowsNum9","0");
		mapAllSwallowExtractorRefe.put("prematurecontractionMapSwallowsNum10","0");
		mapAllSwallowExtractorRefe.put("prematurecontractionMapSwallowsNum11","0");
		mapAllSwallowExtractorRefe.put("rapidcontractionMapSwallowsNum2","0");
		mapAllSwallowExtractorRefe.put("rapidcontractionMapSwallowsNum3","1");
		mapAllSwallowExtractorRefe.put("rapidcontractionMapSwallowsNum4","0");
		mapAllSwallowExtractorRefe.put("rapidcontractionMapSwallowsNum5","0");
		mapAllSwallowExtractorRefe.put("rapidcontractionMapSwallowsNum6","0");
		mapAllSwallowExtractorRefe.put("rapidcontractionMapSwallowsNum7","0");;
		mapAllSwallowExtractorRefe.put("rapidcontractionMapSwallowsNum8","0");
		mapAllSwallowExtractorRefe.put("rapidcontractionMapSwallowsNum9","0");
		mapAllSwallowExtractorRefe.put("rapidcontractionMapSwallowsNum10","1");
		mapAllSwallowExtractorRefe.put("rapidcontractionMapSwallowsNum11","0");
		mapAllSwallowExtractorRefe.put("largebreaksMapSwallowsNum2","0");
		mapAllSwallowExtractorRefe.put("largebreaksMapSwallowsNum3","0");
		mapAllSwallowExtractorRefe.put("largebreaksMapSwallowsNum4","1");
		mapAllSwallowExtractorRefe.put("largebreaksMapSwallowsNum5","0");
		mapAllSwallowExtractorRefe.put("largebreaksMapSwallowsNum6","0");
		mapAllSwallowExtractorRefe.put("largebreaksMapSwallowsNum7","0");
		mapAllSwallowExtractorRefe.put("largebreaksMapSwallowsNum8","0");
		mapAllSwallowExtractorRefe.put("largebreaksMapSwallowsNum9","0");
		mapAllSwallowExtractorRefe.put("largebreaksMapSwallowsNum10","0");
		mapAllSwallowExtractorRefe.put("largebreaksMapSwallowsNum11","0");
		mapAllSwallowExtractorRefe.put("smallbreaksMapSwallowsNum2","1");
		mapAllSwallowExtractorRefe.put("smallbreaksMapSwallowsNum3","1");
		mapAllSwallowExtractorRefe.put("smallbreaksMapSwallowsNum4","0");
		mapAllSwallowExtractorRefe.put("smallbreaksMapSwallowsNum5","1");
		mapAllSwallowExtractorRefe.put("smallbreaksMapSwallowsNum6","1");
		mapAllSwallowExtractorRefe.put("smallbreaksMapSwallowsNum7","0");
		mapAllSwallowExtractorRefe.put("smallbreaksMapSwallowsNum8","0");
		mapAllSwallowExtractorRefe.put("smallbreaksMapSwallowsNum9","0");
		mapAllSwallowExtractorRefe.put("smallbreaksMapSwallowsNum10","1");
		mapAllSwallowExtractorRefe.put("smallbreaksMapSwallowsNum11","1");
		mapAllSwallowExtractorRefe.put("UES/PHARYNGEALMOTILITYMapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("UESresidualpressure(mean)(mmHg)MapSwallowsNum1","");
		mapAllSwallowExtractorRefe.put("STEKELENBURGJoan,X2392420,03/21/MapSwallowsNum1","2016");
		//mapAllSwallowExtractorRefe.put("2016","");

		String s=GetHRMFile(child_str);
		mapAllSwallowExtractorTest=HRMAll.SwallowExtractor(s);
		System.out.println("mapAllSwallowExtractorTest "+mapAllSwallowExtractorTest);
		System.out.println("mapAllSwallowExtractorRefe "+mapAllSwallowExtractorRefe);

//Find what is different about the two maps test and reference
		Set<String> removedKeys = new HashSet<String>(mapAllSwallowExtractorRefe.keySet());
		removedKeys.removeAll(mapAllSwallowExtractorTest.keySet());

		Set<String> addedKeys = new HashSet<String>(mapAllSwallowExtractorTest.keySet());
		addedKeys.removeAll(mapAllSwallowExtractorRefe.keySet());

		Set<Entry<String, String>> changedEntries = new HashSet<Entry<String, String>>(
				mapAllSwallowExtractorTest.entrySet());
		changedEntries.removeAll(mapAllSwallowExtractorRefe.entrySet());

		System.out.println("added " + addedKeys);
		System.out.println("removed " + removedKeys);
		System.out.println("changed " + changedEntries);
		assertEquals(mapAllSwallowExtractorTest,mapAllSwallowExtractorRefe);

	}

}

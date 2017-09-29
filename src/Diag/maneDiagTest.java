package Diag;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.Test;
import org.xml.sax.SAXException;

import HRM.HRMAll;

public class maneDiagTest {
//remove this comment- just for github.
	File child = new File("//Users//sebastianzeki//Documents//PhysJava/Dumper_final//Trial//MEDAIYESE Esther_1392879Q_28042016_Final report.pdf");
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
	public static String getFileCreationDate(File child) throws IOException {
		String FileCreationTime=null;
		Path path = Paths.get(child.getAbsolutePath());
        BasicFileAttributes attr;
        attr = Files.readAttributes(path, BasicFileAttributes.class);
        FileCreationTime= attr.creationTime().toString();

		return(FileCreationTime);

	}

	@Test
	public void test() throws IOException, SAXException, TikaException, SQLException, ParseException {
		Map<String,String> mapAllReportReference= new LinkedHashMap<String,String>();
		Map<String,String>  mapAllReportTest = new LinkedHashMap<String,String>();
		String s=GetHRMFile(child_str);
		String fileCreationDate=getFileCreationDate(child);
		 mapAllReportReference.put("HospNum_Id","1392879Q");
		 mapAllReportReference.put("FileCreationDate","2016-05-03T17:11:22Z");
		 mapAllReportReference.put("FName","Esther");
		 mapAllReportReference.put("SName","MEDAIYESE");
		 mapAllReportReference.put("VisitDate","28_04_2016");
		 mapAllReportReference.put("DOB","31.05.1983");
		 mapAllReportReference.put("PPI","Nil found");
		  mapAllReportReference.put("RDQScore","N/A");
		   mapAllReportReference.put("HODQScore","N/A /50");

		mapAllReportTest=Diag.mane.Dimane(s, child_str,fileCreationDate);
		assertEquals(mapAllReportTest.get("DOB"),mapAllReportReference.get("DOB"));
		assertEquals(mapAllReportTest.get("HospNum_Id"),mapAllReportReference.get("HospNum_Id"));
		assertEquals(mapAllReportTest.get("FileCreationDate"),mapAllReportReference.get("FileCreationDate"));
		assertEquals(mapAllReportTest.get("FName"),mapAllReportReference.get("FName"));
		System.out.println(mapAllReportTest.get("VisitDate"));
		System.out.println(mapAllReportReference.get("VisitDate"));
		//This isnt included as VisitDate isnt part of the map but is extracted seperately in this map
		//assertEquals(mapAllReportTest.get("VisitDate"),mapAllReportReference.get("VisitDate"));
	}

}

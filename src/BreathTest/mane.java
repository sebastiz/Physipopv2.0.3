package BreathTest;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.tika.exception.TikaException;

import Overview.Checkers;
import Overview.DBConnectorForAll;
import org.pmw.tinylog.Logger;


public class mane {
public static String HospNum=null;
public static String FName=null;
public static String SName=null;
public static String DOB=null;
public static Map<String,String> mapAll= new LinkedHashMap<String,String>();
public static Map<String,String> mapPtDataBT= new LinkedHashMap<String,String>();
public static String VisitDate=null;
String s=null;
int sharedkey;

	public mane() {

		PDFont.clearResources();
		System.gc();
	}

	public void Gomane(String s,String child,String FileCreationDate) throws IOException, TikaException, SQLException {
        try {
        	GraphExtractor ge = new GraphExtractor();
        	mapAll.clear();
        	mapAll.putAll(ge.ExtractValues(child));

        	//System.out.println(mapAll);
        	//Need to get ExtractValues to return a map so can be mapAll.put to it
        	//Need to split a word document up
        	try {


			//s=s;
				//s=s.replaceAll("\\p{C}", "_");
				//s=s.replaceAll("__", "_");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



        	try {
                HospNum=Overview.Searcher.HospNo_searcher(s);
                if(HospNum==null||HospNum==""||HospNum.isEmpty()||HospNum.equals("0207188419")){
                	HospNum=Overview.Searcher.HospNo_searcher(child.toString());
        			 }



        			} catch (Exception e2) {
        				// TODO Auto-generated catch block
        				Logger.error(e2+"Couldn't get the hospital number-is this a private patient"+child);
        			}


		} catch (Exception e4) {

		}

        BreathTestExtractorMethods BT =new BreathTestExtractorMethods(HospNum);


        try {
        	FName =BT.FNameExtractor(s);
        	mapAll.put("FName",FName);
		} catch (Exception e3) {
			e3.printStackTrace();
		}

        mapAll.put("FileCreationDate",FileCreationDate);

        try {
        	SName =BT.SNameExtractor(s).replace("'", "");
        	//System.out.println("TheMainSName"+SName);
        	mapAll.put("SName",SName);
		} catch (Exception e3) {

			e3.printStackTrace();
		}
        try {
        	DOB =	BT.DOBExtractor(s);
        	mapAll.put("DOB", DOB);
        	//System.out.println("TheMainDOB"+DOB);
		} catch (Exception e2) {

			e2.printStackTrace();
		}
        try {
        	VisitDate =	BT.VisitDateExtractor(s);

        	mapAll.put("VisitDate", VisitDate);
        	//System.out.println("This is the VisitDateMapAll"+mapAll);
		} catch (Exception e1) {

			e1.printStackTrace();
		}
        try {
        	mapAll.putAll(BT.ReqPhysExtractor(s));

		} catch (Exception e1) {
			e1.printStackTrace();
		}

        try {
        	mapAll.putAll(BT.LactoseTestExtractor(s));
		} catch (Exception e) {
			e.printStackTrace();
		}
        try {
        	mapAll.putAll(BT.LactuloseTestExtractor(s));
		} catch (Exception e) {

			e.printStackTrace();
		}
        try {
        	mapAll.putAll(BT.SucroseTestExtractor(s));
		} catch (Exception e4) {

			e4.printStackTrace();
		}
        try {
        	mapAll.putAll(BT.UreaTestExtractor(s));
		} catch (Exception e3) {

			e3.printStackTrace();
		}
        try {
        	mapAll.putAll(BT.FructoseTestExtractor(s));
		} catch (Exception e2) {

			e2.printStackTrace();
		}
        try {
        	mapAll.putAll(BT.ReqPhysExtractor(s));
		} catch (Exception e1) {

			e1.printStackTrace();
		}
        try {
        	BT.UreaTestExtractor(s);
		} catch (Exception e) {

			e.printStackTrace();
		}



        for(Iterator<Map.Entry<String, String>> it = mapAll.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, String> entry = it.next();
            if(entry.getValue()==null||entry.getValue().isEmpty()) {

              it.remove();
            }




          }

        try {
			String tab="BreathTests";
			DBConnectorForAll ConnectMeUp = new DBConnectorForAll();
			Statement st=ConnectMeUp.Connector(HospNum,FName,SName,DOB);
			String first=ConnectMeUp.StringInsertKeyPreparer(st,mapAll,tab);
			String second=ConnectMeUp.StringInsertValuePreparer(st,mapAll,tab);
				if (!Checkers.VisitDateChecker(st,tab,HospNum).contains(VisitDate)){
			ConnectMeUp.Inserter(st,HospNum,first,second,tab,child);
			//mapAll=null;
			//st.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logger.error(e+HospNum+"->From BreathTests"+child);
		}
		}


	}


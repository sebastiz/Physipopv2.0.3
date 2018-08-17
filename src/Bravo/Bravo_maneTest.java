package Bravo;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.Test;
import org.pmw.tinylog.Logger;
import org.xml.sax.SAXException;

import Overview.Checkers;
import Overview.Iterator;

public class Bravo_maneTest {

	String docSlim=null;
	String day=null;
	String [] seTab3Landmarks=null;
	String s=null;
	File child = new File("/Users/sebastianzeki/Documents/PhysJava/StableValidationFolder/BYRNE Jeremy_25052016_5822359F_BRAVO.doc");
	String child_str=child.getPath();

	public String GetFile() throws IOException, SAXException, TikaException, URISyntaxException{


		//This is the setup
		//Iterator.loopthrough("/Users/sebastianzeki/Documents/PhysJava/StableValidationFolder");
		AutoDetectParser parser = new AutoDetectParser();
		BodyContentHandler handler = new BodyContentHandler(-1);
		Metadata metadata = new Metadata();
		FileInputStream inputstream = new FileInputStream(new File(child_str));
		//Logger.warn("\n\n\n\n"+child.getName());
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
	        s=Checkers.FindNReplace(s);
//For newer Bravo files

			return s;
	}

	public String GetFileCreationDate(File child) throws IOException, SAXException, TikaException, URISyntaxException{
		String FileCreationTime=null;
		Path path = Paths.get(child.getName());
        BasicFileAttributes attr;
        attr = Files.readAttributes(path, BasicFileAttributes.class);
        FileCreationTime= attr.creationTime().toString();

		return(FileCreationTime);
	}



	@Test
	public void testExtractTotal() throws IOException, SAXException, TikaException, SQLException, ParseException, URISyntaxException {
		s=GetFile();
		ArrayList<List<String>> Arr2dTest=new ArrayList<List<String>>();
		Bravo.mane testingExtract = new Bravo.mane();
					   s=s.replaceAll("^\\s", "").replace(" 	     ", "").replaceAll("\n\\t", " ");
					   s=s.trim();
					   Pattern NumDays_pattern = Pattern.compile("Day \\d");
						Matcher matcherNumDays_pattern = NumDays_pattern.matcher(s);

						 int i=0;
						  while (matcherNumDays_pattern.find()){
							  i= i+1;
							 //System.out.println("MY DAYS"+i);

								if (i>0){
									System.out.println("DAY"+day);
						day=Integer.toString(i);
						Pattern Tab3Landmarks_pattern = Pattern.compile("Day "+day+"(.*?)Probability that symptom and reflux",Pattern.DOTALL);
						Matcher matcherTab3Landmarks_pattern = Tab3Landmarks_pattern.matcher(s);

						while (matcherTab3Landmarks_pattern.find()) {
							docSlim=matcherTab3Landmarks_pattern.group(1);
							seTab3Landmarks=matcherTab3Landmarks_pattern.group(1).split("\\n|\\r");
						}
                                        }
								Arr2dTest=testingExtract.ExtractTotal(s, day);
								}



		ArrayList<List<String>> Arr2d=new ArrayList<List<String>>();
		ArrayList<String> Arr=new ArrayList<String>();
		String input1="ReflDay2TimepHLessThan4minTotal, 00:49";
		String [] nd=input1.split(",");
		for (int ig=0;ig<nd.length;ig++){
			Arr.add(nd[ig].trim());
		}
		Arr2d.add(Arr);

		ArrayList<String> Arr2=new ArrayList<String>();
		String input2="ReflDay2NumberofRefluxesTotal, 19";
		String [] nd2=input2.split(",");
		for (int i2=0;i2<nd2.length;i2++){
			Arr2.add(nd2[i2].trim());
		}
		Arr2d.add(Arr2);

		ArrayList<String> Arr3=new ArrayList<String>();
		String input3="ReflDay2FractionTimepHLessThan4Total, 4.5";
		String [] nd3=input3.split(",");
		for (int i3=0;i3<nd3.length;i3++){
			Arr3.add(nd3[i3].trim());
		}
		Arr2d.add(Arr3);

		ArrayList<String> Arr4=new ArrayList<String>();
		String input4="ReflDay2NumberofLongRefluxesMoreThan5minTotal, 2";
		String [] nd4=input4.split(",");
		for (int i4=0;i4<nd4.length;i4++){
			Arr4.add(nd4[i4].trim());
		}
		Arr2d.add(Arr4);

		ArrayList<String> Arr5=new ArrayList<String>();
		String input5="ReflDay2DurationoflongestrefluxminTotal, 00:27";
		String [] nd5=input5.split(",");
		for (int i5=0;i5<nd5.length;i5++){
			Arr5.add(nd5[i5].trim());
		}
		Arr2d.add(Arr5);

		//System.out.println("Arr2dRefrTotal"+Arr2d);
		//System.out.println("Arr2dTestTotal"+Arr2dTest);
		assertEquals(Arr2d,Arr2dTest);
		  }






	@Test
	public void testExtractDurations() throws IOException, SAXException, TikaException, URISyntaxException {
		s=GetFile();
		ArrayList<List<String>> Arr2dTest=new ArrayList<List<String>>();
		Bravo.mane testingExtract = new Bravo.mane();
					   s=s.replaceAll("^\\s", "").replace(" 	     ", "").replaceAll("\n\\t", " ");
					   s=s.trim();
					   Pattern NumDays_pattern = Pattern.compile("Day \\d");
						Matcher matcherNumDays_pattern = NumDays_pattern.matcher(s);
						 int i=0;
						  while (matcherNumDays_pattern.find()){
							  i= i+1;

								if (i>0){
						day=Integer.toString(i);
						Pattern Tab3Landmarks_pattern = Pattern.compile("Day "+day+"(.*?)Probability that symptom and reflux",Pattern.DOTALL);
						Matcher matcherTab3Landmarks_pattern = Tab3Landmarks_pattern.matcher(s);

						while (matcherTab3Landmarks_pattern.find()) {
							docSlim=matcherTab3Landmarks_pattern.group(1);
							seTab3Landmarks=matcherTab3Landmarks_pattern.group(1).split("\\n|\\r");
						}

                                        }
								Arr2dTest=testingExtract.ExtractDurations(s, day);
						                                         }


							ArrayList<List<String>> Arr2d=new ArrayList<List<String>>();
							ArrayList<String> Arr=new ArrayList<String>();
							String input1="ReflDay2DurationofPeriodTotal, 22:01";
							String [] nd=input1.split(",");
							for (int ig=0;ig<nd.length;ig++){
								Arr.add(nd[ig].trim());
							}
							Arr2d.add(Arr);

							ArrayList<String> Arr2=new ArrayList<String>();
							String input2="ReflDay2DurationofPeriodUpright, 12:53";
							String [] nd2=input2.split(",");
							for (int i2=0;i2<nd2.length;i2++){
								Arr2.add(nd2[i2].trim());
							}
							Arr2d.add(Arr2);

							ArrayList<String> Arr3=new ArrayList<String>();
							String input3="ReflDay2DurationofPeriodSupine, 09:08";
							String [] nd3=input3.split(",");
							for (int i3=0;i3<nd3.length;i3++){
								Arr3.add(nd3[i3].trim());
							}
							Arr2d.add(Arr3);

							ArrayList<String> Arr4=new ArrayList<String>();
							String input4="ReflDay2DurationofPeriodPostPr, 07:24";
							String [] nd4=input4.split(",");
							for (int i4=0;i4<nd4.length;i4++){
								Arr4.add(nd4[i4].trim());
							}
							Arr2d.add(Arr4);

							//System.out.println("Arr2dRefrDurations"+Arr2d);
							//System.out.println("Arr2dTestDurations"+Arr2dTest);
							assertEquals(Arr2d,Arr2dTest);

	}

	@Test
	public void testExtractNew() throws IOException, SAXException, TikaException, URISyntaxException, SQLException {
		s=GetFile();
		String FileCreationDate=null;
		try {
			FileCreationDate=GetFileCreationDate(child);
		} catch (Exception e) {

			e.printStackTrace();
		}
		ArrayList<List<String>> Arr2dTest=new ArrayList<List<String>>();
		Bravo.mane testingExtract = new Bravo.mane();
					   s=s.replaceAll("^\\s", "").replace(" 	     ", "").replaceAll("\n\\t", " ");
					   s=s.trim();
					   Arr2dTest=testingExtract.ExtractNew(s,child_str,FileCreationDate);
					   //Sytsem.out.println(Arr2dTest)
	}

	@Test
	public void testExtractUpright() throws IOException, SAXException, TikaException, URISyntaxException {
		s=GetFile();
		ArrayList<List<String>> Arr2dTest=new ArrayList<List<String>>();
		Bravo.mane testingExtract = new Bravo.mane();
					   s=s.replaceAll("^\\s", "").replace(" 	     ", "").replaceAll("\n\\t", " ");
					   s=s.trim();
					   Pattern NumDays_pattern = Pattern.compile("Day \\d");
						Matcher matcherNumDays_pattern = NumDays_pattern.matcher(s);
						 int i=0;
						  while (matcherNumDays_pattern.find()){
							  i= i+1;

								if (i>0){
						day=Integer.toString(i);
						Pattern Tab3Landmarks_pattern = Pattern.compile("Day "+day+"(.*?)Probability that symptom and reflux",Pattern.DOTALL);
						Matcher matcherTab3Landmarks_pattern = Tab3Landmarks_pattern.matcher(s);

						while (matcherTab3Landmarks_pattern.find()) {
							docSlim=matcherTab3Landmarks_pattern.group(1);
							seTab3Landmarks=matcherTab3Landmarks_pattern.group(1).split("\\n|\\r");
						}

                                        }
								Arr2dTest=testingExtract.ExtractUpright(s, day);
								//System.out.println("Returned Upright"+Arr2dTest);
						                                         }
	}

	@Test
	public void testExtractSupine() throws IOException, SAXException, TikaException, URISyntaxException {
		s=GetFile();
		ArrayList<List<String>> Arr2dTest=new ArrayList<List<String>>();
		Bravo.mane testingExtract = new Bravo.mane();
					   s=s.replaceAll("^\\s", "").replace(" 	     ", "").replaceAll("\n\\t", " ");
					   s=s.trim();
					   Pattern NumDays_pattern = Pattern.compile("Day \\d");
						Matcher matcherNumDays_pattern = NumDays_pattern.matcher(s);
						 int i=0;
						  while (matcherNumDays_pattern.find()){
							  i= i+1;

								if (i>0){
						day=Integer.toString(i);
						Pattern Tab3Landmarks_pattern = Pattern.compile("Day "+day+"(.*?)Probability that symptom and reflux",Pattern.DOTALL);
						Matcher matcherTab3Landmarks_pattern = Tab3Landmarks_pattern.matcher(s);

						while (matcherTab3Landmarks_pattern.find()) {
							docSlim=matcherTab3Landmarks_pattern.group(1);
							seTab3Landmarks=matcherTab3Landmarks_pattern.group(1).split("\\n|\\r");
						}

                                        }
								Arr2dTest=testingExtract.ExtractSupine(s, day);
								//System.out.println("Returned Supine"+Arr2dTest);
						                                         }
							ArrayList<List<String>> Arr2d=new ArrayList<List<String>>();
							ArrayList<String> Arr=new ArrayList<String>();
							String input1="ReflDay2TimepHLessThan4minSupine, 00:31";
							String [] nd=input1.split(",");
							for (int ig=0;ig<nd.length;ig++){
								Arr.add(nd[ig].trim());
							}
							Arr2d.add(Arr);

							ArrayList<String> Arr2=new ArrayList<String>();
							String input2="ReflDay2NumberofRefluxesSupine, 6";
							String [] nd2=input2.split(",");
							for (int i2=0;i2<nd2.length;i2++){
								Arr2.add(nd2[i2].trim());
							}
							Arr2d.add(Arr2);

							ArrayList<String> Arr3=new ArrayList<String>();
							String input3="ReflDay2FractionTimepHLessThan4Supine, 5.6";
							String [] nd3=input3.split(",");
							for (int i3=0;i3<nd3.length;i3++){
								Arr3.add(nd3[i3].trim());
							}
							Arr2d.add(Arr3);

							ArrayList<String> Arr4=new ArrayList<String>();
							String input4="ReflDay2NumberofLongRefluxesMoreThan5minSupine, 1";
							String [] nd4=input4.split(",");
							for (int i4=0;i4<nd4.length;i4++){
								Arr4.add(nd4[i4].trim());
							}
							Arr2d.add(Arr4);

							ArrayList<String> Arr5=new ArrayList<String>();
							String input5="ReflDay2DurationoflongestrefluxminSupine, 00:27";
							String [] nd5=input5.split(",");
							for (int i5=0;i5<nd5.length;i5++){
								Arr5.add(nd5[i5].trim());
							}
							Arr2d.add(Arr5);

							//System.out.println("Arr2dRefrSupine"+Arr2d);
							//System.out.println("Arr2dTestSupine"+Arr2dTest);
							assertEquals(Arr2d,Arr2dTest);
	}

	@Test
	public void testExtractPostprandial() throws IOException, SAXException, TikaException, URISyntaxException {
		s=GetFile();
		ArrayList<List<String>> Arr2dTest=new ArrayList<List<String>>();
		Bravo.mane testingExtract = new Bravo.mane();
					   s=s.replaceAll("^\\s", "").replace(" 	     ", "").replaceAll("\n\\t", " ");
					   s=s.trim();
					   Pattern NumDays_pattern = Pattern.compile("Day \\d");
						Matcher matcherNumDays_pattern = NumDays_pattern.matcher(s);
						 int i=0;
						  while (matcherNumDays_pattern.find()){
							  i= 1;

								if (i>0){
						day=Integer.toString(i);
						Pattern Tab3Landmarks_pattern = Pattern.compile("Day "+day+"(.*?)Probability that symptom and reflux",Pattern.DOTALL);
						Matcher matcherTab3Landmarks_pattern = Tab3Landmarks_pattern.matcher(s);

						while (matcherTab3Landmarks_pattern.find()) {
							docSlim=matcherTab3Landmarks_pattern.group(1);
							seTab3Landmarks=matcherTab3Landmarks_pattern.group(1).split("\\n|\\r");
						}

                                        }
								Arr2dTest=testingExtract.ExtractPostprandial(s, day);
								//System.out.println("Returned Postprandial"+Arr2dTest);



								ArrayList<List<String>> Arr2d=new ArrayList<List<String>>();
								ArrayList<String> Arr=new ArrayList<String>();
								String input1="ReflDay1TimepHLessThan4minMeal, 00:18";
								String [] nd=input1.split(",");
								for (int ig=0;ig<nd.length;ig++){
									Arr.add(nd[ig].trim());
								}
								Arr2d.add(Arr);

								ArrayList<String> Arr2=new ArrayList<String>();
								String input2="ReflDay1NumberofRefluxesMeal, 11";
								String [] nd2=input2.split(",");
								for (int i2=0;i2<nd2.length;i2++){
									Arr2.add(nd2[i2].trim());
								}
								Arr2d.add(Arr2);

								ArrayList<String> Arr3=new ArrayList<String>();
								String input3="ReflDay1FractionTimepHLessThan4Meal, 3.9";
								String [] nd3=input3.split(",");
								for (int i3=0;i3<nd3.length;i3++){
									Arr3.add(nd3[i3].trim());
								}
								Arr2d.add(Arr3);

								ArrayList<String> Arr4=new ArrayList<String>();
								String input4="ReflDay1NumberofLongRefluxesMoreThan5minMeal, 1";
								String [] nd4=input4.split(",");
								for (int i4=0;i4<nd4.length;i4++){
									Arr4.add(nd4[i4].trim());
								}
								Arr2d.add(Arr4);

								ArrayList<String> Arr5=new ArrayList<String>();
								String input5="ReflDay1DurationoflongestrefluxminMeal, 00:06";
								String [] nd5=input5.split(",");
								for (int i5=0;i5<nd5.length;i5++){
									Arr5.add(nd5[i5].trim());
								}
								Arr2d.add(Arr5);

								//System.out.println("Arr2dRefrPostprandial"+Arr2d);
								//System.out.println("Arr2dTestPostprandial"+Arr2dTest);
								assertEquals(Arr2d,Arr2dTest);
						                                         }
	}

	@Test
	public void testExtractSymptomsTable() throws IOException, SAXException, TikaException, URISyntaxException {
		s=GetFile();
		ArrayList<List<String>> Arr2dTest=new ArrayList<List<String>>();
		Bravo.mane testingExtract = new Bravo.mane();
					   s=s.replaceAll("^\\s", "").replace(" 	     ", "").replaceAll("\n\\t", " ");
					   s=s.trim();
					   Pattern NumDays_pattern = Pattern.compile("Day \\d");
						Matcher matcherNumDays_pattern = NumDays_pattern.matcher(s);
						 int i=0;
						  while (matcherNumDays_pattern.find()){
							  i= 1;

								if (i>0){
						day=Integer.toString(i);
						Pattern Tab3Landmarks_pattern = Pattern.compile("Day "+day+"(.*?)Probability that symptom and reflux",Pattern.DOTALL);
						Matcher matcherTab3Landmarks_pattern = Tab3Landmarks_pattern.matcher(s);

						while (matcherTab3Landmarks_pattern.find()) {
							docSlim=matcherTab3Landmarks_pattern.group(1);
							seTab3Landmarks=matcherTab3Landmarks_pattern.group(1).split("\\n|\\r");
						}

                                        }
								//System.out.println("SSTEST"+docSlim);
								Arr2dTest=testingExtract.ExtractSymptomsTable(docSlim, day);
								//System.out.println("Returned Symptoms"+Arr2dTest);


								ArrayList<List<String>> Arr2d=new ArrayList<List<String>>();
ArrayList<String> Arr1=new ArrayList<String>();
String input1="Heartburn, ChestPain, StomachPain";
String [] nd1=input1.split(",");
for (int i1=0;i1<nd1.length;i1++){
	Arr1.add(nd1[i1].trim());
}
Arr2d.add(Arr1);

ArrayList<String> Arr2=new ArrayList<String>();
String input2="ReflDay1NumberofRefluxes, 6, 1, 0";
String [] nd2=input2.split(",");
for (int i2=0;i2<nd2.length;i2++){
	Arr2.add(nd2[i2].trim());
}
Arr2d.add(Arr2);


ArrayList<String> Arr3=new ArrayList<String>();
String input3="SIDay1, 37.5, 6.2, 0.0";
String [] nd3=input3.split(",");
for (int i3=0;i3<nd3.length;i3++){
	Arr3.add(nd3[i3].trim());
}
Arr2d.add(Arr3);



ArrayList<String> Arr4=new ArrayList<String>();
String input4="SAPDay1, 99.7, 84.3, 0.0";
String [] nd4=input4.split(",");
for (int i4=0;i4<nd4.length;i4++){
	Arr4.add(nd4[i4].trim());
}
Arr2d.add(Arr4);


System.out.println("Arr2dRefrSymp"+Arr2d);
System.out.println("Arr2dTestSymp"+Arr2dTest);
assertEquals(Arr2d,Arr2dTest);
						                                         }
	}

	@Test
	public void testHospNum() throws IOException, SQLException, ParseException, SAXException, TikaException, URISyntaxException {

	}

	@Test
	public void testFName() throws IOException, SQLException {
		String s = "Patient: JEREMY BYRNE";
		Bravo.mane testingExtract = new Bravo.mane();
		String FName=testingExtract.FName(s);
		assertEquals(FName,"JEREMY");
	}

	@Test
	public void testSName() throws IOException, SQLException, SAXException, TikaException, URISyntaxException {
		String sg=GetFile();
		//System.out.println(sg);
		String s = "Patient: JEREMY BYRNE  ";
		Bravo.mane testingExtract = new Bravo.mane();
		String SName=testingExtract.SName(s);
		assertEquals(SName,"BYRNE");
	}

	@Test
	public void testVisitDate() throws IOException, SAXException, TikaException, URISyntaxException, SQLException, ParseException {
		s=GetFile();
		Bravo.mane testingExtract = new Bravo.mane();
		String VisitDate=testingExtract.VisitDate(s);

		assertEquals(VisitDate,"5_25_2016");
		//System.out.println("VisitDate"+VisitDate);
	}

	@Test
	public void testDOB() throws IOException, SAXException, TikaException, URISyntaxException, SQLException {
		//s=GetFile();
		String s = "Birth Date: 09/25/1975 Technician";
		Bravo.mane testingExtract = new Bravo.mane();
		String DOB=testingExtract.DOB(s);
		assertEquals(DOB,"09/25/1975");


	}




}

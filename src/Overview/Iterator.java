package Overview;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;
import Console.TBB_SQLBuilder;
import Diag.mane;
import Endoscopy.EndoscopyExtractor;
import Endoscopy.EndoscopyExtractorOld;
import HRM.*;
import Histopath.HistopathExtractor;
import Histopath.HistopathExtraxtorOld;
import Surgery.Procedures;

import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;

public class Iterator {
	static HashSet<String> myFiles = new HashSet<String>();
	public static Preferences prefs;
	 static String filename;
	static String folderName;
	static Path p;
	public Iterator() {
	}

	public static void main(String[] args) throws IOException, SAXException, TikaException, SQLException, ParseException, URISyntaxException, BackingStoreException {


		Preferences userPrefs = Preferences.userNodeForPackage(TBB_SQLBuilder.class);
		//Load up the error log

		org.pmw.tinylog.writers.FileWriter fwError= new org.pmw.tinylog.writers.FileWriter(userPrefs.get("PathForError", null),false);
		Configurator.currentConfig().writer(fwError).level(Level.ERROR).formatPattern("{level}: {class}.{method}.{line}()\t{message}+{method}").maxStackTraceElements(5).activate();

		filename=userPrefs.get("PathForLogger", null);
		//System.out.println(userPrefs.get("PathForLogger", null));

		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)),2048);
		String line = null;
		//Reading the files from the logger so they can be avoided
		while((line = reader.readLine()) != null) {
			myFiles.add(line.trim());
		}

			//This iterates through each of the files in the specified folder and copies them to a log.
			//It also checks to see if that file has been read already so that it isn't re-inputted into the database if run again
		    //Loop through the ArrayList with the full path names of each folder in the outer loop

	    	String[] keys = userPrefs.keys();

		    for (String folderName : keys) {


		        if(userPrefs.get(folderName, null)!=null){
		        		if(!(folderName.toString().contains("PathToGRS"))){
		        			String fn=folderName;
		        			if(!(fn.toString().contains("PathForR"))){
		        				if(!(folderName.toString().contains("PathForMyLogger"))){
		        					if(!(folderName.toString().contains("PathForLogger"))){
		        						if(!(folderName.toString().contains("PathForLogger"))){
		        							if(!(folderName.toString().contains("PathToPaperClinic"))){
		        					System.out.println("folderName"+fn);
		        		loopthrough(userPrefs.get(fn, null));
		        							}
		        						}
		        					}
		        				}
		        			}
		        		}
		            }
		    }
		    reader.close();
}




/////////////////// Loops through all the files in the folder


	public static void loopthrough(String folderName) throws IOException, SAXException, TikaException, SQLException, ParseException, URISyntaxException{
		//System.out.println("This is the loopthrough folderName"+folderName);
		File dir = new File(folderName);
		File[] directoryListing = dir.listFiles();
		  try {

			if (directoryListing != null) {
				  int adder=0;
					for (File child : directoryListing) {
						 try {

						if(!myFiles.contains(child.getName().toString().trim())){
							if(child.isFile()){
							adder++;
							if(adder % 5 == 0){
								try {
								Thread.sleep(500);

								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}


////////////////////////////////////////////////////////////////// Add the file to the logger

							Preferences userPrefs = Preferences.userNodeForPackage(TBB_SQLBuilder.class);
							try {

								FileWriter fw= new FileWriter(userPrefs.get("PathForLogger", null),true);

									BufferedWriter bw = new BufferedWriter(fw,2048);

								         // APPEND MODE SET HERE- unComment here
									 bw.write(child.getName().toString().trim());
									 bw.newLine();
									 bw.flush();
									 bw.close();
									 fw.close();

								      } catch (IOException ioe) {
									 Logger.error(ioe+"IT DIDNT WRITE TO THE FILELOGGER");

								 }

					    AutoDetectParser parser = new AutoDetectParser();
						BodyContentHandler handler = new BodyContentHandler(-1);
						Metadata metadata = new Metadata();
						FileInputStream inputstream = new FileInputStream(new File(child.getPath()));
						//Logger.warn("\n\n\n\n"+child.getName());
						ParseContext context = new ParseContext();
						parser.parse(inputstream, handler, metadata, context);
						String s=null;
					    s =handler.toString();
					   System.out.println("Comparison string"+s);
					    handler=null;
					    context=null;
					    inputstream.close();
					    PDFont.clearResources();
					    System.gc();
					    s=Checkers.FindNReplace(s);
					    //child.getName().toString().replaceAll("//%", "")
					    //System.out.println("child.getName().tostring()"+child.getName().toString());
					    System.out.println("child.getPath().tostring()"+child.getPath().toString());
					    //System.out.println("child.getPath().tostring()"+s);

					    //if(child.getPath().contains("final")){
					    //	System.out.println("final here");
					    //}

//Endoscopy files....takes xlsx files....
					        if(child.getPath().contains("T6200")||child.getPath().contains("TCR2232")||child.getPath().contains("Gastroscopy_Hi")){
					        	try {
					        		System.out.println(child.getName().trim()+"Im in endoscopy");

					        		//getFileCreationDate(child);
									EndoscopyExtractor.Endoscopy_mane(child.getPath());
									HistopathExtractor.Histology_mane(child.getPath());
									//System.out.println(child.getName().trim());
								} catch (Exception e) {
									Logger.error(e+child.getName());

								}
					        }


//For newer Bravo files
					        else if(s.contains("pH Analysis Thresholds")&&!s.contains("ENDOSCOPIC DIAGNOSIS")

					        		&&!child.getName().trim().contains("Chph")
					        		&&!child.getName().trim().contains("ChPh")
					        		&&!child.getName().trim().contains("PAE")
					        		){
					        	System.out.println("Caught in New Bravo"+child.getName());
					        	Bravo.mane newer = new Bravo.mane();


					  		  try {
									  try {
										  if (child.getAbsolutePath().contains(".doc")&!child.getAbsolutePath().contains(".docx")){
										   s=s.replaceAll("^\\s", "").replace(" 	     ", "").replaceAll("\n\\t", " ");

										   s=s.trim();
										   newer.ExtractNew(s,child.getName(),getFileCreationDate(child));

										  }
										  else{
											  getFileCreationDate(child);
											  newer.ExtractNew(s,child.getName(),getFileCreationDate(child));
											  ////System.out.println("THISIS S"+s);
										  }
									} catch (Exception e) {
										Logger.error(e,child.getName());

									}
							} catch (Exception e) {
								Logger.error(e+child.getName());

							}
					        }
//Older BRAVO files
					       else if(s.contains("Fraction Time pH <4")&&!s.contains("Reflux Table - pH")&&!s.contains("ENDOSCOPIC DIAGNOSIS")&&s.contains("Day")&&!s.contains("Reflux Table - Proximal")){
					    	   System.out.println("Caught in Bravo old"+child.getName());
					        	Bravo.maneOld g=new Bravo.maneOld();

					        	g.Extract(s,child.getName(),getFileCreationDate(child));

					        }
//Surgical files
					       else if(child.getName().contains("Galaxy")&&!s.contains("ENDOSCOPIC DIAGNOSIS")){
					    	   System.out.println("Caught in surgery"+child.getName());
					        	//Surgery.Procedures p=new Surgery.Procedures();
					        	getFileCreationDate(child);
					        	Procedures.Sugery_mane(child.getPath());

					        }
//Impedance files
					        else if(s.contains("Impedance")&&!s.contains("ENDOSCOPIC DIAGNOSIS")){
					        	 try {
									if (child.getPath().contains("rtf")){
										System.out.println("File now being dealt with impedance"+getFileCreationDate(child));
										System.out.println("GHGHGHGHGHGHGH");
									Impedance.mane i = new Impedance.mane(s);
									i.Impmane(s,child.getName(),getFileCreationDate(child));
									 }
								} catch (Exception e) {
								}
					        }
//Breath Test files
					        else if(s.contains("Breath Test")&&!s.contains("Breath Test Technician")&&!s.contains("ENDOSCOPIC DIAGNOSIS")){
					        	System.out.println("Caught in breath test"+child.getName());
					        	try {
									BreathTest.mane b= new BreathTest.mane();

									b.Gomane(s,child.getAbsolutePath().toString().trim(),getFileCreationDate(child));
									PDFont.clearResources();
									System.gc();

								} catch (Exception e) {
									Logger.error(e+child.getName());
								}
					        }


//HRM- doesnt process the final report here as Indication is excluded:
					        else if(s.contains("nares")&&!s.contains("Indications")&&!s.contains("RECALL")&&!s.contains("ENDOSCOPIC DIAGNOSIS")){
					        	//do Something for HRM
					        	System.out.println("Caught in HRM ONly"+child.getName());

					        	s=Checkers.FindNReplace(s);

					        	try {

									HRMAll.mane(s,child.getName(),getFileCreationDate(child));

								} catch (Exception e) {
									Logger.error("getFileCreationDate"+e+child.getName());

								}
					        }
//HRM and Diag
					        //This looks to see if file is called FINAL. This is the first one to look at as it
					        //will definitely be the final file and then any "Indication" keyword in other files
					        //will not be entered into the database (so won't displace this one


					        if((child.getPath().contains("final")
					        		||child.getPath().contains("FINAL")
					        		||child.getPath().contains("Final"))&&s.contains("nares")&&s.contains("Indications")&&!s.contains("ENDOSCOPIC DIAGNOSIS")){
					        	System.out.println("Caught in Diag"+child.getName());
					        	System.out.println("The raw string"+s);
					        	s=Checkers.FindNReplace(s);
					        	try {
									mane.Dimane(s,child.getName(),getFileCreationDate(child));
								} catch (Exception e) {
									Logger.error("getFileCreationDate"+e+child.getName());
								}
					           	try {
					           		//This prevents the HRM only files which are labelled as FINAL from being put in the DIag table
					           		if(!child.getPath().contains("Interpretation / Findings")){

									HRMAll.mane(s,child.getName(),getFileCreationDate(child));
									System.out.println("Im entering some HRM data"+child.getName());
					           		}

								} catch (Exception e) {
									Logger.error(e+child.getName());

								}
					        }

					        else if(s.contains("nares")&&s.contains("Indications")){
					        	//send to both HRM and to Diag
					        	System.out.println("Caught in both HRM and to Diag"+child.getName());
					        	s=Checkers.FindNReplace(s);
					        	try {
					        		getFileCreationDate(child);
									mane.Dimane(s,child.getName(),getFileCreationDate(child));
									System.out.println("Im entering some diag data with the HRM as well"+child.getName());
								} catch (Exception e) {
									Logger.error(e+child.getName());

								}
					        	try {
					        		getFileCreationDate(child);
									HRMAll.mane(s,child.getName(),getFileCreationDate(child));
									System.out.println("Im entering some HRM data with the diag as well"+child.getName());

								} catch (Exception e) {
									Logger.error(e+child.getName());

								}
					        }
//Just diag
					        else if(s.contains(("Indications"))&&!s.contains("nares")&&!s.contains("HISTOLOGY")&&!s.contains("ENDOSCOPIC DIAGNOSIS")&&!s.contains("Analysis Thresholds Channel")){
					        	try {
					        		//Just for Diag
					        		System.out.println("SEND SEND SEND send to MANE DIAG only");
					        		getFileCreationDate(child);
									mane.Dimane(s,child.getName(),getFileCreationDate(child));

								} catch (Exception e) {
									Logger.error(e+child.getName(),getFileCreationDate(child));
								}
					        }
					        else {
					        	////System.out.println("NAAAAH");
					        }
					        s=null;
							}
							else if(child.isDirectory()){
								PDFont.clearResources();
								System.gc();
								////System.out.println("THIS IS A DIRECTORY"+child.getAbsolutePath().toString());
								String direc;
								direc=child.getAbsolutePath().toString();
								loopthrough(direc);
							}
						}
						 } catch (Exception e) {

								Logger.error(e+"This file didnt get processed:"+child.getName());
								//System.out.println("This file didnt get processed:"+child.getName());
							}
					}

			  }
		} catch (Exception e) {

			Logger.error(e+"This file didnt get processed:"+directoryListing);
		}
	}
	public static String getFileCreationDate(File child) throws IOException {
		String FileCreationTime=null;
		Path path = Paths.get(child.getAbsolutePath());
        BasicFileAttributes attr;
        attr = Files.readAttributes(path, BasicFileAttributes.class);
        FileCreationTime= attr.creationTime().toString();

		return(FileCreationTime);

	}

}
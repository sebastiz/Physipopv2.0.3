package Bravo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.prefs.Preferences;

import Console.TBB_SQLBuilder;
import org.pmw.tinylog.Logger;

public class DBConnection {
	
	  Map<String,String> map=null;
	  Statement st;
	  String HospNum;

	public DBConnection(Map<String,String> map,String HospNum) {
		this.map=map;
		this.HospNum=HospNum;
			}
	public Statement Connector(String HospNum) throws SQLException{
		
		Preferences userPrefs = Preferences.userNodeForPackage(TBB_SQLBuilder.class);
		String connectDB ="jdbc:ucanaccess://"+userPrefs.get("PathForDB", null);
		
			Connection conn=DriverManager.getConnection(connectDB);
			Statement st =conn.createStatement();
			HospNumChecker(st,HospNum);
			return st;	
	}
			
	public String StringInsertKeyPreparer(Map<String,String> map){
			String prep ="";
			 
			 for (Entry<String, String> entry : map.entrySet()) {
		         String key = entry.getKey().replaceAll("\\s", "_");
		         prep=prep+ key.trim()+",";
		         
			 }			 
			 prep=prep.replace(":","");
			 prep=prep.replace("<","LessThan");
			 prep=prep.replace(">","MoreThan");
			 prep=prep.replace("__","");
			 prep=prep.replaceAll("\\." ,"");
			 prep=prep.replaceAll("\\(s\\)" ,"");
			 prep=prep.replaceAll(",$" ,"");
			 prep=prep.replaceAll("%" ,"");
			 prep=prep.replaceAll("\\(" ,"");
			 prep=prep.replaceAll("\\)" ,"");
			 prep=prep.replace("-" ,"_");
			 prep=prep.replace("-" ,"_");
			 prep=prep.replace("_" ,"");
			 prep=prep.replace("#" ,"");
			 map.clear();
			 return prep;
	
	}
	public String StringInsertValuePreparer(Map<String,String> map){
		
		 String value ="";
		 for (Entry<String, String> entry : map.entrySet()) {
	         value=value+",'"+entry.getValue().replaceAll("\\s", "_")+"'";
		 }			 
		 value=value.replaceAll("^,", "");
		 value=value.replace(":", "_");
		 value=value.replaceAll("\\/", "_");
		 map.clear();
		 return value;
	}
	
	
	public void HospNumChecker(Statement st, String HospNum) throws SQLException{
	ArrayList<String> CheckHospNum =new ArrayList<String>();							
	String str = "select HospNum_Id from PatientData";
	ResultSet rsHNum = st.executeQuery(str);
	while (rsHNum.next()){
	String HNum = rsHNum.getString("HospNum_Id");

	CheckHospNum.add(HNum);
			}	
	if (!CheckHospNum.contains(HospNum)){
	String stg = "INSERT INTO PatientData (HospNum_Id,FName,SName,DOB) VALUES('"+HospNum+"','A','Patient','1953')";
	st.execute(stg, Statement.RETURN_GENERATED_KEYS);
	//System.out.println("PtData Inserted Successfully");

	
		}
	else{
		//System.out.println("The patient's already innit");
	}
	}
	
	
	public int Inserter(Statement st, String HospNum,String prep,String value) throws SQLException{

	String stg2 = "INSERT INTO BRAVODay1And2 (HospNum_Id,"+prep+") VALUES('"+HospNum+"',"+value+")";
	//System.out.println(stg2);
	st.execute(stg2,Statement.RETURN_GENERATED_KEYS);
	

  ResultSet gk = st.getGeneratedKeys();
    int Bravo_Id=0;

	if (gk != null && gk.next()) {
		Bravo_Id = gk.getInt(1);
	}

	return Bravo_Id;
	}
	
	public void Inserter2(Statement st, String prep,String value,int Bravo_Id) throws SQLException{
	
	String stg3 = "INSERT INTO BRAVOTotal (BravoID,"+prep+") VALUES("+Bravo_Id+","+value+")";
	//System.out.println(stg3);

	st.execute(stg3);
	//System.out.println("Impedance Sx Inserted Successfully");

	}

	
	

}

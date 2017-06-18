package HRM;
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

public class DBConnectorHRM {
	 Map<String,String> map;
	  Statement st;
	  String HospNum=null;
	  String key;

	  public DBConnectorHRM(Map<String,String> map,String HospNum) {
			this.map=map;
			this.HospNum=HospNum;
				}
	  public Statement Connector(String HospNum) throws SQLException{
		  Preferences userPrefs = Preferences.userNodeForPackage(TBB_SQLBuilder.class);
			String connectDB ="jdbc:ucanaccess://"+userPrefs.get("PathForDB", null);
			//System.out.println("NICE");
			////System.out.println("HospNum"+HospNum);
			Connection conn=DriverManager.getConnection(connectDB);
			Statement st =conn.createStatement();
			HospNumChecker(st,HospNum);
			return st;	
	}
	  
	  public ArrayList<String> ColumnNameVerifier(){
		  ArrayList<String> che=new ArrayList<String>();
		return che;

	  }
	  
	  public String StringInsertKeyPreparer(Map<String,String> map){
		  String key=null;
		   key = "";
			for (Entry<String, String> entry : map.entrySet()) {
		         key =key+","+ entry.getKey().trim();
		         key=key.replace(":","");
				 key=key.replace(" / ", "_");
				 key=key.replace("<","LessThan");
				 key=key.replace(">","MoreThan");
				 key=key.replace("__","");
				 key=key.replaceAll("\\." ,"");
				 key=key.replaceAll("\\(s\\)" ,"");
				 key=key.replaceAll(",$" ,"");
				 key=key.replaceAll("%" ,"");
				 key=key.replaceAll("\\(" ,"");
				 key=key.replaceAll("\\)" ,"");
				 key=key.replace("-" ,"_");
				 key=key.replace("-" ,"_");
				 key=key.replace("_" ,"");
				 key=key.replace("#" ,"");
				 key=key.replaceAll("\\/", "_");
				 key=key.replaceAll("@", "AT");
				 key=key.replaceAll("&", "AND");
				 key=key.replaceAll("\\?", "AND");
				 key=key.replace("≤","LessThan");
				 key=key.replace("≥","MoreThan");
				 key=key.replaceAll("\\s" ,"");
				 key=key.replaceAll("^,","");
				 //System.out.println("KEY"+key);
			}
				 return key;
	}
	  
	public String StringInsertValuePreparer(Map<String,String> map){
		 String value=null;  
		value ="";
		 
		 for (Entry<String, String> entry : map.entrySet()) {
	         value=value+",'"+entry.getValue().replaceAll("\\s", "_")+"'";
		 }			 
		 value=value.replaceAll("^,", "");
		 value=value.replace(":", "_");
		 value=value.replaceAll("\\/", "_");
		 value=value.replaceAll("\\(.*?\\)","");
		 value=value.replaceAll("__","");
		 value=value.replaceAll("__","");
		 value=value.replaceAll("'',","");
		 return value;
	}
	
	
	public void HospNumChecker(Statement st, String HospNum) throws SQLException{
		//System.out.println("HospNum"+HospNum);
	ArrayList<String> CheckHospNum =new ArrayList<String>();							
	String str = "select HospNum_Id from PatientData";
	ResultSet rsHNum = st.executeQuery(str);
	while (rsHNum.next()){
	String HNum = rsHNum.getString("HospNum_Id");
	//System.out.println("HNum"+HNum);
	//System.out.println("HospNum"+HospNum);
	CheckHospNum.add(HNum);
			}	
	
	if (!CheckHospNum.contains(HospNum)){
	 String stg= "INSERT INTO PatientData (HospNum_Id,FName,SName,DOB) VALUES('"+HospNum+"','A','Patient','1953')";
	st.execute(stg, Statement.RETURN_GENERATED_KEYS);
	//System.out.println("PtData Inserted Successfully"+stg);
		}
	else{
		//System.out.println("The patient's already innit");
		//System.out.println(HospNum);
	}
	}
	
	
	
	
	public int Inserter(Statement st, String HospNum,String prep,String value) throws SQLException{

		String stg2 = "INSERT INTO HRMImportMain (HospNum_Id,"+prep+") VALUES('"+HospNum+"',"+value+")";
		//System.out.println("The HRMIMPORT QUERY"+stg2);
		st.execute(stg2,Statement.RETURN_GENERATED_KEYS);
		
		//System.out.println("Main HRM All Inserted Successfully");
  ResultSet gk = st.getGeneratedKeys();
    int HRM_Id=0;
    //System.out.println("INT");
	if (gk != null && gk.next()) {
		HRM_Id = gk.getInt(1);
	}
	return HRM_Id;
	}
	
	
	
	
	public void Inserter2(Statement st, String prep,String value,int HRM_Id) throws SQLException{
			////System.out.println(WholeBRAVO_Total_strKey3);
			String stg3 = "INSERT INTO HRMImportSwallows (HRM_Id,"+prep+") VALUES("+HRM_Id+","+value+")";
			//System.out.println(stg3);
			st.execute(stg3);
			//System.out.println("HRM Swallows Inserted Successfully");
			//st.close();
			
	}

	

}

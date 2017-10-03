package Overview;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.prefs.Preferences;

import Console.TBB_SQLBuilder;
import org.pmw.tinylog.Logger;

public class DBConnectorForAll {
	  Map<String,String> map;
	  Statement st;
	  String HospNum;
	  String FName;
	  String SName;
	  String DOB;
	  ArrayList<String> chesney= new ArrayList<String>();

	public DBConnectorForAll() {
			}

	public Statement ConnectorNoInsert(String HospNum,String SName,String FName,String DOB) throws SQLException{
		Preferences userPrefs = Preferences.userNodeForPackage(TBB_SQLBuilder.class);
		String connectDB ="jdbc:ucanaccess://"+userPrefs.get("PathForDB", null);
		//System.out.println("Connection To Database Made");
		Connection conn=DriverManager.getConnection(connectDB);
		Statement st =conn.createStatement();
		return st;
}

	public Statement Connector(String HospNum,String SName,String FName,String DOB) throws SQLException{
		Preferences userPrefs = Preferences.userNodeForPackage(TBB_SQLBuilder.class);
		String connectDB ="jdbc:ucanaccess://"+userPrefs.get("PathForDB", null);
		//System.out.println("Some tuff"+connectDB);
		//System.out.println("Connection To Database Made");
		Connection conn=DriverManager.getConnection(connectDB);
		Statement st =conn.createStatement();
		try {
			HospNumChecker(st,HospNum,SName,FName,DOB);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logger.error(e+"There is no Hospital Number here");
		}
		return st;
}

	public String StringInsertKeyPreparer(Statement st,Map<String,String> map, String table) throws SQLException{
		String key;
		String prep ="";
		ArrayList<String> tester=new ArrayList<String>();
		ArrayList<String> PostReplaceAllPreChes =new ArrayList<String>();
		Checkers che =new Checkers();
		System.out.println("chesney the checker"+chesney);
		System.out.println("1. THIS IS THE MAP"+map);
		chesney=che.CheckDbCol(st, "SELECT * FROM "+table);
		System.out.println("2a.THIS IS THE DB"+chesney);
		 for (Entry<String, String> entry : map.entrySet()) {
		System.out.println("THIS IS THE KEYpre: "+entry);

		key = entry.getKey().replaceAll("\\s", "").replace(":","").replace("#" ,"").replaceAll("_" ,"").replaceAll("-","").replace("(?<!^)-" ,"").replaceAll("\\)" ,"").replace("<","LessThan").replace(">","MoreThan").replace("__","").replaceAll("\\." ,"").replaceAll("\\(s\\)" ,"").replaceAll(",$" ,"").replaceAll("\\(" ,"").replaceAll("%" ,"").replace("@LESRmmHg","ATLESRmmHg").replace("@LESR,mmHg","ATLESRmmHg").replace("cm/s","cms").replaceAll("_cm_s","cms").trim();			//Messy solution to a weird thing with the Hospital number in the endoscopy reports where HospNum_Id gets converted to HospNumID although this doesnt happen anywhere else
			 //key = entry.getKey().replaceAll("HospNumId", "HospNum_Id");
			 PostReplaceAllPreChes.add(key);
		for (String n:chesney){
			//System.out.println("THE KEYin DB::::"+n);
			 if (n.trim().equals(key)){

				 //System.out.println("THE KEY::::"+key);
				 tester.add(n);
	         prep=prep+ key.trim()+",";
			 }
			 }
		 }
		 System.out.println("3. THIS IS THE KEY post replaceall pre chesney"+PostReplaceAllPreChes);
		 System.out.println("4. database and the key matched fields"+tester);
				 System.out.println("5. THIS IS THE PREP KEY for entry into INSERT "+prep);
				 prep=prep.replaceAll(",$", "");
		 return prep;
}

	public String StringInsertValuePreparer(Statement st,Map<String,String> map,String table) throws SQLException{
		//System.out.println("THIS IS THE MAP GOING TO INSERTVALUEPREPARER"+map);
		String key;
		 String value="";
		 //System.out.println("THIS IS THE TABLE GOING TO INSERTVALUEPREPARER"+table);
		 Checkers che =new Checkers();
		 chesney=che.CheckDbCol(st, "SELECT * FROM "+ table);
		 for (Entry<String, String> entry : map.entrySet()) {
				key = entry.getKey().replaceAll("\\s", "").replace(":","").replace("#" ,"").replaceAll("_" ,"").replaceAll("(?<!^)-" ,"").replaceAll("\\)" ,"").replace("<","LessThan").replace(">","MoreThan").replace("__","").replaceAll("\\." ,"").replaceAll("\\(s\\)" ,"").replaceAll(",$" ,"").replaceAll("\\(" ,"").replaceAll("%" ,"").replace("@LESRmmHg","ATLESRmmHg").replace("@LESR,mmHg","ATLESRmmHg").replace("cm/s","cms").replaceAll("_cm_s","cms").trim();
				//key = entry.getKey().replaceAll("HospNumId", "HospNum_Id");
				for (String n:chesney){
				 if (n.trim().equals(key)){
			 if (value!=null){
				 //value=value.replaceAll("'", "");
	         value=value+",'"+entry.getValue()+"'";

			 }

		 }

			 }
		 }
		 value=value.replaceAll("^,", "");
		 value=value.replaceAll("$,", "");
		 value=value.replaceAll("([A-Z])'([A-Z])", "$1$2");
		 value=value.replace(":", "_");
		 value=value.replaceAll("\\/", "_").trim();
		 value=value.replaceAll("\\?", "").replaceAll("%", "pcent").replaceAll("\\(", "").replaceAll("\\)","").trim();
		 System.out.println("6. THIS IS THE PREP VALUE f"
		 		+ "or entry into INSERT "+value);
		 //map=null;
		 return value;
	}

	public void HospNumChecker(Statement st, String HospNum,String SName,String FName,String DOB) throws SQLException{
		ArrayList<String> CheckHospNum =new ArrayList<String>();
		String str = "select HospNum_Id from PatientData";
		ResultSet rsHNum = st.executeQuery(str);
		while (rsHNum.next()){
		String HNum = rsHNum.getString("HospNum_Id");
		CheckHospNum.add(HNum);
				}
		if (!CheckHospNum.contains(HospNum)){
		try {
			try {
				DOB=VisitDateFormatter.VDFormat(DOB);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String stg = "INSERT INTO PatientData (HospNum_Id,FName,SName,DOB) VALUES('"+HospNum+"','"+SName+"','"+FName+"','"+DOB+"')";
			System.out.println("STIGo"+stg);
			st.execute(stg, Statement.RETURN_GENERATED_KEYS);
			System.out.println("PtData Inserted Successfully");
		} catch (Exception e) {
			//if(e.getMessage().contains("unique constraint or index violation")){
				//Logger.error("You already have this HospNum in the table"+HospNum);
			//};
		}
			}
		else{
		}
				}

	public ArrayList<String> Selector(Statement st, String HospNum,String table,String Field) throws SQLException{
		String stg2 = "SELECT "+ Field+" FROM "+table+" Where HospNum_Id='"+HospNum+"'";
		System.out.println(stg2);
		ArrayList<String>SelectResult=new ArrayList<String>();
		ResultSet rsHNum = st.executeQuery(stg2);

		while (rsHNum.next()){
			String Res = rsHNum.getString(Field);
			SelectResult.add(Res+"\n");
					}

		return SelectResult;

	}

	public HashMap<String,Object> CompositeSelector(Statement st, String HospNum,String table,String Field) throws SQLException{
		String stg2 = "SELECT * FROM "+table+" Where (HospNum_Id='"+HospNum+"'"+" AND VisitDate='"+Field.replaceAll("\\n", "")+"')";

		ResultSet rsHNum = st.executeQuery(stg2);
		ResultSetMetaData metadata = rsHNum.getMetaData();
		int numberOfColumns = metadata.getColumnCount();
		HashMap<String,Object> row= new LinkedHashMap<String,Object>();

		while (rsHNum.next()){
		     for(int j=1; j<=numberOfColumns; ++j){
		      row.put(metadata.getColumnName(j),rsHNum.getObject(j));

		     }

					}

		return row;
	}


	public int Inserter(Statement st, String HospNum,String prep,String value,String table,String child) throws SQLException{
		ResultSet gk;
		String stg2;
		try {
			 stg2 = "INSERT INTO "+table+" (HospNum_Id,"+prep+") VALUES('"+HospNum+"',"+value.trim()+")";
			System.out.println("THIS IS STG2"+stg2);
			try {
				st.execute(stg2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Logger.error(e+"This db didnt get processed: "+HospNum+stg2+"TABLE:"+table+"CHILD"+child);
				e.printStackTrace();
			}

		} catch (Exception e) {


		}
	    int Bravo_Id=0;
	    //System.out.println("INT");
	    gk = st.getGeneratedKeys();
		if (gk != null && gk.next()) {
			Bravo_Id = gk.getInt(1);
		}
		// System.out.println("This is the FK so that The 1:1 table can be Added"+Bravo_Id);
		 //st.close();

		return Bravo_Id;
		}


	public int Inserter2(Statement st, String prep,String value,String table,int FKnum,String FK,String child) throws SQLException{
		ResultSet gk2;
		String stg3;
		try {
			 stg3 = "INSERT INTO "+table+" ("+FK+","+prep+") VALUES("+FKnum+","+value+")";

		try {
			st.execute(stg3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logger.error(e+"No process"+child+":"+stg3);
		}

		System.out.println("Successful execution innit");
		}
             catch (Exception e) {
				// TODO Auto-generated catch block

			}
		int Bravo_Id=0;
	    gk2 = st.getGeneratedKeys();
		if (gk2 != null && gk2.next()) {
			Bravo_Id = gk2.getInt(1);
		}
		return Bravo_Id;

}

	public void InserterMany2Many(Statement st, String prep,String value,String table,int FKnum,String FK) throws SQLException{
		String stg3;
		 stg3 = "INSERT INTO "+table+" ("+FK+","+prep+") VALUES("+FKnum+","+value+")";
		 st.execute(stg3);
}

}

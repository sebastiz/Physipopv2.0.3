package Overview;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.pmw.tinylog.Logger;

public class Checkers {
	 ArrayList<String> che2=new ArrayList<String>();
	 static URL dictionary;
	 String n=null;

	public Checkers() {

	}

	public static String FindNReplace(String n) throws IOException, URISyntaxException{

		BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.class.getResourceAsStream("/Files/FindNReplaceDictionary.txt")),2048);

		//dictionary = Checkers.class.getResource("/Files/FindNReplaceDictionary.txt");
		//Path p = Paths.get(dictionary.toURI());
		//System.out.println("MY FILENAME"+dictionary);
		//String ps=p.toString();
		String [] split=null;
		ArrayList<String> orig= new ArrayList<String>();
		String [] orig_arr=null;
		ArrayList<String> newDoc= new ArrayList<String>();
		String [] newDoc_arr=null;


	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = reader.readLine();

	        while (line != null) {
	        	split=line.split(":");
	        	//System.out.println("SPLIT"+split);
	        		orig.add(split[1]);
	        		newDoc.add(split[0]);
	            sb.append(line);
	            sb.append("\n");

	            line = reader.readLine();
	        }

	    } finally {
	    	reader.close();
	    }

	    orig_arr = new String[orig.size()];
	    orig_arr = orig.toArray(orig_arr);
	    newDoc_arr = new String[newDoc.size()];
	    newDoc_arr = newDoc.toArray(newDoc_arr);

	    String replacer=StringUtils.replaceEach(n, orig_arr, newDoc_arr);
	    newDoc_arr=null;
	    orig_arr=null;
	    n=null;

		return replacer;

		}

	public ArrayList<String> CheckDbCol(Statement st,String select) throws SQLException{
	 ResultSet rs = st.executeQuery(select);
     ResultSetMetaData rsmd = rs.getMetaData();
     for (int i = 1; i <= rsmd.getColumnCount(); i++) {
     	  che2.add(rsmd.getColumnName(i).trim());
     }
     rs.close();
	return che2;
	}

	public static ArrayList<String> VisitDateChecker(Statement st,String table,String HospNum) throws SQLException{
		ArrayList<String> VisitFromDB=new ArrayList<String>();
		System.out.println("THE TABLE"+table+"  HospNum"+HospNum);
	 ResultSet rs = st.executeQuery("Select VisitDate from "+table.trim()+" Where HospNum_Id='"+HospNum.trim()+"'");
	 System.out.println("Select VisitDate from "+table.trim()+" Where HospNum_Id='"+HospNum.trim()+"'");
			 while(rs.next()){
    	 System.out.println("\n  ID       : " + rs.getString("VisitDate"));
    	  VisitFromDB.add(rs.getString("VisitDate"));
    	  System.out.println("YEAH"+rs.getString("VisitDate"));
     }
			 System.out.println("VisitFromDB"+VisitFromDB);
			 rs.close();
	return VisitFromDB;
	}
}

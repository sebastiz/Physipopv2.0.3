package BreathTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BTAlgos {


//Has to take the row for the hosp. number and visit date selected as a hash

public void HashMaker(Map <String, Object> map){

	//Then chop it up according to the breath test you want, in to another two integer hashes with TimePoint as the Key and hydrogen or methane as the value
	//Then
	ArrayList<String> lactulTimePoint = new ArrayList<String>();
	  ArrayList<String> lactulH2 = new ArrayList<String>();
	  ArrayList<String> lactulCH4 = new ArrayList<String>();
	  ArrayList<String> lactTimePoint = new ArrayList<String>();
	  ArrayList<String> lactH2 = new ArrayList<String>();
	  ArrayList<String> lactCH4 = new ArrayList<String>();
	  ArrayList<String> sucrTimePoint = new ArrayList<String>();
	  ArrayList<String> sucrH2 = new ArrayList<String>();
	  ArrayList<String> sucrCH4 = new ArrayList<String>();
	  ArrayList<String> glucTimePoint = new ArrayList<String>();
      ArrayList<String> glucH2 = new ArrayList<String>();
      ArrayList<String> glucCH4 = new ArrayList<String>();
      ArrayList<String> frucTimePoint = new ArrayList<String>();
      ArrayList<String> frucH2 = new ArrayList<String>();
      ArrayList<String> frucCH4 = new ArrayList<String>();

	for (Entry<String, Object> entry : map.entrySet()) {

		if (entry.getKey().toString().trim().contains("lactulTimePoint")){
			if(entry.getValue()!=null){

             lactulTimePoint.addAll(Arrays.asList(entry.getValue().toString().trim().split("_")));
			}
		}
	     if (entry.getKey().toString().trim().contains("lactulH2")){
	    	 if(entry.getValue()!=null){

            lactulH2.addAll(Arrays.asList(entry.getValue().toString().trim().split("_")));
	    	 }
		}
		 if (entry.getKey().toString().trim().contains("lactulCH4")){
			 System.out.println(entry.getKey());
			 if(entry.getValue()!=null){
            lactulCH4.addAll(Arrays.asList(entry.getValue().toString().trim().split("_")));
			 }

		}
		 if (entry.getKey().toString().trim().contains("lactTimePoint")){
			 if(entry.getValue()!=null){


             lactTimePoint.addAll(Arrays.asList(entry.getValue().toString().trim().split("_")));
			 }
		}
	     if (entry.getKey().toString().trim().contains("lactH2")){
	    	 if(entry.getValue()!=null){


            lactH2.addAll(Arrays.asList(entry.getValue().toString().trim().split("_")));
	    	 }
		}
		 if (entry.getKey().toString().trim().contains("lactCH4")){
			 if(entry.getValue()!=null){


            lactCH4.addAll(Arrays.asList(entry.getValue().toString().trim().split("_")));
			 }
		}
		 if (entry.getKey().toString().trim().contains("sucrTimePoint")){
			 if(entry.getValue()!=null){


             sucrTimePoint.addAll(Arrays.asList(entry.getValue().toString().trim().split("_")));
			 }
		}
	     if (entry.getKey().toString().trim().contains("sucrH2")){
	    	 if(entry.getValue()!=null){


            sucrH2.addAll(Arrays.asList(entry.getValue().toString().trim().split("_")));
	    	 }
		}
		 if (entry.getKey().toString().trim().contains("sucrCH4")){
			 if(entry.getValue()!=null){


            sucrCH4.addAll(Arrays.asList(entry.getValue().toString().trim().split("_")));
			 }
		}
		 if (entry.getKey().toString().trim().contains("glucTimePoint")){
			 if(entry.getValue()!=null){


             glucTimePoint.addAll(Arrays.asList(entry.getValue().toString().trim().split("_")));
			 }
		}
	     if (entry.getKey().toString().trim().contains("glucH2")){
	    	 if(entry.getValue()!=null){

	    		 glucH2.addAll(Arrays.asList(entry.getValue().toString().trim().split("_")));
	    	 }
		}
		 if (entry.getKey().toString().trim().contains("glucCH4")){
			 if(entry.getValue()!=null){

            glucCH4.addAll(Arrays.asList(entry.getValue().toString().trim().split("_")));
			 }
		}
		 if (entry.getKey().toString().trim().contains("frucTimePoint")){
			 if(entry.getValue()!=null){

             frucTimePoint.addAll(Arrays.asList(entry.getValue().toString().trim().split("_")));
			 }
		}
	     if (entry.getKey().toString().trim().contains("frucH2")){
	    	 if(entry.getValue()!=null){

            frucH2.addAll(Arrays.asList(entry.getValue().toString().trim().split("_")));
	    	 }
		}
		 if (entry.getKey().toString().trim().contains("frucCH4")){
			 if(entry.getValue()!=null){

            frucCH4.addAll(Arrays.asList(entry.getValue().toString().trim().split("_")));


			 }
		}


		 //Now call the algorithm to get a TimePoint-value pair in a hash
if(!lactulCH4.isEmpty()){
	try {
		algoLactul(lactulTimePoint,lactulH2);
		algoLactul(lactulTimePoint,lactulCH4);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		 }
if(!lactH2.isEmpty()){
	try {
		algoLact(lactTimePoint,lactH2);
		algoLact(lactTimePoint,lactCH4);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		 }
if(!frucH2.isEmpty()){
	try {
		algoFruc(frucTimePoint,frucH2);
		algoFruc(frucTimePoint,frucCH4);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
if(!sucrH2.isEmpty()){
	try {
		algoSucr(sucrTimePoint,sucrH2);
		algoSucr(sucrTimePoint,sucrCH4);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
if(!glucH2.isEmpty()){
	try {
		algoGluc(glucTimePoint,glucH2);
		algoGluc(glucTimePoint,glucCH4);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	}

}

//Each algorithm should return an interpretation as a String from the method it is in
public void algoLactul(ArrayList<String> lactulTime,ArrayList<String> lactulMetric){
//Split up the Arrays and then recoombine them into a new Hash that gives the time as the key and the metric as the value
	HashMap<String,String> lactulHash = new HashMap<String,String>();
	 for (int i = 1; i < lactulTime.size(); i++) {
		 lactulHash.put(lactulTime.get(i),lactulMetric.get(i));
	 }


}
public void algoLact(ArrayList<String> lactTime,ArrayList<String> lactMetric){
	HashMap<String,String> lactHash = new HashMap<String,String>();
	for (int i = 0; i < lactTime.size(); i++) {
		 lactHash.put(lactTime.get(i),lactMetric.get(i));


	 }


}
public void algoGluc(ArrayList<String> glucTime,ArrayList<String> glucMetric){
	HashMap<String,String> glucHash = new HashMap<String,String>();
	for (int i = 0; i < glucTime.size(); i++) {
		glucHash.put(glucTime.get(i),glucMetric.get(i));

	 }


}
public void algoSucr(ArrayList<String> sucrTime,ArrayList<String> sucrMetric){
	HashMap<String,String> sucrHash = new HashMap<String,String>();
	for (int i = 0; i < sucrTime.size(); i++) {
		sucrHash.put(sucrTime.get(i),sucrMetric.get(i));

	 }

}
public void algoFruc(ArrayList<String> frucTime,ArrayList<String> frucMetric){
	HashMap<String,String> frucHash = new HashMap<String,String>();
	for (int i = 0; i < frucTime.size(); i++) {
		frucHash.put(frucTime.get(i),frucMetric.get(i));
	 }


}


}

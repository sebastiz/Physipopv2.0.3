package Overview;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SearcherTest {


	@Test
	public void testHospNo_searcher() throws IOException, SQLException {
		String s = "2233123544";
		String[] InputArrHospNo = {"2233123544","223312","22b3123544","Z233123544","Z233123","Z2331232","Z233123y","z233123","433445b","",};
		String[] ExpectedOPArrHospNo = { "2233123544","23_01_1956","23.01.1956","23/01_1956","Jan 23rd 1956","23rd Jan 1956"};
		List<String> elements_Hospfinal= new ArrayList<String>(Arrays.asList(ExpectedOPArrHospNo));
		List<String> InputArrHospNo_AL = new ArrayList<String>();
		for( int i = 0; i < InputArrHospNo.length; i++)
		{
			String t=Searcher.HospNo_searcher(InputArrHospNo[i]);
			InputArrHospNo_AL.add(t);
		}

		String HospNo= Searcher.HospNo_searcher(s);
		System.out.println("HospNo"+InputArrHospNo_AL);
		System.out.println("HospNo"+elements_Hospfinal);
		assertEquals(s,HospNo);
	}

	@Test
	public void testVisitDate_searcher() throws ParseException {
		String[] elements = { "23/01/1956","23_01_1956","23.01.1956","23/01_1956","Jan 23rd 1956","23rd Jan 1956"};
		String[] elements_final = { "23_01_1956","23_01_1956","23_01_1956","23_01_1956","23_01_1956","23_01_1956"};
		List<String> elements_ALfinal= new ArrayList<String>(Arrays.asList(elements_final));
		List<String> VisitDate = new ArrayList<String>();
		 for( int i = 0; i < elements.length; i++)
		{
			String t=VisitDateFormatter.VDFormat(elements[i]);
			VisitDate.add(t);
		}
		 System.out.println(elements_ALfinal);
		 System.out.println(elements_ALfinal.getClass());
		 System.out.println(VisitDate);
		 System.out.println(VisitDate.getClass());
		assertEquals(elements_ALfinal,VisitDate);
	}
}

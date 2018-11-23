import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.team.app.utils.DateUtil;

public class test6 {
	public static void main(String[] args) {
	    //short b=(short) 32769;
	/*	String b = "\b1";
		int[] a;
		a=new int[2];
		a[0]=2;
		a[1]=1;
		Arrays.sort(a);		
	    System.out.println("Value print"+Arrays.binarySearch(a, 1));*/
		//System.out.println("Value print"+a[1]);
		
		/*String decodeBinary="1234";
		String led1 = decodeBinary.substring(0, 1);	
		String	led2 = decodeBinary.substring(1, 2);	
		String	led3 = decodeBinary.substring(2, 3);	
		String	led4 = decodeBinary.substring(3, 4);	
		
		System.out.println("Value led1"+decodeBinary.length());
		System.out.println("Value led2"+led2);
		System.out.println("Value led3"+led3);
		System.out.println("Value led4"+led4);*/
		
		/*List<String> li=new ArrayList<String>();
		
		li.add("Sizy");
		li.add("Vikky");
		for(String s: li){
		System.out.println(s);
		}
		
		Object[] s=li.toArray();
		for (Object i: s){
		System.out.println(i.toString());
		}*/
		long epoch=1537864200000L;
		System.out.println("Date "+DateUtil.convertLongToDateIST(epoch,"yyyy-MM-dd"));
	}
		
		
	
}

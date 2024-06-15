package businessComponents;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import framework.parent.DriverManager;

public class Round2InterviewQnA extends DriverManager{
	
	public static void main(String[] args) {
		String str="aabbbcccca";
		String str1="";
		//output a2b3c4a1
		
		for(int i=0;i<str.length();i++) {
			int count =0;
			int j=0;
				for ( j = i; j < str.length(); j++) {
					if (str.charAt(i) == str.charAt(j)) {
						count++;
					}else{
						break;
					}
				}
				
				System.out.print(str.charAt(i));
				System.out.print(count);
			
				i=j-1;
		}
		
	}

}

package com.mindtree.practice;

public class ArrayTest {

	public static void main(String[] args) {
		int[] no = {2,3,4,5,6,2,1};
		
		
		
		for(int i=1;i<=no.length;i++) {
			 int s1=0;
			 int s2=0;
			for(int j=i+1;j<no.length;j++) {
				s1 +=no[j];
				
			}
            for(int k=i-1;k>=0;k--) {
				
				s2+=no[k];
			}
			if(s1==s2) {
            System.out.println("s1= "+ s1 +" s2= "+s2+" index   "+i+"  value  "+no[i]);
            break;
			}
		}
		
		

	}

}

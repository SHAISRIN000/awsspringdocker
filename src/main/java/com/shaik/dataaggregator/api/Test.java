package com.shaik.dataaggregator.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Test {

public static void main(String[] args) {
	System.out.println(System.currentTimeMillis());
	
	String query="5201 brookside dr apt 110 madison wi 53718";
	

	try {
		System.out.println(URLEncoder.encode(query,"utf-8"));
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}

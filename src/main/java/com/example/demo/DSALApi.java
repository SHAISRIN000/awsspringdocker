package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("aggregator")
public class DSALApi {

	
	@RequestMapping("/property")
	public String property(){
		return "{age:10 ,name:shaik}";
		
	}
	
}

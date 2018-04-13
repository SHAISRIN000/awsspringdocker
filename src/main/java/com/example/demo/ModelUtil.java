package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class ModelUtil {

	
	public static PropertyData getData() {
		
		Address address1=new Address("5201 Brookside Dr"," ","Madison","WI","53718");
		DataElements dataelements1=new DataElements("pool", "30", "imagePool","30");
		DataElements dataelements2=new DataElements("area", "40", "imageArea","30");
		DataElements dataelements3=new DataElements("attic", "50", "imageAttic","30");
		
		List<DataElements> elements =new ArrayList<DataElements>();
		elements.add(dataelements1);
		elements.add(dataelements2);
		elements.add(dataelements3);
		PropertyData propertyData=new PropertyData(address1, elements);
		return propertyData;
		
	}
}

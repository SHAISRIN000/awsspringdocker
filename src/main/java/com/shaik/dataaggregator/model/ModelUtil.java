package com.shaik.dataaggregator.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.assertj.core.util.Arrays;

public class ModelUtil {

	
	public static Report createReport() {
		
		Report r=new Report();
		r.setId(UUID.randomUUID().toString());
		Set<String> srcs=new HashSet<>();
		srcs.add("PropertyVision");
		srcs.add("DSAL");
		r.setSources(srcs);
		r.setCreateTS("TimeSTamp");
		List<ElementInference> eis=new ArrayList<ElementInference>();
		eis.add(new ElementInference("chiminey", "3", "imageID", "90", "1.0.0", "propertyVision"));
		eis.add(new ElementInference("pool", "30", "imageID", "90", "1.0.0", "propertyVision"));
		ElementInference roof=new ElementInference("roof", "30", "imageID", "90", "1.0.0", "propertyVision");
		SubElementInference roofs=new SubElementInference("roof1", "301", "imageID", "90", "1.0.0", "propertyVision");
		SubElementInference roofs1=new SubElementInference("roof1", "301", "imageID", "90", "1.0.0", "propertyVision");
		List<SubElementInference> ses=new ArrayList<SubElementInference>();
		ses.add(roofs);
		ses.add(roofs1);
		roof.setSubElementsInferences(ses);
		eis.add(roof);
		r.setElementInferences(eis);
		r.setStatus("Inprocess");
		return r;
	}
	
	public static PropertyData getData() {
		
		/*Address address1=new Address("5201 Brookside Dr"," ","Madison","WI","53718");
		DataElements dataelements1=new DataElements("pool", "30", "imagePool","30");
		DataElements dataelements2=new DataElements("area", "40", "imageArea","30");
		DataElements dataelements3=new DataElements("attic", "50", "imageAttic","30");
		
		List<DataElements> elements =new ArrayList<DataElements>();
		elements.add(dataelements1);
		elements.add(dataelements2);
		elements.add(dataelements3);
		PropertyData propertyData=new PropertyData(address1, elements);*/
		
		return new PropertyData();// propertyData;
		
	}
}

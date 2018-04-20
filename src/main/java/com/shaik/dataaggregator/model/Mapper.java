package com.shaik.dataaggregator.model;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.lang.model.util.Elements;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

public class Mapper {

	public static Report translate(String request) throws JsonProcessingException, IOException   {
			JsonParser parser = new JsonFactory().createJsonParser(request);
		   JsonNode rootNode = new ObjectMapper().readTree(parser);
	      String source="PropertyVision";
	       ObjectNode currentNode;
	       currentNode =(ObjectNode) rootNode;
	        Report r=new Report();
	        r.setCreateTS( new Timestamp(System.currentTimeMillis()).toString());
	        r.setId(UUID.randomUUID().toString());
	      Set<String>  srcs= new HashSet<>();
	      srcs.add(source);
	        r.setSources(srcs);
	        r.setStatus("InProgress");
	        
	        List<ElementInference> elemnts=new ArrayList<ElementInference>();
	        
	       
	        JsonNode numberStoriesInferenceNode=rootNode.get("numberStoriesInference");
	        
	    	ElementInference numberofstories=new ElementInference("numberOfStories",numberStoriesInferenceNode.get("numberStories").asText(),numberStoriesInferenceNode.get("inputUrl").asText(),numberStoriesInferenceNode.get("confidence").asText(),numberStoriesInferenceNode.get("modelVersion").asText(),source);
	    	elemnts.add(numberofstories);
	        JsonNode numberofChimneysInferenceNode=rootNode.get("numberChimneysInference");
		    ElementInference numberofChimneys=new ElementInference("num_chimneys",numberofChimneysInferenceNode.get("num_chimneys")!=null?numberofChimneysInferenceNode.get("num_chimneys").asText():"0",numberofChimneysInferenceNode.get("inputUrl").asText(),numberofChimneysInferenceNode.get("confidence")!=null?numberofChimneysInferenceNode.get("confidence").asText():"0",numberofChimneysInferenceNode.get("modelVersion").asText(),source);
		    elemnts.add(numberofChimneys);
		    JsonNode poolsInferenceNode=rootNode.get("poolsInference");
		    ElementInference poolss=new ElementInference("pools",poolsInferenceNode.get("pools")!=null?poolsInferenceNode.get("pools").asText():"0",poolsInferenceNode.get("inputUrl").asText(),poolsInferenceNode.get("confidence")!=null?poolsInferenceNode.get("confidence").asText():"0",poolsInferenceNode.get("modelVersion").asText(),source);
		    elemnts.add(poolss);
		    
		    JsonNode roofsInferenceNode=rootNode.get("roofsInference");
		    ElementInference roofs=new ElementInference();
		    roofs.setKey("roofs");
		    JsonNode roofsArrayNode=roofsInferenceNode.get("roofs");
		    if (roofsArrayNode.isArray()) {
		        for (final JsonNode objNode : roofsArrayNode) {
		        	
		        	SubElementInference subElementMaterial=new SubElementInference("material",objNode.get("material").asText(),objNode.get("materialInputUrl").asText(),objNode.get("materialConfidence").asText(),objNode.get("materialModelVersion").asText(),source);
		        	SubElementInference subElementShape=new SubElementInference("shape",objNode.get("shape").asText(),objNode.get("shapeInputUrl").asText(),objNode.get("shapeConfidence").asText(),objNode.get("shapeModelVersion").asText(),source);
		        	List<SubElementInference>  subinferences=new ArrayList<>();
		        	subinferences.add(subElementMaterial);
		        	subinferences.add(subElementShape);
		        	roofs.setSubElementsInferences(subinferences);
		             }
		    }
		    
		    elemnts.add(roofs);
		    
		    r.setElementInferences(elemnts);
		    return r;
	}
	
/*
	private static Address convertAddress(String request) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("Started convertAddress");

		 ObjectMapper mapper=new ObjectMapper();
		 Address address = mapper.readValue(request, Address.class);
			System.out.println("End convertAddress");

		 return address;
			
	}
	
	private static ElementInference fetchNumberOfStories(String dsalJSONstr) throws JsonProcessingException, IOException {
	   ObjectMapper mapper=new ObjectMapper();
	   JsonNode rootNode = mapper.readTree(dsalJSONstr);
		JsonNode numberStoriesInferenceNode=rootNode.get("numberStoriesInference");
		System.out.println(numberStoriesInferenceNode.get("confidence"));
		numberStoriesInferenceNode.get("inputUrl");
		numberStoriesInferenceNode.get("numberStories");
		//ElementInference numberofstories=new ElementInference("numberOfStories",numberStoriesInferenceNode.get("numberStories").asText(),numberStoriesInferenceNode.get("inputUrl").asText(),numberStoriesInferenceNode.get("confidence").asText());
		ElementInference numberofstories=null;
		 return numberofstories;
	}
	
	
	private static ElementInference fetchNumberOfChimneys(String dsalJSONstr) throws JsonProcessingException, IOException {
	   ObjectMapper mapper=new ObjectMapper();
	   JsonNode rootNode = mapper.readTree(dsalJSONstr);
	   JsonNode numberStoriesInferenceNode=rootNode.get("numberChimneysInference");
	   ElementInference numberofChimneys;
	   //ElementInference numberofChimneys=new ElementInference("num_chimneys",numberStoriesInferenceNode.get("num_chimneys")!=null?numberStoriesInferenceNode.get("num_chimneys").asText():"0",numberStoriesInferenceNode.get("inputUrl").asText(),numberStoriesInferenceNode.get("confidence")!=null?numberStoriesInferenceNode.get("confidence").asText():"0");
	   return numberofChimneys=null;
	}*/
}

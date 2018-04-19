package com.shaik.dataaggregator.model;

import java.util.List;
import java.util.UUID;
import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

public class Mapper {

	public static PropertyData translate(String request,Report report) throws JsonProcessingException, IOException   {
			JsonParser parser = new JsonFactory().createJsonParser(request);
		   JsonNode rootNode = new ObjectMapper().readTree(parser);
	      String source="PropertyVision";
	       ObjectNode currentNode;
	       currentNode =(ObjectNode) rootNode;
	        
	       
	       
	        JsonNode numberStoriesInferenceNode=rootNode.get("numberStoriesInference");
	        
	    	ElementInference numberofstories=new ElementInference("numberOfStories",numberStoriesInferenceNode.get("numberStories").asText(),numberStoriesInferenceNode.get("inputUrl").asText(),numberStoriesInferenceNode.get("confidence").asText(),numberStoriesInferenceNode.get("modelVersion").asText(),source);
		
	        JsonNode numberofChimneysInferenceNode=rootNode.get("numberChimneysInference");
		    ElementInference numberofChimneys=new ElementInference("num_chimneys",numberStoriesInferenceNode.get("num_chimneys")!=null?numberStoriesInferenceNode.get("num_chimneys").asText():"0",numberStoriesInferenceNode.get("inputUrl").asText(),numberStoriesInferenceNode.get("confidence")!=null?numberStoriesInferenceNode.get("confidence").asText():"0",numberStoriesInferenceNode.get("modelVersion").asText(),source););

		    JsonNode poolsInferenceNode=rootNode.get("poolsInference");
		    ElementInference poolss=new ElementInference("pools",poolsInferenceNode.get("pools")!=null?poolsInferenceNode.get("pools").asText():"0",poolsInferenceNode.get("inputUrl").asText(),poolsInferenceNode.get("confidence")!=null?poolsInferenceNode.get("confidence").asText():"0",poolsInferenceNode.get("modelVersion").asText(),source);

		    
	       PropertyData data=new PropertyData();
	       List<ElementInference> elements=new ArrayList<>();
	       elements.add(fetchNumberOfStories(response));
	       elements.add(fetchNumberOfChimneys(response));
	       data.setElements(elements);
	       return data;
	}
	

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
	}
}

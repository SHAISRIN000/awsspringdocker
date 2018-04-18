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

	public static PropertyData translate(String response,String request) throws JsonProcessingException, IOException   {
			JsonParser parser = new JsonFactory().createJsonParser(response);
			System.out.println("Started translate");
	System.out.println("Started translate");

	       JsonNode rootNode = new ObjectMapper().readTree(parser);
	    
	       ObjectNode currentNode;
	       currentNode =(ObjectNode) rootNode;

	       PropertyData data=new PropertyData();
	       List<DataElements> elements=new ArrayList<>();
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
	
	private static DataElements fetchNumberOfStories(String dsalJSONstr) throws JsonProcessingException, IOException {
	   ObjectMapper mapper=new ObjectMapper();
	   JsonNode rootNode = mapper.readTree(dsalJSONstr);
		JsonNode numberStoriesInferenceNode=rootNode.get("numberStoriesInference");
		System.out.println(numberStoriesInferenceNode.get("confidence"));
		numberStoriesInferenceNode.get("inputUrl");
		numberStoriesInferenceNode.get("numberStories");
		DataElements numberofstories=new DataElements("numberOfStories",numberStoriesInferenceNode.get("numberStories").asText(),numberStoriesInferenceNode.get("inputUrl").asText(),numberStoriesInferenceNode.get("confidence").asText());
	
		 return numberofstories;
	}
	
	
	private static DataElements fetchNumberOfChimneys(String dsalJSONstr) throws JsonProcessingException, IOException {
	   ObjectMapper mapper=new ObjectMapper();
	   JsonNode rootNode = mapper.readTree(dsalJSONstr);
	   JsonNode numberStoriesInferenceNode=rootNode.get("numberChimneysInference");
	   DataElements numberofChimneys=new DataElements("num_chimneys",numberStoriesInferenceNode.get("num_chimneys")!=null?numberStoriesInferenceNode.get("num_chimneys").asText():"0",numberStoriesInferenceNode.get("inputUrl").asText(),numberStoriesInferenceNode.get("confidence")!=null?numberStoriesInferenceNode.get("confidence").asText():"0");
	   return numberofChimneys;
	}
}

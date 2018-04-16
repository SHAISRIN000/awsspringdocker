//Copyright 2012-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
//Licensed under the Apache License, Version 2.0.
package com.example.demo;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.example.demo.model.DataElements;
import com.example.demo.model.PropertyData;
import com.example.demo.model.PropertyDataClone;


public class DocumentAPIItemCRUDExample {

 static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
 static DynamoDB dynamoDB = new DynamoDB(client);
 
 
 static String tableName = "DSAL.RawReport";

 public static void main(String[] args) throws IOException {

     //createItems(null);

  //   retrieveItem("4c6a2cf3-5439-47aa-b2d9-60773b58eb65");
	 
	 PropertyDataClone data=new PropertyDataClone();
	 data.setRequestor("test");
	 data.setSource("DSAL");
	 data.setCreateTS(new Timestamp(System.currentTimeMillis()).toString());
	 data.setId(UUID.randomUUID().toString());
	 List<DataElements> dataElements =new ArrayList<DataElements>();
	 dataElements.add(new DataElements("pool", "30", "imagePool","30"));
	 dataElements.add(new DataElements("area", "40", "imageArea","30"));
	 dataElements.add(new DataElements("attic", "50", "imageAttic","30"));
	 data.setElements(dataElements);
	 createReport(data);
	 
	 
     // Perform various updates.
//     updateMultipleAttributes();
//     updateAddNewAttribute();
//     updateExistingAttributeConditionally();
//
//     // Delete the item.
//     deleteItem();

 }
 
 
 public static void createReport(PropertyDataClone data) {
	 	Table table = dynamoDB.getTable(tableName);
	 	String id=data.getId();
	    DynamoDBMapper mapper = new DynamoDBMapper(client);
	    mapper.save(data);
	    // Retrieve the updated item.
      DynamoDBMapperConfig config = new DynamoDBMapperConfig(DynamoDBMapperConfig.ConsistentReads.CONSISTENT);
        PropertyDataClone updatedItem = mapper.load(PropertyDataClone.class, id, config);
        System.out.println("Retrieved the previously updated item:");
        //System.out.println(updatedItem)
	    /*
	 	try {
		           try {
	               table.putItem(new Item().withPrimaryKey("reportId", UUID.randomUUID().toString()).withString("report",
	                   currentNode.toString())
	         	  .withString("source", "DSAL")
	 			  .withString("requestor", "PLPC")
	               .withString("createTS",new Timestamp(System.currentTimeMillis()).toString()));
	               
	         	//  table.putItem(item);
	      
	           }
	           catch (Exception e) {
	               System.err.println("Create items failed.");
	               System.err.println(e.getMessage());

	                }
	       
	       parser.close();
	 	}catch(Exception e) {
	 		System.err.println("Error in Parsing the JSON");
	 	}*/
	 	
	 }

 
 public static void getReport(String id) {
	    DynamoDBMapper mapper = new DynamoDBMapper(client);
	    // Retrieve the updated item.
	    DynamoDBMapperConfig config = new DynamoDBMapperConfig(DynamoDBMapperConfig.ConsistentReads.CONSISTENT);
     PropertyDataClone updatedItem = mapper.load(PropertyDataClone.class, id, config);
     System.out.println("Retrieved the previously updated item:");
     //System.out.println(updatedItem)
	    /*
	 	try {
		           try {
	               table.putItem(new Item().withPrimaryKey("reportId", UUID.randomUUID().toString()).withString("report",
	                   currentNode.toString())
	         	  .withString("source", "DSAL")
	 			  .withString("requestor", "PLPC")
	               .withString("createTS",new Timestamp(System.currentTimeMillis()).toString()));
	               
	         	//  table.putItem(item);
	      
	           }
	           catch (Exception e) {
	               System.err.println("Create items failed.");
	               System.err.println(e.getMessage());

	                }
	       
	       parser.close();
	 	}catch(Exception e) {
	 		System.err.println("Error in Parsing the JSON");
	 	}*/
	 	
	 }
 public static void createItems(String json) {
 	Table table = dynamoDB.getTable(tableName);
     
 	try {
 	JsonParser parser = new JsonFactory().createJsonParser(json);

       JsonNode rootNode = new ObjectMapper().readTree(parser);
    
       ObjectNode currentNode;
       currentNode =(ObjectNode) rootNode;

//           int year = currentNode.path("year").asInt();
//           String title = currentNode.path("title").asText();

           try {
               table.putItem(new Item().withPrimaryKey("reportId", UUID.randomUUID().toString()).withString("report",
                   currentNode.toString())
         	  .withString("source", "DSAL")
 			  .withString("requestor", "PLPC")
               .withString("createTS",new Timestamp(System.currentTimeMillis()).toString()));
               
         	//  table.putItem(item);
      
           }
           catch (Exception e) {
               System.err.println("Create items failed.");
               System.err.println(e.getMessage());

                }
       
       parser.close();
 	}catch(Exception e) {
 		System.err.println("Error in Parsing the JSON");
 	}
 	
 }

 public static String retrieveItem(String reportId) {
     Table table = dynamoDB.getTable(tableName);
     String reportJson=null;
     try {

     	  GetItemSpec spec = new GetItemSpec().withPrimaryKey("reportId", reportId);
     	  Item item = table.getItem(spec);
           
System.out.println(item.getJSONPretty("report"));
//         Item item = table.getItem("reportId", 100, "reportId, report, requestor, source", null);

         System.out.println("Printing item after retrieving it....");
         System.out.println(item.toJSONPretty());
         reportJson= item.toJSONPretty();

     }
     catch (Exception e) {
         System.err.println("GetItem failed.");
         System.err.println(e.getMessage());
     }
     return reportJson;

 }

 public static void updateAddNewAttribute() {
     Table table = dynamoDB.getTable(tableName);

     try {

         UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("Id", 121)
             .withUpdateExpression("set #na = :val1").withNameMap(new NameMap().with("#na", "NewAttribute"))
             .withValueMap(new ValueMap().withString(":val1", "Some value")).withReturnValues(ReturnValue.ALL_NEW);

         UpdateItemOutcome outcome = table.updateItem(updateItemSpec);

         // Check the response.
         System.out.println("Printing item after adding new attribute...");
         System.out.println(outcome.getItem().toJSONPretty());

     }
     catch (Exception e) {
         System.err.println("Failed to add new attribute in " + tableName);
         System.err.println(e.getMessage());
     }
 }

 public static void updateMultipleAttributes() {

     Table table = dynamoDB.getTable(tableName);

     try {

         UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("Id", 120)
             .withUpdateExpression("add #a :val1 set #na=:val2")
             .withNameMap(new NameMap().with("#a", "Authors").with("#na", "NewAttribute"))
             .withValueMap(
                 new ValueMap().withStringSet(":val1", "Author YY", "Author ZZ").withString(":val2", "someValue"))
             .withReturnValues(ReturnValue.ALL_NEW);

         UpdateItemOutcome outcome = table.updateItem(updateItemSpec);

         // Check the response.
         System.out.println("Printing item after multiple attribute update...");
         System.out.println(outcome.getItem().toJSONPretty());

     }
     catch (Exception e) {
         System.err.println("Failed to update multiple attributes in " + tableName);
         System.err.println(e.getMessage());

     }
 }

 public static void updateExistingAttributeConditionally() {

     Table table = dynamoDB.getTable(tableName);

     try {

         // Specify the desired price (25.00) and also the condition (price =
         // 20.00)

         UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("Id", 120)
             .withReturnValues(ReturnValue.ALL_NEW).withUpdateExpression("set #p = :val1")
             .withConditionExpression("#p = :val2").withNameMap(new NameMap().with("#p", "Price"))
             .withValueMap(new ValueMap().withNumber(":val1", 25).withNumber(":val2", 20));

         UpdateItemOutcome outcome = table.updateItem(updateItemSpec);

         // Check the response.
         System.out.println("Printing item after conditional update to new attribute...");
         System.out.println(outcome.getItem().toJSONPretty());

     }
     catch (Exception e) {
         System.err.println("Error updating item in " + tableName);
         System.err.println(e.getMessage());
     }
 }

 public static void deleteItem() {

     Table table = dynamoDB.getTable(tableName);

     try {
     	
     	  DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey("reportId", "100")
                  .withReturnValues(ReturnValue.ALL_OLD);


       /*  DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey("Id", 120)
             .withConditionExpression("#ip = :val").withNameMap(new NameMap().with("#ip", "InPublication"))
             .withValueMap(new ValueMap().withBoolean(":val", false)).withReturnValues(ReturnValue.ALL_OLD);
*/
         DeleteItemOutcome outcome = table.deleteItem(deleteItemSpec);

         // Check the response.
         System.out.println("Printing item that was deleted...");
         System.out.println(outcome.getItem().toJSONPretty());

     }
     catch (Exception e) {
         System.err.println("Error deleting item in " + tableName);
         System.err.println(e.getMessage());
     }
 }
}

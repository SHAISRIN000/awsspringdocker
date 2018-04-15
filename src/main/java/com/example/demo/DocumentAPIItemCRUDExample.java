// Copyright 2012-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
// Licensed under the Apache License, Version 2.0.
package com.example.demo;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
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


public class DocumentAPIItemCRUDExample {

    static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
    static DynamoDB dynamoDB = new DynamoDB(client);
    
    
    static String tableName = "DSAL.RawReport";

    public static void main(String[] args) throws IOException {

        createItems();

        retrieveItem();

        // Perform various updates.
//        updateMultipleAttributes();
//        updateAddNewAttribute();
//        updateExistingAttributeConditionally();
//
//        // Delete the item.
//        deleteItem();

    }

    private static void createItems() {

        Table table = dynamoDB.getTable(tableName);
        try {

        	  Item item = new Item().withPrimaryKey("reportId", UUID.randomUUID().toString())
        			  .withString("source", "DSAL")
        			  .withString("requestor", "PLPC")
                      .withString("createTS",new Timestamp(System.currentTimeMillis()).toString())
                      .withString("report", "{\n\"address\": {\n\"addressLine1\": \"5201 Brookside Dr\",\n\"addressLine2\": \" \",\n\"city\": \"Madison\",\n\"state\": \"WI\",\n\"zipcode\": \"53718\"\n},\n\"elements\": [\n{\n\"key\": \"numberOfStories\",\n\"value\": \"3+\",\n\"image\": \"/images/9e95f141-08dd-46c1-aa0a-7218bf1976ab_png\",\n\"confidence\": \"99\"\n}\n]\n}");
              
        	  table.putItem(item);
                  
                  
        /*    Item item = new Item().withPrimaryKey("Id", 120).withString("Title", "Book 120 Title")
                .withString("ISBN", "120-1111111111")
                .withStringSet("Authors", new HashSet<String>(Arrays.asList("Author12", "Author22")))
                .withNumber("Price", 20).withString("Dimensions", "8.5x11.0x.75").withNumber("PageCount", 500)
                .withBoolean("InPublication", false).withString("ProductCategory", "Book");
            table.putItem(item);

            item = new Item().withPrimaryKey("Id", 121).withString("Title", "Book 121 Title")
                .withString("ISBN", "121-1111111111")
                .withStringSet("Authors", new HashSet<String>(Arrays.asList("Author21", "Author 22")))
                .withNumber("Price", 20).withString("Dimensions", "8.5x11.0x.75").withNumber("PageCount", 500)
                .withBoolean("InPublication", true).withString("ProductCategory", "Book");
            table.putItem(item);*/

        }
        catch (Exception e) {
            System.err.println("Create items failed.");
            System.err.println(e.getMessage());

        }
    }

    private static void retrieveItem() {
        Table table = dynamoDB.getTable(tableName);
        

      

        try {

        	  GetItemSpec spec = new GetItemSpec().withPrimaryKey("reportId", "100");
        	  Item item = table.getItem(spec);
              
System.out.println(item.getJSONPretty("report"));
//            Item item = table.getItem("reportId", 100, "reportId, report, requestor, source", null);

            System.out.println("Printing item after retrieving it....");
            System.out.println(item.toJSONPretty());

        }
        catch (Exception e) {
            System.err.println("GetItem failed.");
            System.err.println(e.getMessage());
        }

    }

    private static void updateAddNewAttribute() {
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

    private static void updateMultipleAttributes() {

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

    private static void updateExistingAttributeConditionally() {

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

    private static void deleteItem() {

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

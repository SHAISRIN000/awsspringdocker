package com.shaik.dataaggregator.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class DataElements {
 String key;
 String value;
 String image;
 String confidence;
 
public DataElements() {
	
}
 
public DataElements(String key, String value, String image,String confidence) {
	super();
	this.key = key;
	this.value = value;
	this.image = image;
	this.confidence=confidence;
}
@Override
public String toString() {
	return "DataElements [key=" + key + ", value=" + value + ", image=" + image + ", confidence=" + confidence + "]";
}

@DynamoDBAttribute(attributeName = "key")
public String getKey() {
	return key;
}
public void setKey(String key) {
	this.key = key;
}

@DynamoDBAttribute(attributeName = "value")
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}

@DynamoDBAttribute(attributeName = "imageUrl")
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}

@DynamoDBAttribute(attributeName = "confidence")
public String getConfidence() {
	return confidence;
}
public void setConfidence(String confidence) {
	this.confidence = confidence;
}
 
}

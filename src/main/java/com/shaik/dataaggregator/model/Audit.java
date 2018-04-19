package com.shaik.dataaggregator.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class Audit {
  String consumer;
  String requestTS;
  String user;
public Audit() {
	super();
	// TODO Auto-generated constructor stub
}

@DynamoDBAttribute(attributeName = "consumer")
public String getConsumer() {
	return consumer;
}
public void setConsumer(String consumer) {
	this.consumer = consumer;
}

@DynamoDBAttribute(attributeName = "requestTS")
public String getRequestTS() {
	return requestTS;
}
public void setRequestTS(String requestedTS) {
	this.requestTS = requestedTS;
}

@DynamoDBAttribute(attributeName = "user")
public String getUser() {
	return user;
}
public void setUser(String user) {
	this.user = user;
}
}

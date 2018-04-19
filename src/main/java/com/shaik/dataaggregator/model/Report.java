package com.shaik.dataaggregator.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="DSAL.Report")
public class Report {
	public String id;
	List<ElementInference> elementInferences;
	Set<String> sources;
	String createTS;
	String status;
	
	@DynamoDBAttribute(attributeName="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Report() {
		elementInferences=new ArrayList<ElementInference>();
		sources=new HashSet<String>();
	}
	@Override
	public String toString() {
		return "Report [id=" + id + "]";
	}
	public Report(String id, List<ElementInference> elementInferences, Set<String> sources, String createTS) {
		super();
		this.id = id;
		this.elementInferences = elementInferences;
		this.sources = sources;
		this.createTS = createTS;
	}
	
	@DynamoDBHashKey(attributeName="reportId")  
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@DynamoDBAttribute(attributeName="ElementInferences") 
	public List<ElementInference> getElementInferences() {
		return elementInferences;
	}
	public void setElementInferences(List<ElementInference> elementInferences) {
		this.elementInferences = elementInferences;
	}
	
	@DynamoDBAttribute(attributeName="souces") 
	public Set<String> getSources() {
		return sources;
	}
	public void setSources(Set<String> sources) {
		this.sources = sources;
	}
	
	@DynamoDBAttribute(attributeName="createTS") 
	public String getCreateTS() {
		return createTS;
	}
	public void setCreateTS(String createTS) {
		this.createTS = createTS;
	}
	
	
}

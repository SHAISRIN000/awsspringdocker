package com.shaik.dataaggregator.model;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="DSAL.Report")
public class PropertyData {
	public String id;
	Address address;
	List<DataElements> elements=new ArrayList<>();
	String source;
	String createTS;
	
	@DynamoDBIgnore
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	
	

	public PropertyData(){
		super();
	}
	
	public PropertyData(Address address,List<DataElements> elements){
	this.address=address;
		this.elements=elements;
	}
	
	
	 @DynamoDBHashKey(attributeName="reportId")  
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	
	

	@DynamoDBAttribute(attributeName="report")  
	public List<DataElements> getElements() {
		return elements;
	}
	public void setElements(List<DataElements> elements) {
		this.elements = elements;
	}
	
	
	@DynamoDBAttribute(attributeName="source")  
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@DynamoDBAttribute(attributeName="createTS")  
	public String getCreateTS() {
		return createTS;
	}

	public void setCreateTS(String createTS) {
		this.createTS = createTS;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}

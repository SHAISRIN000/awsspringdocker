package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class PropertyData {

	Address address;
	List<DataElements> elements=new ArrayList<>();
	
	public PropertyData(){
		super();
	}
	
	public PropertyData(Address address,List<DataElements> elements){
		this.address=address;
		this.elements=elements;
	}
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<DataElements> getElements() {
		return elements;
	}
	public void setElements(List<DataElements> elements) {
		this.elements = elements;
	}
	
}

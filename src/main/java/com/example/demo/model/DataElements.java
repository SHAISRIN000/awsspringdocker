package com.example.demo.model;

public class DataElements {
 String key;
 String value;
 String image;
 String confidence;
 

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
public String getKey() {
	return key;
}
public void setKey(String key) {
	this.key = key;
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public String getConfidence() {
	return confidence;
}
public void setConfidence(String confidence) {
	this.confidence = confidence;
}
 
}

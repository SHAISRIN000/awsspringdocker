package com.shaik.dataaggregator.model;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class ElementInference {
	String key;
	String value;
	String imageId;
	String confidence;
	String modelVersion;
	String source;
	
	List<SubElementInference> subElementsInferences;

	public ElementInference() {
		subElementsInferences = new ArrayList<SubElementInference>();
	}

	public ElementInference(String key, String value, String image, String confidence,String modelVersion,String source) {
		super();
		this.key = key;
		this.value = value;
		this.imageId = image;
		this.confidence = confidence;
		this.modelVersion=modelVersion;
		this.source=source;
	}



	@Override
	public String toString() {
		return "ElementInference [key=" + key + ", value=" + value + ", imageId=" + imageId + ", confidence="
				+ confidence + ", modelVersion=" + modelVersion + ", source=" + source + "]";
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
		return imageId;
	}

	public void setImage(String image) {
		this.imageId = image;
	}

	@DynamoDBAttribute(attributeName = "confidence")
	public String getConfidence() {
		return confidence;
	}

	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}

	@DynamoDBAttribute(attributeName = "modelVersion")
	public String getModelVersion() {
		return modelVersion;
	}

	public void setModelVersion(String modelVersion) {
		this.modelVersion = modelVersion;
	}

	@DynamoDBAttribute(attributeName = "source")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@DynamoDBAttribute(attributeName = "imageId")
	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	@DynamoDBAttribute(attributeName = "subElementsInferences")
	public List<SubElementInference> getSubElementsInferences() {
		return subElementsInferences;
	}

	public void setSubElementsInferences(List<SubElementInference> subElementsInferences) {
		this.subElementsInferences = subElementsInferences;
	}

}

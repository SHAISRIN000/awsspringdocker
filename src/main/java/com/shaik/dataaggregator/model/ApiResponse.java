package com.shaik.dataaggregator.model;

import java.util.ArrayList;
import java.util.List;

public class ApiResponse {
String reportId;
List<ElementInference> elementInferences=new ArrayList<ElementInference>();

public ApiResponse(String reportId, List<ElementInference> elementInferences) {
	super();
	this.reportId = reportId;
	this.elementInferences = elementInferences;
}
public ApiResponse() {
	super();
	// TODO Auto-generated constructor stub
}

public String getReportId() {
	return reportId;
}
public void setReportId(String reportId) {
	this.reportId = reportId;
}
public List<ElementInference> getElementInferences() {
	return elementInferences;
}
public void setElementInferences(List<ElementInference> elementInferences) {
	this.elementInferences = elementInferences;
}

}

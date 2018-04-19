package com.shaik.dataaggregator.model;

public class Error {
String id;
String reportId;
String error;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getReportId() {
	return reportId;
}
public void setReportId(String reportId) {
	this.reportId = reportId;
}
public String getError() {
	return error;
}
public void setError(String error) {
	this.error = error;
}
public Error() {
	super();
	// TODO Auto-generated constructor stub
}

}

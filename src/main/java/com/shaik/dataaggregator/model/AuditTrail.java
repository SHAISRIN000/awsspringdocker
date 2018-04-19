package com.shaik.dataaggregator.model;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;

public class AuditTrail {
	
	String reportId;
	List<Audit> audit=new ArrayList<Audit>();

	public AuditTrail() {
		super();
		//TODO Auto-generated constructor stub
	}
	@DynamoDBHashKey(attributeName="reportId")  
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	
	@DynamoDBHashKey(attributeName="audit")  
	public List<Audit> getAudit() {
		return audit;
	}
	public void setAudit(List<Audit> audit) {
		this.audit = audit;
	}
	
}

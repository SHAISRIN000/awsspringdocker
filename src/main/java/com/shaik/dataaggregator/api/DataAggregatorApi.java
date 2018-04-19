package com.shaik.dataaggregator.api;

import java.io.IOException;

import org.codehaus.jackson.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.shaik.dataaggregator.db.DynamoDBClient;
import com.shaik.dataaggregator.model.Mapper;
import com.shaik.dataaggregator.model.ModelUtil;
import com.shaik.dataaggregator.model.PropertyData;
import com.shaik.dataaggregator.model.Report;

@RestController()
@RequestMapping("/propertydataaggregator")
public class DataAggregatorApi {

	long tockenExpireTS;
	 
	  @Autowired
	    private OAuth2RestOperations restTemplate;

	  
  @RequestMapping(value = "/{id}/propertydata", method = RequestMethod.GET)
    public ResponseEntity<?> getPropertyDataById(@PathVariable("id") String id) {
        System.out.println("Fetching report with id {}"+id);
        PropertyData data= DynamoDBClient.retrieveReport(id);
         return new ResponseEntity<PropertyData>(data, HttpStatus.OK);
   }
  
  @RequestMapping(value = "/{id}/propertyRawData", method = RequestMethod.GET)
  public ResponseEntity<?> getPropertyRawDataById(@PathVariable("id") String id) {
      System.out.println("Fetching report with id {}"+id);
      String data= DynamoDBClient.retrieveRawReport(id);
      return new ResponseEntity<String>(data, HttpStatus.OK);    
  }
   
  @RequestMapping(value = "/property", method = RequestMethod.GET)
  public ResponseEntity<?> getPropertyData(@RequestParam("address") String address,@RequestParam("consumer") String consumer,@RequestParam(name="forceOrder", required=false) boolean forceOrder,@RequestParam(name="lookupDays", required=false) String days) {

	/*
	 * Validate the request parameters
	 * Check if the address already exists based on the number of days ,if it is then return the report(It requires the request scan need to find dynamodb efficient way
	 *       if the forceORder flag is set then fetch the report again from the Vendor
	 *        
	 */
	 
	  
	  PropertyData data=new PropertyData();
	  Report report=null;
	
	String reportId=DynamoDBClient.insertRequest(address,consumer);
	
	String rawResponse=getRestTemplate().getForObject("https://api.property.vision/property?address="+address, String.class);
	String source="PropertyVision";
	String sourceId=reportId+"_"+"source";
	DynamoDBClient.insertRawReport(reportId,rawResponse,source,sourceId);
	  report=ModelUtil.createReport();
		  
	/*  try {
		  
		  //This need to be the Report in DynamoDB
	//	data=Mapper.translate(rawResponse, address);
	} catch (JsonProcessingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}*/
	  data.setId(reportId);
	  data.setSource(source);
	  DynamoDBClient.createReport(report);
	  DynamoDBClient.updateRequestStatus(reportId, "completed");
	  
	  //Insert AuditLog
	  //

	  if (data == null) {
      	 System.out.println("DSAL report Error.");
          return new ResponseEntity("DSAL Report No found", HttpStatus.NOT_FOUND);
      }
	  
	  //ApiResponse populate;
      return new ResponseEntity<PropertyData>(data, HttpStatus.OK);
  }
	
  private OAuth2RestOperations getRestTemplate() {
		long currentTS= System.currentTimeMillis();
	    OAuth2AccessToken token;
		if (System.currentTimeMillis()>tockenExpireTS) {
			token=restTemplate.getAccessToken();
			tockenExpireTS=System.currentTimeMillis()+token.getExpiresIn()*1000;
			System.out.println("Oauth Token"+token.getValue());
			System.out.println("Expires"+token.getExpiresIn());	
		}
		return restTemplate;
  }
  
  @RequestMapping(value = "/ping", method = RequestMethod.GET)
  public ResponseEntity<?> getPing(@RequestParam(name="type",required=false) String pingType) {
	 	String str=getRestTemplate().getForObject("https://api.property.vision/property?address=3142+Lindbergh+St+Madison+WI+53704", String.class);
		System.out.println(str);	
  }
}

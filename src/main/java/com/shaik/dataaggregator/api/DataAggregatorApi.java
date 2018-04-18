package com.shaik.dataaggregator.api;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.shaik.dataaggregator.db.DynamoDBClient;
import com.shaik.dataaggregator.model.Address;
import com.shaik.dataaggregator.model.Mapper;
import com.shaik.dataaggregator.model.PropertyData;

@RestController()
@RequestMapping("/propertydataaggregator")
public class DataAggregatorApi {

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
  public ResponseEntity<?> getPropertyData(@RequestParam("address") String reque) {
	 //Validate the request
	  
	  
	  String request="{\"addressLine1\": \"5201 Brookside Dr\", \"addressLine2\": \" \" , \"city\": \"Madison\", \"state\": \"WI\", \"zipcode\": \"53718\"}";
	String consumer="PC"; 
	 
	 ObjectMapper mapper=new ObjectMapper();
	Address address=null;
	try {
		address = mapper.readValue(request, Address.class);
	} catch (JsonParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JsonMappingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	PropertyData data=new PropertyData();
	
	String reportId=DynamoDBClient.insertRequest(request,consumer);
	//System.out.println("Address"+address.toString());
	//Invoke the DSAL Service 
	
	
	//  Object dsalJSON =  oAuth2RestTemplate().getForObject("http://localhost:8080/sampleresponse.json", Object.class);
	  RestTemplate restTemplate1 = new RestTemplate();
	  
	 // Object dsalJSON =  restTemplate.getForObject("http://localhost:8080/sampleresponse.json", Object.class);
	  String rawResponse =  restTemplate1.getForObject("http://localhost:8080/sampleresponse.json", String.class);
	String source="PropertyVision";
	String sourceId=reportId+"_"+"source";
	  DynamoDBClient.insertRawReport(reportId,rawResponse,source,sourceId);
	  
	  try {
		data=Mapper.translate(rawResponse, request);
	} catch (JsonProcessingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	  data.setId(reportId);
	  data.setSource(source);
	  DynamoDBClient.createReport(data);

	  if (data == null) {
      	 System.out.println("DSAL report Error.");
          return new ResponseEntity("DSAL Report No found", HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<PropertyData>(data, HttpStatus.OK);
  }
	
  
  @RequestMapping(value = "/ping", method = RequestMethod.GET)
  public ResponseEntity<?> getPing(@RequestParam("type") String pingType) {
	  OAuth2AccessToken token=restTemplate.getAccessToken();
		System.out.println("Oauth Token"+token.getValue());
		System.out.println("Expires"+token.getExpiresIn());
	    String str=restTemplate.getForObject("https://api.property.vision/property?address=3142+Lindbergh+St+Madison+WI+53704", String.class);
		System.out.println(str);	  
		
	  //pingType indicates None or Component
	  
	  return new ResponseEntity<String>("success", HttpStatus.OK);
  }

}

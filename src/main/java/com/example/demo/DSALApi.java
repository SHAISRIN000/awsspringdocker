package com.example.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.validator.internal.metadata.aggregated.PropertyMetaData;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Address;
import com.example.demo.model.DataElements;
import com.example.demo.model.Mapper;
import com.example.demo.model.ModelUtil;
import com.example.demo.model.PropertyData;


@RestController()
@RequestMapping("/propertydataaggregator")
public class DSALApi {

	@Value("${application.externalAccessUrl}")
	String externalAccessUrl="";
	
	@Value("${application.clientId}")
	String clientId="";
	
	@Value("${application.clientSecret}")
	String clientSecret="";
	
	@RequestMapping("/property1")
	public String property(){
		return "{age:10 ,name:shaik}";
		
	}
	
  @RequestMapping(value = "/{id}/propertydata", method = RequestMethod.GET)
    public ResponseEntity<?> retrievePropertyData(@PathVariable("id") String id) {
        System.out.println("Fetching report with id {}"+id);
        PropertyData data= DynamoDBClient.getReport(id);
        //4c6a2cf3-5439-47aa-b2d9-60773b58eb65
        
        /*PropertyData data=ModelUtil.getData();
        if (data == null) {
        	 System.out.println("Report with id {} not found."+id);
            return new ResponseEntity("Report No found", HttpStatus.NOT_FOUND);
        }*/
        return new ResponseEntity<PropertyData>(data, HttpStatus.OK);
        //return new ResponseEntity<PropertyData>(data, HttpStatus.OK);
    }
  
  
  
  @RequestMapping(value = "/{id}/propertyRawData", method = RequestMethod.GET)
  public ResponseEntity<?> retrievePropertyRawData(@PathVariable("id") String id) {
      System.out.println("Fetching report with id {}"+id);
      String data= DynamoDBClient.retrieveRawReport(id);
      //4c6a2cf3-5439-47aa-b2d9-60773b58eb65
      
      /*PropertyData data=ModelUtil.getData();
      if (data == null) {
      	 System.out.println("Report with id {} not found."+id);
          return new ResponseEntity("Report No found", HttpStatus.NOT_FOUND);
      }*/
      return new ResponseEntity<String>(data, HttpStatus.OK);
      //return new ResponseEntity<PropertyData>(data, HttpStatus.OK);
  }
  
  
	
  @RequestMapping(value = "/property", method = RequestMethod.GET)
  public ResponseEntity<?> getPropertyData(@RequestParam("address") String Stringaddress) {
	 String address1="{\"addressLine1\": \"5201 Brookside Dr\", \"addressLine2\": \" \" , \"city\": \"Madison\", \"state\": \"WI\", \"zipcode\": \"53718\"}";
	 ObjectMapper mapper=new ObjectMapper();
	Address address=null;
	try {
		address = mapper.readValue(address1, Address.class);
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
	
	  System.out.println("Address"+address.toString());
	  DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
	//  Object dsalJSON =  oAuth2RestTemplate().getForObject("http://localhost:8080/sampleresponse.json", Object.class);
	  RestTemplate restTemplate = new RestTemplate();
	 // Object dsalJSON =  restTemplate.getForObject("http://localhost:8080/sampleresponse.json", Object.class);
	  String dsalJSONstr =  restTemplate.getForObject("http://localhost:8080/sampleresponse.json", String.class);
		
	  DynamoDBClient.createRawReport(dsalJSONstr,address1);
	  
	  try {
		data=Mapper.translate(dsalJSONstr, address1);
	} catch (JsonProcessingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	  
	  DynamoDBClient.createReport(data);

	  if (data == null) {
      	 System.out.println("DSAL report Error.");
          return new ResponseEntity("DSAL Report No found", HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<PropertyData>(data, HttpStatus.OK);
  }
	
  
 public OAuth2RestTemplate oAuth2RestTemplate() {
	 
	 ClientCredentialsAccessTokenProvider provider=new ClientCredentialsAccessTokenProvider();
		
		ClientCredentialsResourceDetails resource=new ClientCredentialsResourceDetails();
		resource.setClientAuthenticationScheme(AuthenticationScheme.form);
		resource.setClientId(clientId);
		resource.setClientSecret(clientSecret);
		resource.setAccessTokenUri(externalAccessUrl+"/pcmsrest/oauth/token");
		
		OAuth2AccessToken accessTokenObject=provider.obtainAccessToken(resource, new DefaultAccessTokenRequest());

		// Did we get a token? This prints on the server log.
		System.out.println("Access Token:"+accessTokenObject.getValue());
		DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource, clientContext);
        return restTemplate;
	
 }
}

package com.shaik.dataaggregator.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableOAuth2Client
@Configuration
public class OAuthConfig {
	
	/*@Value("${application.externalAccessUrl}")
	String externalAccessUrl="";
	
	@Value("${application.clientId}")
	String clientId="";
	
	@Value("${application.clientSecret}")
	String clientSecret="";
	
	OAuth2RestTemplate restTemplate;
	
	
	
	@Bean
	public OAuth2ProtectedResourceDetails resource() {
		ClientCredentialsResourceDetails resource=new ClientCredentialsResourceDetails();
		resource.setClientAuthenticationScheme(AuthenticationScheme.form);
		resource.setClientId(clientId);
		resource.setClientSecret(clientSecret);
		resource.setAccessTokenUri(externalAccessUrl+"/pcmsrest/oauth/token");
		return resource;
		
	}
	
	@Bean
	 public OAuth2RestOperations  restTemplate() {
		AccessTokenRequest atr = new DefaultAccessTokenRequest();
		DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext(atr);
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource(), clientContext);
        return restTemplate;
		
	 }*/
}

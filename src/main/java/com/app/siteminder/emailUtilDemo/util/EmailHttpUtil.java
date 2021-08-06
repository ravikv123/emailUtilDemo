package com.app.siteminder.emailUtilDemo.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * @author ravi
 *
 */
@Component
public class EmailHttpUtil {

	@Autowired
	private RestTemplate restTemplate;
	
	
	/**
	 * @param url
	 * @param auth
	 * @param emailInfo
	 * @param server
	 * @throws CustomException
	 * Calls HTTP(S) service url using restTemplate
	 */
	public void sendMail(String url, String auth, Object emailInfo, String server) throws CustomException
	{
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization",auth);
		HttpEntity<Object> entity = new HttpEntity<>(emailInfo, headers);
		ResponseEntity<String> response = null;
		try
		{
			response = restTemplate.postForEntity(url, entity, String.class);
		}
		catch(HttpClientErrorException e)
		{
			throw new CustomException(server + ": Error while sending email - Received status:"+e.getLocalizedMessage());
		}
		if(!(HttpStatus.OK == response.getStatusCode() || HttpStatus.ACCEPTED == response.getStatusCode()))
		{	
			throw new CustomException(server + ": Error while sending email - Received status Code:"+response.getStatusCode());
		}
	}
	
	
	
}

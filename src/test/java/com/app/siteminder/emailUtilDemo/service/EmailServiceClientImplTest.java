package com.app.siteminder.emailUtilDemo.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.app.siteminder.emailUtilDemo.dto.EmailDetails;
import com.app.siteminder.emailUtilDemo.dto.EmailMessageDTO;
import com.app.siteminder.emailUtilDemo.dto.EmailMessageService2DTO;
import com.app.siteminder.emailUtilDemo.util.CustomException;
import com.app.siteminder.emailUtilDemo.util.EmailHttpUtil;

@RunWith(SpringRunner.class)
@AutoConfigureWebClient
public class EmailServiceClientImplTest {

	@InjectMocks
	EmailServiceClient1Impl client1;
	
	@InjectMocks
	EmailServiceClient2Impl client2;
	
	@Mock
	EmailHttpUtil emailUtil;
	
	
	@Test
	public void sendMail1() throws CustomException {
		
		Mockito.doNothing().when(emailUtil).sendMail(Mockito.anyString(), Mockito.anyString(), Mockito.any(EmailMessageDTO.class),Mockito.anyString());		
		client1.sendMail(getSucessReqJson());
}
	@Test
	public void sendMail2() throws CustomException {
		
		Mockito.doNothing().when(emailUtil).sendMail(Mockito.anyString(), Mockito.anyString(), Mockito.any(EmailMessageDTO.class),Mockito.anyString());		
		client2.sendMail(getSucessReqJson());
}

	private EmailMessageDTO getSucessReqJson() {
		EmailMessageDTO requestJson = new EmailMessageDTO();
		List<EmailDetails> emailList = new ArrayList<EmailDetails>() ;
		EmailDetails email = new EmailDetails();
		email.setEmail("abc@abc.com");
		email.setName("name");
		emailList.add(email);
		requestJson.setTo(emailList);
		requestJson.setCc(emailList);
		requestJson.setBcc(emailList);
		requestJson.setFrom("Sender");
		requestJson.setSubject("Test");
		requestJson.setEmailBody("email body");
		requestJson.setTo(emailList);
		return requestJson;
	}

}

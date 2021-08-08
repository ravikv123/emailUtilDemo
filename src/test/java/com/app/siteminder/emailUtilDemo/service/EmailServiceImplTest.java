package com.app.siteminder.emailUtilDemo.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.test.context.junit4.SpringRunner;
import com.app.siteminder.emailUtilDemo.dto.EmailDetails;
import com.app.siteminder.emailUtilDemo.dto.EmailMessageDTO;
import com.app.siteminder.emailUtilDemo.util.CustomException;

@RunWith(SpringRunner.class)
@AutoConfigureWebClient
public class EmailServiceImplTest {
	
	@InjectMocks
	private EmailServiceImpl emailService;

	@Mock
	@Qualifier("service1")
	EmailServiceClient service1;
	
	@Mock
	@Qualifier("service2")
	EmailServiceClient service2;


	@Test
	public void testSendMail1() throws CustomException {
		Mockito.doNothing().when(service1).sendMail(Mockito.any(EmailMessageDTO.class));		
		emailService.sendMail(getSucessReqJson());		
	}
	
	@Test
	public void testSendMail2() throws CustomException {
		Mockito.doThrow(CustomException.class).when(service1).sendMail(Mockito.any(EmailMessageDTO.class));		
		Mockito.doNothing().when(service2).sendMail(Mockito.any(EmailMessageDTO.class));		
		emailService.sendMail(getSucessReqJson());		
	}
	
	@Test(expected = CustomException.class)
	public void testSendMail3() throws CustomException {
		Mockito.doThrow(CustomException.class).when(service1).sendMail(Mockito.any(EmailMessageDTO.class));		
		Mockito.doThrow(CustomException.class).when(service2).sendMail(Mockito.any(EmailMessageDTO.class));		
		emailService.sendMail(getSucessReqJson());		
	}
	
	private EmailMessageDTO getSucessReqJson() {
		EmailMessageDTO requestJson = new EmailMessageDTO();
		List<EmailDetails> emailList = new ArrayList<EmailDetails>() ;
		EmailDetails email = new EmailDetails();
		email.setEmail("abc@abc.com");
		email.setName("name");
		emailList.add(email);
		requestJson.setTo(emailList);
		requestJson.setFrom("Sender");
		requestJson.setSubject("Test");
		requestJson.setEmailBody("email body");
		requestJson.setTo(emailList);
		return requestJson;
	}

}

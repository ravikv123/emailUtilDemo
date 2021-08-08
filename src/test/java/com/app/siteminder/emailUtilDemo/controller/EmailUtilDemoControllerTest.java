package com.app.siteminder.emailUtilDemo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.app.siteminder.emailUtilDemo.dto.EmailDetails;
import com.app.siteminder.emailUtilDemo.dto.EmailMessageDTO;
import com.app.siteminder.emailUtilDemo.service.EmailService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(EmailUtilDemoController.class)
@AutoConfigureWebClient 
public class EmailUtilDemoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmailService emailService;
		
	@Mock
	private TestRestTemplate template = new TestRestTemplate();
	
	@Test
	public void testSendMail1() throws Exception {
				
		EmailMessageDTO requestJson = getSucessReqJson();
		String requestjsonStr = new ObjectMapper().writeValueAsString(requestJson);
		Mockito.doNothing().when(emailService).sendMail(Mockito.any(EmailMessageDTO.class));		
		System.out.println(mockMvc.toString());
		this.mockMvc.perform(post("/email/message").content(requestjsonStr).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(jsonPath("$.statusMessage").value("Email Sent Successfully"));
	}
	
	@Test
	public void testSendMail2() throws Exception {
				
		EmailMessageDTO requestJson = getFailReqJson1();
		String requestjsonStr = new ObjectMapper().writeValueAsString(requestJson);
		Mockito.doNothing().when(emailService).sendMail(Mockito.any(EmailMessageDTO.class));		
		System.out.println(mockMvc.toString());
		this.mockMvc.perform(post("/email/message").content(requestjsonStr).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
	}

	@Test
	public void testSendMail3() throws Exception {
				
		EmailMessageDTO requestJson = getFailReqJson2();
		String requestjsonStr = new ObjectMapper().writeValueAsString(requestJson);
		Mockito.doNothing().when(emailService).sendMail(Mockito.any(EmailMessageDTO.class));		
		System.out.println(mockMvc.toString());
		this.mockMvc.perform(post("/email/message").content(requestjsonStr).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
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

	private EmailMessageDTO getFailReqJson1() {
		EmailMessageDTO requestJson = new EmailMessageDTO();
		List<EmailDetails> emailList = new ArrayList<EmailDetails>() ;
		EmailDetails email = new EmailDetails();
		email.setEmail("abc@abc.com");
		email.setName("name");
		emailList.add(email);
		requestJson.setFrom("Sender");
		requestJson.setSubject("Test");
		requestJson.setEmailBody("email body");
		return requestJson;
	}
	
	private EmailMessageDTO getFailReqJson2() {
		EmailMessageDTO requestJson = new EmailMessageDTO();
		List<EmailDetails> emailList = new ArrayList<EmailDetails>() ;
		EmailDetails email = new EmailDetails();
		email.setName("name");
		emailList.add(email);
		requestJson.setFrom("Sender");
		requestJson.setSubject("Test");
		requestJson.setEmailBody("email body");
		requestJson.setTo(emailList);
		return requestJson;
	}
}

package com.app.siteminder.emailUtilDemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.siteminder.emailUtilDemo.dto.Content;
import com.app.siteminder.emailUtilDemo.dto.EmailDetails;
import com.app.siteminder.emailUtilDemo.dto.EmailMessageDTO;
import com.app.siteminder.emailUtilDemo.dto.EmailMessageService2DTO;
import com.app.siteminder.emailUtilDemo.dto.Personalization;
import com.app.siteminder.emailUtilDemo.util.CustomException;
import com.app.siteminder.emailUtilDemo.util.EmailHttpUtil;

@Service("service2")
public class EmailServiceClient2Impl implements EmailServiceClient {

	@Autowired
	EmailHttpUtil emailUtil;
	
	@Value("${sendgrid.apiKey}")
	private String sendgridApiKey;
	
	@Value("${sendgrid.url}")
	private String url;
	
	@Value("${sendgrid.fromId}")
	private String sendgridFromId;
	@Value("${sendgrid.fromName}")
	private String sendgridFromName;

	@Override
	public void sendMail(EmailMessageDTO emailInfo) throws CustomException {
		emailUtil.sendMail(url,sendgridApiKey, convertEmailObject(emailInfo),"server2");
	}
	
	private EmailMessageService2DTO convertEmailObject(EmailMessageDTO emailInfo) {
		EmailMessageService2DTO obj = new EmailMessageService2DTO();
		List<Personalization> perList = new ArrayList<Personalization>();
		Personalization per = new Personalization();
		per.setTo(emailInfo.getTo());
		per.setSubject(emailInfo.getSubject());
		perList.add(per);
		obj.setPersonalizations(perList);
	
		if(emailInfo.getCc() != null && emailInfo.getCc().size() > 0)
		{
			List<Personalization> ccList = new ArrayList<Personalization>();
			Personalization cc = new Personalization();
			cc.setTo(emailInfo.getCc());
			cc.setSubject(emailInfo.getSubject());
			ccList.add(per);
			obj.addPersonalizations(ccList);
		}
		
		if(emailInfo.getBcc() != null && emailInfo.getBcc().size() > 0)
		{
			List<Personalization> bccList = new ArrayList<Personalization>();
			Personalization bcc = new Personalization();
			bcc.setTo(emailInfo.getBcc());
			bcc.setSubject(emailInfo.getSubject());
			bccList.add(per);
			obj.addPersonalizations(bccList);
		}
		
		List<Content> conList = new ArrayList<Content>();
		Content con = new Content();
		con.setType("text/plain");
		con.setValue(emailInfo.getEmailBody());
		conList.add(con);
		obj.setContent(conList);

		EmailDetails fromEmail = new EmailDetails();
		fromEmail.setEmail(sendgridFromId);
		fromEmail.setName(sendgridFromName);
		obj.setFrom(fromEmail);
		obj.setReply_to(fromEmail);
		return obj;
		
		
	}

}

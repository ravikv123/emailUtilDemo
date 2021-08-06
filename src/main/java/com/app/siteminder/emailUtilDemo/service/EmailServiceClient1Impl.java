package com.app.siteminder.emailUtilDemo.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.app.siteminder.emailUtilDemo.dto.EmailMessageDTO;
import com.app.siteminder.emailUtilDemo.util.CustomException;
import com.app.siteminder.emailUtilDemo.util.EmailHttpUtil;

@Service("service1")
public class EmailServiceClient1Impl implements EmailServiceClient {

	@Autowired
	EmailHttpUtil emailUtil;
	
	@Value("${mailgun.apiKey}")
	private String mailgunApiKey;
	
	@Value("${mailgun.url}")
	private String url;
	
	@Value("${mailgun.from}")
	private String mailgunFrom;

	
	/* (non-Javadoc)
	 * @see com.app.siteminder.emailUtilDemo.service.EmailServiceClient#sendMail(com.app.siteminder.emailUtilDemo.dto.EmailMessageDTO)
	 */
	@Override
	public void sendMail(EmailMessageDTO emailInfo) throws CustomException {
		
		emailInfo.setFrom(mailgunFrom);
		String vars = "?from="+emailInfo.getFrom()+"&to="+emailInfo.getTo().stream().map(email->email.getEmail()).collect(Collectors.joining(","))
				+((emailInfo.getCc().size() > 0)?"&cc="+(emailInfo.getCc().stream().map(email->email.getEmail()).collect(Collectors.joining(","))):null)
				+((emailInfo.getBcc().size() > 0)?"&bcc="+(emailInfo.getBcc().stream().map(email->email.getEmail()).collect(Collectors.joining(","))):null)
				+"&subject="+emailInfo.getSubject()+"&text="+emailInfo.getEmailBody();
	    emailUtil.sendMail(url+vars,mailgunApiKey, emailInfo,"server1");

	}

}

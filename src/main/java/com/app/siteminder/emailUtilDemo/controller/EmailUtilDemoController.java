package com.app.siteminder.emailUtilDemo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.siteminder.emailUtilDemo.dto.EmailMessageDTO;
import com.app.siteminder.emailUtilDemo.dto.EmailResponse;
import com.app.siteminder.emailUtilDemo.service.EmailService;
import com.app.siteminder.emailUtilDemo.util.CustomException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ravi
 *
 */
@RestController
@RequestMapping("/email")
public class EmailUtilDemoController {

	@Autowired
	EmailService emailService;

	
	/**
	 * @param emailInfo
	 * @return EmailResponse
	 * @throws CustomException
	 * Sends email 
	 */
	@PostMapping("/message")
	public EmailResponse sendMail( @Valid @RequestBody EmailMessageDTO emailInfo) throws CustomException {
	   emailService.sendMail(emailInfo);
	   EmailResponse resp = new EmailResponse();
	   resp.setStatusMessage("Email Sent Successfully");
	   return resp;
	}	
}

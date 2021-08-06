package com.app.siteminder.emailUtilDemo.service;

import com.app.siteminder.emailUtilDemo.dto.EmailMessageDTO;
import com.app.siteminder.emailUtilDemo.util.CustomException;

/**
 * @author ravi
 *
 */
public interface EmailService {

	/**
	 * @param emailInfo
	 * @throws CustomException
	 */
	public void sendMail(EmailMessageDTO emailInfo) throws CustomException;
}

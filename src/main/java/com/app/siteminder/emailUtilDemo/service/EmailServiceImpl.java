package com.app.siteminder.emailUtilDemo.service;

import java.lang.reflect.Field;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.test.util.ReflectionTestUtils;

import com.app.siteminder.emailUtilDemo.dto.EmailMessageDTO;
import com.app.siteminder.emailUtilDemo.util.CustomException;
import com.app.siteminder.emailUtilDemo.util.EmailHttpUtil;


/**
 * @author ravi
 * Create a new client class by implementing EmailServiceClient interface and autowire here with the next serviceX number
 */
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	@Qualifier("service1")
	EmailServiceClient service1;

	@Autowired
	@Qualifier("service2")
	EmailServiceClient service2;

	/**
	 * @param emailInfo
	 * @throws CustomException
	 */
	@Override
	public void sendMail(EmailMessageDTO emailInfo) throws CustomException {
		
			boolean isEnd = true;
			int currntNum = 1;
			CustomException exc = new CustomException();
			Class<?> thisClass = this.getClass();
			while(isEnd)
			{
				try
				{
					Field fld = thisClass.getDeclaredField("service"+currntNum);
					if(fld != null)
					{
						EmailServiceClient obj = (EmailServiceClient)(fld.get(this));
						obj.sendMail(emailInfo);
						isEnd = false;
					}
				}
				catch(NoSuchFieldException | IllegalArgumentException | IllegalAccessException e)
				{
					isEnd = false;
					throw exc;

				}
				catch(CustomException e)
				{
					exc.addErrorList(e.getErrorMessage());
					currntNum++;
				}
			}
	}
}

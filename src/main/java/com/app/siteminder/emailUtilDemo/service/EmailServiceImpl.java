package com.app.siteminder.emailUtilDemo.service;

import java.lang.reflect.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.app.siteminder.emailUtilDemo.dto.EmailMessageDTO;
import com.app.siteminder.emailUtilDemo.util.CustomException;


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
		
			boolean isEnd = false;
			int currntNum = 1;
			CustomException exc = new CustomException();
			Class<?> thisClass = this.getClass();
			while(!isEnd)
			{
				try
				{
					Field fld = thisClass.getDeclaredField("service"+currntNum);
					if(fld != null)
					{
						EmailServiceClient obj = (EmailServiceClient)(fld.get(this));
						obj.sendMail(emailInfo);
						isEnd = true;
					}
				}
				catch(NoSuchFieldException | IllegalArgumentException | IllegalAccessException e)
				{
					isEnd = true;
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

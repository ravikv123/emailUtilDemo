package com.app.siteminder.emailUtilDemo.dto;

import java.util.List;

import javax.validation.Constraint;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

public class EmailMessageDTO {
	
	
	@NotEmpty(message = "'To' List cannot be empty.")
	@NotNull(message = "'To' List cannot be empty.")
	@Valid
    private List<EmailDetails> to;
	@Valid
	private List<EmailDetails> cc;
	@Valid
	private List<EmailDetails> bcc;
	@NotNull
    private String subject;
    @NotNull
    private String emailBody;
    private String from;
    
	public List<EmailDetails> getTo() {
		return to;
	}
	public void setTo(List<EmailDetails> to) {
		this.to = to;
	}
	public List<EmailDetails> getCc() {
		return cc;
	}
	public void setCc(List<EmailDetails> cc) {
		this.cc = cc;
	}
	public List<EmailDetails> getBcc() {
		return bcc;
	}
	public void setBcc(List<EmailDetails> bcc) {
		this.bcc = bcc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEmailBody() {
		return emailBody;
	}
	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}
	
	
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	@Override
	public String toString() {
		return "EmailMessageDTO [from=" + from + ", to=" + to + ", cc=" + cc + ", bcc=" + bcc + ", subject=" + subject + ", emailBody="
				+ emailBody + "]";
	}
    
    

}

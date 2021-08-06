package com.app.siteminder.emailUtilDemo.dto;

import java.util.List;

public class Personalization {


    private List<EmailDetails> to;
	private List<EmailDetails> cc;
	private List<EmailDetails> bcc;

    private String subject;
	public List<EmailDetails> getTo() {
		return to;
	}
	public void setTo(List<EmailDetails> to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
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

}

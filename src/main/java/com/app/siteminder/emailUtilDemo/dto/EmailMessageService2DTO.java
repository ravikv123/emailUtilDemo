package com.app.siteminder.emailUtilDemo.dto;

import java.util.List;

public class EmailMessageService2DTO {
	  private List<Personalization> personalizations;
	    private List<Content> content;
	    private EmailDetails from;
	    private EmailDetails reply_to;
		public List<Personalization> getPersonalizations() {
			return personalizations;
		}
		public void setPersonalizations(List<Personalization> personalizations) {
			this.personalizations = personalizations;
		}
		public void addPersonalizations(List<Personalization> personalizations) {
			this.personalizations.addAll(personalizations);
		}
		
		public List<Content> getContent() {
			return content;
		}
		public void setContent(List<Content> content) {
			this.content = content;
		}
		public EmailDetails getFrom() {
			return from;
		}
		public void setFrom(EmailDetails from) {
			this.from = from;
		}
		public EmailDetails getReply_to() {
			return reply_to;
		}
		public void setReply_to(EmailDetails reply_to) {
			this.reply_to = reply_to;
		}
	    
	    
}





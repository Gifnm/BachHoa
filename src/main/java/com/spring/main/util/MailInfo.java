package com.spring.main.util;

import lombok.Data;

@Data
public class MailInfo {
	String from;
	String to;
	String[] cc;
	String[] bcc;
	String subject;
	String body;
	String[] attachments;

	// Để sẳn sử dụng 
	public MailInfo(String to, String subject, String body) {
		super();
		this.from = "phucnstps20362@fpt.edu.vn";
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
	// Tự điền gmail
	public MailInfo(String from, String to, String subject, String body) {
		super();
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
}

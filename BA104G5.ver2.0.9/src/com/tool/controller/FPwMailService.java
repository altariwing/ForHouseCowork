package com.tool.controller;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class FPwMailService {
	private final String username = "eatkaikai@gmail.com";
	private final String password = "e124217388";

	public void sendPassword(String rtr_name, String rtr_psw, String rtr_id) throws MessagingException {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("eatkaikai@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rtr_id));
			message.setSubject("[ For House房仲 ]會員: " + rtr_name + "您好");
			message.setText("Dear： " + rtr_name + " ，這是您的新密碼:" + rtr_psw);

			Transport.send(message);
			System.out.println("傳送新密碼成功!");
		} catch (MessagingException e) {
			System.out.println("傳送新密碼失敗!");
			e.printStackTrace();
		}
	}
}
package com.tool.controller;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class RtrMailOn implements Runnable {

	// 設定email帳號 & 密碼
	private final String username = "ba104g5@gmail.com";
	private final String password = "BA104++!!";

	// 傳入新加入房仲姓名、E-mail address
//	public void sendMail(String rtr_name, String rtr_id, String msg) throws MessagingException{
//		try {
//			// 設定使用SSL連線至Gmail server
//			Properties props = new Properties();
//			props.put("mail.smtp.host", "smtp.gmail.com");
//			props.put("mail.smtp.socketFactory.port", "465");
//			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//			props.put("mail.smtp.auth", "true");
//			props.put("mail.smtp.port", "465");
//
//			Session session = Session.getInstance(props, new Authenticator() {
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication(username, password);
//				}
//			});
//
//			// 文字部份，注意 img src 部份要用 cid:接下面附檔的header
//			MimeBodyPart textPart = new MimeBodyPart();
//			StringBuffer html = new StringBuffer();
//			html.append("<html><head>");
//			html.append("<title>For House 會員註冊通知</title></head><body>");
//			html.append("<div class='container' style='margin-top: 5em;'>");
//			html.append("<div class='row'>");
//			html.append("<div class='col-sm-6 col-sm-offset-3'>");
//			html.append("<table><thead><h3>For House 歡迎你加入[房仲]會員</h3></thead><br><tbody>");
//			html.append("<tr><h4>親愛的" + rtr_name + "會員您好:</h4></tr><br>");
//			html.append("<tr><h4>非常感謝您加入本網站!</h4></tr><br>");
//			html.append("<tr><h4>已經為您開啟使用本網站服務的權利了，請一定Ipad溫開水!</h4></tr><br></tbody></table>");
//			html.append("</div></div></div></body></html>");
//			html.append("<div class='col-sm-6 col-sm-offset-3'>");
//			html.append("<img src='cid:image'style='width:300px; height:300px;'/><br>");
//			html.append("</div>");
//			textPart.setContent(html.toString(), "text/html; charset=UTF-8");
//
//			// 圖檔部份，注意 html 用 cid:image，則header要設<image>
//			MimeBodyPart picturePart = new MimeBodyPart();
//			FileDataSource fds = new FileDataSource(
//					"D:/Servlet/BA104_WebApp/workspace_servlet/BA104G5/WebContent/images/realtorphoto/realtor7.jpg");
//
//			picturePart.setDataHandler(new DataHandler(fds));
//			picturePart.setFileName(fds.getName());
//			picturePart.setHeader("Content-ID", "<image>");
//
//			Multipart email = new MimeMultipart();
//			email.addBodyPart(textPart);
//			email.addBodyPart(picturePart);
//
//			MimeMessage message = new MimeMessage(session);
//			Transport transport = session.getTransport();
//
//			message.setContent(email);
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(rtr_id));// 收件者
//			message.setSubject("ForHouse系統通知");
//			transport.connect();
//			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
//			transport.close();
//			System.out.println("房仲傳送成功!");
//		} catch (MessagingException e) {
//			System.out.println("房仲傳送失敗!");
//			e.printStackTrace();
//		}
//
//		// Message message = new MimeMessage(session);
//		// message.setFrom(new InternetAddress(username));
//		// message.setRecipients(Message.RecipientType.TO,
//		// InternetAddress.parse(rtr_id));
//		// message.setSubject("ForHouse系統通知");
//		// message.setText("Dear: " + rtr_name + msg);
//		//
//		// Transport.send(message);
//		// System.out.println("傳送成功!");
//		// } catch (MessagingException e) {
//		// System.out.println("傳送失敗!");
//		// e.printStackTrace();
//		// }
//	}
	
	private final String rtr_name;
	private final  String rtr_id;
	private final  String msg;
	public RtrMailOn(String rtr_name, String rtr_id, String msg){
		this.rtr_name = rtr_name;
		this.rtr_id = rtr_id;
		this.msg = msg;
	}
	
	@Override
	public void run() {
		
		try {
			// 設定使用SSL連線至Gmail server
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

			// 文字部份，注意 img src 部份要用 cid:接下面附檔的header
			MimeBodyPart textPart = new MimeBodyPart();
			StringBuffer html = new StringBuffer();
			html.append("<html><head>");
			html.append("<title>For House 會員註冊通知</title></head><body>");
			html.append("<div class='container' style='margin-top: 5em;'>");
			html.append("<div class='row'>");
			html.append("<div class='col-sm-6 col-sm-offset-3'>");
			html.append("<table><thead><h3>For House 歡迎你加入[房仲]會員</h3></thead><br><tbody>");
			html.append("<tr><h4>親愛的" + rtr_name + "會員您好:</h4></tr><br>");
			html.append("<tr><h4>非常感謝您加入本網站!</h4></tr><br>");
			html.append("<tr><h4>已經為您開啟使用本網站服務的權利了，請一定Ipad溫開水!</h4></tr><br></tbody></table>");
			html.append("</div></div></div></body></html>");
			html.append("<div class='col-sm-6 col-sm-offset-3'>");
			html.append("<img src='cid:image'style='width:300px; height:300px;'/><br>");
			html.append("</div>");
			textPart.setContent(html.toString(), "text/html; charset=UTF-8");

			// 圖檔部份，注意 html 用 cid:image，則header要設<image>
			MimeBodyPart picturePart = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(
					"D:/MyProject/Project_workspace/BA104G5.ver2.1.0/WebContent/images/realtorphoto/realtor1.jpg");

			picturePart.setDataHandler(new DataHandler(fds));
			picturePart.setFileName(fds.getName());
			picturePart.setHeader("Content-ID", "<image>");

			Multipart email = new MimeMultipart();
			email.addBodyPart(textPart);
			email.addBodyPart(picturePart);

			MimeMessage message = new MimeMessage(session);
			Transport transport = session.getTransport();

			message.setContent(email);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(rtr_id));// 收件者
			message.setSubject("ForHouse系統通知");
			transport.connect();
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
			System.out.println("房仲傳送成功!");
		} catch (MessagingException e) {
			System.out.println("房仲傳送失敗!");
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws MessagingException {

		String rtr_name = "meow meow";
		String rtr_id = "eatkaikai@gmail.com";
		// String rtr_id = "eatkaikai@gmail.com";
		// String rtr_id = "yushihching@gmail.com";
		String msg = "非常感謝你加入本網站[ 房仲 ]會員，已經為你開啟使用本網站服務的權利了，請一定Ipad溫開水!" + "\r\n" + "請不用懷疑，馬上點開下面網址: "
				+ "http://localhost:8081/BA104G5/front/realtor/realtor_login.jsp";

		RtrMailOn mailService = new RtrMailOn(rtr_name, rtr_id, msg);
		Thread thread = new Thread(mailService);
		thread.start();

	}

}
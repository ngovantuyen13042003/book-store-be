package util;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {

	public static void sendEmail(String to, String subject, String body) throws MessagingException {

		String from = "ngovantuyen13042003@gmail.com"; // Địa chỉ email người gửi
		String password = "jhrffbxuvjbpbgyv"; // Mật khẩu email người gửi

		// Cấu hình địa chỉ máy chủ SMTP của email người gửi
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		// Xác thực người gửi
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		// Tạo message
		Message message = new MimeMessage(session);
		try {
			message.addHeader("Content-type", "text/HTML;charset=UTF-8");
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to,false));
			message.setSubject(subject);
			message.setContent(body, "text/html;charset=UTF-8");

			
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		
	}


}

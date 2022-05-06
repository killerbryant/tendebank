package tw.com.tendebank.utils;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 發Mail工具
 * @author Edison
 *
 */
public class MailUtils {
	private JavaMailSender mailSender;
	 
	public JavaMailSender getMailSender() {
		return mailSender;
	}
 
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
 
	public void sendMail(String from, String to, String subject, String msg) {
		MimeMessage message = mailSender.createMimeMessage();
 
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			// 設置發送mail的暱稱
			String nickname = MimeUtility.encodeText(ConstantUtils.COMPANY_TITLE);
			helper.setFrom(new InternetAddress(nickname + "<" + from + ">"));
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(msg, true);
 
		} catch (MessagingException e) {
			throw new MailParseException(e);
		} catch (UnsupportedEncodingException e) {
			throw new MailParseException(e);
		} 
 
		mailSender.send(message);
	}
}

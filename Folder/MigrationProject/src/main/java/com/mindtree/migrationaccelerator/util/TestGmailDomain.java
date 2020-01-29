package com.mindtree.migrationaccelerator.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author M1049890
 *
 */
public class TestGmailDomain {
	//chandankmr9876@gmail.com csarang2007@gmail.com sarang.chaudhari@mindtree.com chandan.kumar4@mindtree.com
		public static void main(String[] args) {
			System.out.println("Testing Gmail to Gmail Mailing..");
			sendPDFReportByGMail("chandan.kumar4@mindtree.com", "Binod$789", "Sarang.Chaudhari@mindtree.com", "Schedule Booking is sucessful", "You have successfully Booked Schedule on 1-April-2019..!");
		}

		private static void sendPDFReportByGMail(String from, String pass, String to, String subject, String body) {
			Properties props = System.getProperties();
			String host = "sendmtw.mindtree.com";//smtp.gmail.com 172.22.218.149 sendmtw.mindtree.com
			
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.password", pass);
			props.put("mail.smtp.port", "25");//587 25
			props.put("mail.smtp.auth", "true");
			Session session = Session.getDefaultInstance(props);
			MimeMessage message = new MimeMessage(session);
			try {
				// Set from address
				message.setFrom(new InternetAddress(from));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

				// Set subject
				message.setSubject(subject);
				message.setText(body);

				Transport transport = session.getTransport("smtp");
				transport.connect(host, from, pass);
				transport.sendMessage(message, message.getAllRecipients());
				System.out.println("Mail successfully sent.."); 
				transport.close();
			}
			catch (AddressException ae) {
				ae.printStackTrace();
			}
			catch (MessagingException me) {
				me.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

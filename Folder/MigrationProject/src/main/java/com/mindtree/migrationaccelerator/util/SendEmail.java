package com.mindtree.migrationaccelerator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.mindtree.migrationaccelerator.constants.MigrationAcceleratorConstants;

public class SendEmail {
	private static Properties prop;
	final static Logger logger = Logger.getLogger(SendEmail.class);

	public static Properties getPropertiesReaderInstance() throws IOException {
		if (prop == null) {
			logger.info("Reading email.properties file....");
			prop = new Properties();
			ClassLoader classLoader = new SendEmail().getClass().getClassLoader();
			URL url = classLoader.getResource("email.properties");
			File file = new File(url.getFile());
			prop.load(new FileInputStream(file));
		}
		return prop;
	}

	public static void sendEmailNotificationToUser(String userEmailID, Date migrationDate) {
		logger.info("Inside sendEmailNotificationToUser()");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
		String migDate = simpleDateFormat.format(migrationDate);
		try {
			Properties properties = System.getProperties();
			prop = getPropertiesReaderInstance();
			String host = (String) prop.get(MigrationAcceleratorConstants.HOST);
			String from = (String) prop.get(MigrationAcceleratorConstants.EMAILID);
			String to = userEmailID;
			properties.put("mail.smtp.host", host);
			Session session = Session.getDefaultInstance(properties);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Schedule Booking is sucessful");
			message.setText("Hello, You have successfully Booked Schedule on" + " " + migDate + " ..!");
			// Send message
			Transport.send(message);
			logger.info("Mail successfully sent to user " + userEmailID);
		} catch (AddressException ae) {
			logger.error("AddressException Occurred in sendEmailNotificationToUser() .." + ae);
		} catch (MessagingException me) {
			logger.error("MessagingException Occurred in sendEmailNotificationToUser() .." + me);
		} catch (Exception e) {
			logger.error("Exception Occurred in sendEmailNotificationToUser() .." + e);
		}
	}

}
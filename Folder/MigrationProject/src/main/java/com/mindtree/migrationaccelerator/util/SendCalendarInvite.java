package com.mindtree.migrationaccelerator.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;

import com.mindtree.migrationaccelerator.constants.MigrationAcceleratorConstants;
import com.mindtree.migrationaccelerator.dto.UserSlotSelectionDTO;

import net.fortuna.ical4j.model.TimeZone;

public class SendCalendarInvite {

	private static Properties prop;
	final static Logger logger = Logger.getLogger(SendCalendarInvite.class);

	public static Properties getPropertiesReaderInstance() throws IOException {
		if (prop == null) {
			logger.info("Reading email.properties file....");
			prop = new Properties();
			ClassLoader classLoader = new SendCalendarInvite().getClass().getClassLoader();
			URL url = classLoader.getResource("email.properties");
			File file = new File(url.getFile());
			prop.load(new FileInputStream(file));
		}
		return prop;
	}
	
public SendCalendarInvite(){
	
} 
    private static String convertStartEndTime(UserSlotSelectionDTO userSlotSelectionDTO){
    	Date migrationDate=userSlotSelectionDTO.getMigrationDate();
 	   Date sTime=userSlotSelectionDTO.getSlotDTO().getStartTime();
 	   Date eTime=userSlotSelectionDTO.getSlotDTO().getEndTime();
 	   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
 		String migDate = simpleDateFormat.format(migrationDate);
 		 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
 		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
 		 String startTime = sdf.format(sTime);
 		 String endTime = sdf.format(eTime);
 		System.out.println(migDate.replaceAll("[\\s\\-()]", ""));
 		 System.out.println(startTime.replaceAll("[\\s\\:()]", ""));
 		System.out.println(endTime.replaceAll("[\\s\\:()]", ""));
 		String startDateTime = migDate.replaceAll("[\\s\\-()]", "")+"T"+startTime.replaceAll("[\\s\\:()]", "")+"Z";
 		String endDateTime = migDate.replaceAll("[\\s\\-()]", "")+"T"+endTime.replaceAll("[\\s\\:()]", "")+"Z";
    	return startDateTime+"|"+endDateTime;
    }

public static void sendCalendarNotificationToUser(String userEmailID, UserSlotSelectionDTO userSlotSelectionDTO) throws Exception {
	logger.info("Inside sendCalenderNotificationToUser()");
	Date migrationDate=userSlotSelectionDTO.getMigrationDate();
	 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
		String migDate = simpleDateFormat.format(migrationDate);
	String startEndDateTime [] = convertStartEndTime(userSlotSelectionDTO).split("\\|");
	   
	       try {
	           Properties properties = System.getProperties();
				prop = getPropertiesReaderInstance();

				String host = (String) prop.get(MigrationAcceleratorConstants.HOST);
				System.out.println(host);
				String from = (String) prop.get(MigrationAcceleratorConstants.EMAILID);
				String to = userEmailID;
				System.out.println(to);
				properties.put("mail.smtp.host", host);
				//properties.setProperty("mail.smtp.host", host);
	           Session session = Session.getDefaultInstance(properties);
	           MimeMessage message = new MimeMessage(session);
	           message.addHeaderLine("method=REQUEST");
	           message.addHeaderLine("charset=UTF-8");
	           message.addHeaderLine("component=VEVENT");

	           message.setFrom(new InternetAddress(from));
	           message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	           message.setSubject("Schedule Booking is sucessful");
	           message.setText("Hello, You have successfully Booked Schedule on" + " " + migDate + " ..!");
	           StringBuffer sb = new StringBuffer();
	           StringBuffer buffer = sb.append("BEGIN:VCALENDAR\n" +
	                   "PRODID:-//Microsoft Corporation//Outlook 9.0 MIMEDIR//EN\n" +
	                   "VERSION:2.0\n" +
	                   "METHOD:REQUEST\n" +
	                   "BEGIN:VEVENT\n" +
	                   "ATTENDEE;ROLE=REQ-PARTICIPANT;PARTSTAT=NEEDS-ACTION;RSVP=TRUE:MAILTO:"+to+"\n" +
	                   "ORGANIZER:MAILTO:"+from+"\n" +
	                   "DTSTART:"+startEndDateTime[0]+"\n" +
	                   "DTEND:"+startEndDateTime[1]+"\n" +
	                   "LOCATION:India,Bangalore\n" +
	                   "TRANSP:OPAQUE\n" +
	                   "SEQUENCE:0\n" +
	                   "UID:324\n" +
	                   "DTSTAMP:"+startEndDateTime[1]+"\n" +
	                   "CATEGORIES:Meeting\n" +
	                   "DESCRIPTION:Hello, You have successfully Booked Schedule on "+migDate+"\n\n" +
	                   "SUMMARY:Schedule meeting request\n" +
	                   "PRIORITY:5\n" +
	                   "CLASS:PUBLIC\n" +
	                   "STATUS:CONFIRMED\n" +
	                   "TRANSP:OPAQUE\n" +
	                   "BEGIN:VALARM\n" +
	                   "TRIGGER:PT1440M\n" +
	                   "ACTION:DISPLAY\n" +
	                   "DESCRIPTION:Reminder\n" +
	                   "END:VALARM\n" +
	                   "END:VEVENT\n" +
	                   "END:VCALENDAR");
	           
	           BodyPart messageBodyPart = new MimeBodyPart();
	           messageBodyPart.setHeader("Content-Class", "urn:content-  classes:calendarmessage");
	           messageBodyPart.setHeader("Content-ID", "calendar_message");
	           messageBodyPart.setDataHandler(new DataHandler(
	                   new ByteArrayDataSource(buffer.toString(), "text/calendar")));
	           Multipart multipart = new MimeMultipart();
	           multipart.addBodyPart(messageBodyPart);
	           message.setContent(multipart);
               Transport.send(message);

	           System.out.println("Mail successfully sent to user");

	       } catch (MessagingException me) {
	           me.printStackTrace();
	       } catch (Exception ex) {
	           ex.printStackTrace();
	       }
	    } 
                
}


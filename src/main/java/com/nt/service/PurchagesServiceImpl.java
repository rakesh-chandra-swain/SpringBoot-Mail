package com.nt.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
@Service
public class PurchagesServiceImpl implements IPurchageMgmtService{

	@Autowired
		private JavaMailSender sender;
	@Value("${spring.mail.username}")
		private String fromEmailId;
	
	@Override
	public String shopping(String[] items, double[] price, String[] emails) throws Exception{
		//calculate bill amount
		double totalAmt=0.0;
		for(double p:price) {
			totalAmt=totalAmt+p;
		}
		String msg1=Arrays.toString(items)+" are purchaged having price"+Arrays.toString(price)+"with bill amount"+totalAmt;
		//  String fromEmailId = "chandrarakesh2001@gmail.com"; 
		//trigger the email message
		 try {
	            // Trigger the email message
	            String response = sendMail(msg1, emails, fromEmailId);
	            return response;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Failed to send email: " + e.getMessage();
	        }
	}
	private String sendMail(String msg,String[] toemailid,String fromEmailId) throws Exception {
		//create and send multipartMIME message
		MimeMessage message=sender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message,true);
		//seating the header value
		helper.setSentDate(new Date());
		helper.setFrom(fromEmailId);
		helper.setTo(toemailid);
		helper.setSubject("open it to know it");
		//set multipart body
		helper.setText(msg);
	//	helper.addAttachment("photo1.jpeg",new ClassPathResource("photo1.jpeg"));
		//send the message
		sender.send(message);
		return "Email message sent successfully!";
	}
}

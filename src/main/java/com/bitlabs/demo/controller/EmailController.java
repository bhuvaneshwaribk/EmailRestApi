package com.bitlabs.demo.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitlabs.demo.config.EmailSenderService;
import com.bitlabs.demo.modal.EmailOtp;
import com.bitlabs.demo.service.EmailService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/emailService")
public class EmailController {

	@Autowired
	EmailService emailservice;
	@Autowired
	private EmailSenderService emailsenderservice;
	
	@RequestMapping("/sendOtp/{email}")
	public String sendOtp(@PathVariable String email) throws MessagingException {
		
		String otp=generateOtp();
		String to=email;
		String subject="otp transcation";
		String body="here your otp "+otp;
		
		emailsenderservice.sendEmail(to,subject,body);
		
		EmailOtp obj=new EmailOtp();
		obj.setEmail(email);
		obj.setOtp(otp);
		String result=emailservice.addEmailOtp(obj);
		return "otp sent successfully";
		
	}
	
	
	@RequestMapping("/verfiyOtp")
	public String verifyOtp(@RequestBody EmailOtp emailotp) {
		String email=emailotp.getEmail();
		String otp=emailotp.getOtp();
		List<EmailOtp> obj=emailservice.getEmailOtpByEmail(email);
		
		for(EmailOtp obj1:obj) {
		if(obj1.getOtp().equals(otp)) {
			return "otp verfied successfully";
		}
		}
		return "otp did not match";	
	
	}
	
	
	
	public static String generateOtp() {
		String num="0123456789";
		Random r=new Random();
		char[] ch=new char[6];
		for(int i=0;i<ch.length;i++) {
			ch[i]=num.charAt(r.nextInt(num.length()));
		}
		String otp=new String(ch);
		return otp;
	}
}

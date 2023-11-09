package com.bitlabs.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitlabs.demo.modal.EmailOtp;
import com.bitlabs.demo.repository.EmailOtpRepository;

@Service
public class EmailService {

	@Autowired
	private EmailOtpRepository emailOtpRepo;
	
	public String addEmailOtp(EmailOtp emailOtp) {
		EmailOtp emailotp=emailOtpRepo.save(emailOtp);
		
		if(emailotp!=null)
		return "addedd successfully";
		else
		return "some thing went wrong";
			
		
	}
	
	public List<EmailOtp> getEmailOtpByEmail(String email) {
		//custom finder method
		List<EmailOtp> obj=emailOtpRepo.findByEmail(email);
		return obj;
		
	}
}

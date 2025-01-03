package com.springboot.main.services;

import com.springboot.main.entities.UserCredential;
import com.springboot.main.entities.UserInfo;
import com.springboot.main.repositories.UserCredentialRepository;
import com.springboot.main.repositories.UserInfoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialServiceImpl implements UserCredentialService {
	@Autowired
	private UserCredentialRepository userCredentialRepository;
	@Autowired
	private UserInfoRepository userInfoRepository;

	public String validateUserLogin(String email, String password) {
		Optional<UserCredential> userCredentialOpt = this.userCredentialRepository.findByEmail(email);
		if (userCredentialOpt.isPresent()) {
			UserCredential userCredential = (UserCredential) userCredentialOpt.get();
			return userCredential.getPassword().equals(password) ? "VALID" : "INVALID_PASSWORD";
		} else {
			return "USER_NOT_FOUND";
		}
	}

	public void registerNewUser(UserCredential userCredential, UserInfo userInfo) {
		this.userCredentialRepository.save(userCredential);
		this.userInfoRepository.save(userInfo);
	}

	public UserInfo getUserInfo(String email) {
		return this.userInfoRepository.findByUserCredential_Email(email);
	}

	public List<UserInfo> getAllUsersInfo() {
		return this.userInfoRepository.findAll();
	}
}

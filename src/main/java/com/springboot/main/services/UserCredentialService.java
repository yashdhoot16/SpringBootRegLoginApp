package com.springboot.main.services;

import com.springboot.main.entities.UserCredential;
import com.springboot.main.entities.UserInfo;
import java.util.List;

public interface UserCredentialService {
	String validateUserLogin(String email, String password);

	void registerNewUser(UserCredential userCredential, UserInfo userInfo);

	UserInfo getUserInfo(String email);

	List<UserInfo> getAllUsersInfo();

	boolean isEmailRegistered(String email);
}

package com.springboot.main.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.main.entities.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
	List<UserInfo> findAllByUserType(String userType);

	UserInfo findByUserCredential_Email(String email);
}

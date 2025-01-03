package com.springboot.main.controller;

import com.springboot.main.entities.UserCredential;
import com.springboot.main.entities.UserInfo;
import com.springboot.main.services.UserCredentialService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MyController {
	@Autowired
	private UserCredentialService userCredentialService;

	@GetMapping({ "/show-users" })
	public String showAllUsers(Model model, @RequestParam String userType) {
		if ("A".equals(userType)) {
			List<UserInfo> allUsers = this.userCredentialService.getAllUsersInfo();
			model.addAttribute("users", allUsers);
			return "showUsers";
		} else {
			return "redirect:/homePage";
		}
	}

	@GetMapping({ "/show-user" })
	public String showCurrentUserInfo(@RequestParam("email") String email, Model model) {
		UserInfo userInfo = this.userCredentialService.getUserInfo(email);
		if (userInfo != null) {
			model.addAttribute("userInfo", userInfo);
		} else {
			model.addAttribute("errorMessage", "User not found.");
		}

		return "showUserInfo";
	}

	@GetMapping({ "/download-users" })
	public void downloadUsersInfo(HttpServletResponse response) throws IOException {
		List<UserInfo> allUsers = this.userCredentialService.getAllUsersInfo();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("User Info");
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("First Name");
		headerRow.createCell(1).setCellValue("Last Name");
		headerRow.createCell(2).setCellValue("Email");
		headerRow.createCell(3).setCellValue("Mobile Number");
		int rowNum = 1;
		Iterator var8 = allUsers.iterator();

		while (var8.hasNext()) {
			UserInfo userInfo = (UserInfo) var8.next();
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(userInfo.getFirstName());
			row.createCell(1).setCellValue(userInfo.getLastName());
			row.createCell(2).setCellValue(userInfo.getUserCredential().getEmail());
			row.createCell(3).setCellValue(userInfo.getMobileNumber());
		}

		response.setHeader("Content-Disposition", "attachment; filename=users_info.xlsx");
		workbook.write(response.getOutputStream());
		workbook.close();
	}

	@GetMapping({ "/" })
	public String loginPage() {
		return "login";
	}

	@PostMapping({ "/login" })
	public String login(@RequestParam String email, @RequestParam String password,
			RedirectAttributes redirectAttributes, Model model) {
		String validationResult = this.userCredentialService.validateUserLogin(email, password);
		switch (validationResult.hashCode()) {
		case -75433118:
			if (!validationResult.equals("USER_NOT_FOUND")) {
			}
			break;
		case 81434588:
			if (validationResult.equals("VALID")) {
				UserInfo userInfo = this.userCredentialService.getUserInfo(email);
				redirectAttributes.addFlashAttribute("successMessage",
						"Successful login as userrole: " + userInfo.getUserType() + "\n (U-User | A-Admin)");
				redirectAttributes.addFlashAttribute("userInfo", userInfo);
				redirectAttributes.addFlashAttribute("ModelFirstName", userInfo.getFirstName());
				redirectAttributes.addFlashAttribute("ModelLastName", userInfo.getLastName());
				redirectAttributes.addFlashAttribute("isAdmin", userInfo.getUserType().equals("A"));
				return "redirect:/homePage";
			}
			break;
		case 1094975491:
			if (validationResult.equals("INVALID_PASSWORD")) {
				redirectAttributes.addFlashAttribute("errorMsg", "Incorrect email or password!");
				return "redirect:/";
			}
		}

		redirectAttributes.addFlashAttribute("errorMessage", "User does not exists! ");
		return "redirect:/";
	}

	@GetMapping({ "/registerPage" })
	public String registerPage() {
		return "register";
	}

	@PostMapping({ "/register" })
	public String register(@ModelAttribute UserCredential userCredential, @ModelAttribute UserInfo userInfo,
			Model model, RedirectAttributes redirectAttributes) {
		userInfo.setUserCredential(userCredential);
		if ("A".equals(userInfo.getUserType())) {
			if (!"admin@domain.com".equals(userInfo.getUserCredential().getEmail())) {
				redirectAttributes.addFlashAttribute("UnauthorizeMsg", "Unauthorized to register as Admin!");
				return "redirect:/registerPage";
			} else {
				this.userCredentialService.registerNewUser(userCredential, userInfo);
				redirectAttributes.addFlashAttribute("successMessage", "Admin registration successful!");
				return "redirect:/";
			}
		} else {
			this.userCredentialService.registerNewUser(userCredential, userInfo);
			redirectAttributes.addFlashAttribute("successMessage", "Registration successful!");
			return "redirect:/";
		}
	}

	@GetMapping({ "/homePage" })
	public String openHomePage(Model model) {
		if (!model.containsAttribute("isAdmin")) {
			model.addAttribute("isAdmin", false);
		}

		return "home";
	}

	@GetMapping({ "/logout" })
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		return "redirect:/";
	}
}

package shop.newshop.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shop.newshop.Entity.Account;
import shop.newshop.Service.AccountService;
import shop.newshop.Service.EmployeeService;

@Controller
public class HomeController {

	@Autowired
	private JavaMailSender MailSender;

	@Autowired
	private AccountService accountService;

	@Autowired
	private EmployeeService employeeService;

	private static final String PATH = Paths.get("").toAbsolutePath().toString()
			+ "\\src\\main\\resources\\static\\images\\";

	boolean status = false;

	@GetMapping(value = "/")
	public String HomeChomer(HttpSession session, ModelMap model) {
		if (session.getAttribute("account") != null) {
			Account account = (Account) session.getAttribute("account");
			if (account.getRole() == 1) {
				return "redirect:/admin/listDepartment";
			} else {
				return "redirect:/employee/inforEmployee";
			}
		}

		if (status == false) {
			model.put("success", "");
		} else {
			model.put("success", "Mật khẩu mới đã được gửi đến Email");
		}

		return "employee/index";
	}

	@PostMapping(value = "/login")
	public String login(@RequestParam("username") String user, @RequestParam("password") String pass,
			HttpSession session, ModelMap modelMap) {
		Account account = accountService.login(user, pass);
		if (account != null) {
			session.setAttribute("account", account);

			if (account.getEmployee().getAvatar() != null && !account.getEmployee().getAvatar().isEmpty()) {
				File fileAvatar = new File(PATH + account.getEmployee().getAvatar());
				session.setAttribute("avatar", "data:image/jpeg;base64," + encodeFileToBase64Binary(fileAvatar));
			}

			if (account.getRole() == 1) {
				return "redirect:/admin/listDepartment";
			} else {
				return "redirect:/employee/inforEmployee";
			}
		}
		modelMap.put("errorLogin", "Sai tài khoản hoặc mật khẩu");
		return "employee/index";

	}

	@GetMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("account");
		if (session.getAttribute("avatar") != null) {
			session.removeAttribute("avatar");
		}
		status = false;
		return "redirect:/";
	}

	@PostMapping(value = "/updatePass")
	public String forgotPass(ModelMap model, @RequestParam("email") String email) throws MessagingException {
		boolean checkEmail = employeeService.checkEmail(email);
		if (checkEmail == true) {

			String newPass = randomAlphaNumeric(10);

			MimeMessage message = MailSender.createMimeMessage();
			boolean mutipart = true;
			MimeMessageHelper helper = new MimeMessageHelper(message, mutipart, "utf-8");

			String htmlMsg = "<h2>Mật khẩu mới của bạn</h2> <br/> <table style='width:100% ; border-collapse: collapse;border: 1px solid black;'>"
					+ " <tr style=' border-collapse: collapse;border: 1px solid black;'>"
					+ "<th style=' border-collapse: collapse;border: 1px solid black;'>Mật khẩu mới</th></tr>"
					+ "<tr style=' border-collapse: collapse;border: 1px solid black;'>"
					+ "<th style=' border-collapse: collapse;border: 1px solid black;'>" + newPass + "</th>" + "</tr>"
					+ "</table>";

			message.setContent(htmlMsg, "text/html; charset=UTF-8");

			helper.setTo(email);
			helper.setSubject("Thông tin mật khẩu mới");

			Account account = accountService.getAccountByEmail(email);

			account.setPassword(encryptThisString(newPass));

			accountService.update(account);

			MailSender.send(message);
			status = true;
			return "redirect:/";
		}
		model.put("error", "Không tìm thấy Email ! Vui lòng kiểm tra lại");
		return "employee/index";
	}

	@GetMapping(value = "/backLogin")
	public String backLogin() {
		status = false;
		return "redirect:/";
	}

	// ConvertToBase64
	private static String encodeFileToBase64Binary(File file) {
		String encodedfile = null;
		try {
			FileInputStream fileInputStreamReader = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			fileInputStreamReader.read(bytes);
			encodedfile = Base64.getEncoder().encodeToString(bytes);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return encodedfile;
	}

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	public static String encryptThisString(String input) {
		try {
			// getInstance() method is called with algorithm SHA-512
			MessageDigest md = MessageDigest.getInstance("SHA-512");

			// digest() method is called
			// to calculate message digest of the input string
			// returned as array of byte
			byte[] messageDigest = md.digest(input.getBytes());

			// Convert byte array into signum representation
			BigInteger no = new BigInteger(1, messageDigest);

			// Convert message digest into hex value
			String hashtext = no.toString(16);

			// Add preceding 0s to make it 32 bit
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			// return the HashText
			return hashtext;
		}

		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}

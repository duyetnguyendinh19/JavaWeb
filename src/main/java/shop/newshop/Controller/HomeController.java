package shop.newshop.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
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

	@GetMapping(value = "/")
	public String HomeChomer(HttpSession session) {
		if (session.getAttribute("account") != null) {
			Account account = (Account) session.getAttribute("account");
			if (account.getRole() == 1) {
				return "redirect:/admin/listDepartment";
			} else {
				return "redirect:/employee/inforEmployee";
			}
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
		return "redirect:/";
	}

	@PostMapping(value = "/updatePass")
	public String forgotPass(ModelMap model, @RequestParam("email") String email) throws MessagingException {
		boolean checkEmail = employeeService.checkEmail(email);
		if (checkEmail == true) {
			model.put("success", "Mật khẩu mới đã được gửi đến Email");
			MimeMessage message = MailSender.createMimeMessage();
			boolean mutipart = true;
			MimeMessageHelper helper = new MimeMessageHelper(message, mutipart, "utf-8");

			String htmlMsg = "<h2>Tài khoản đã được tạo</h2> <br/> <table style='width:100% ; border-collapse: collapse;border: 1px solid black;'>"
					+ " <tr style=' border-collapse: collapse;border: 1px solid black;'>"
					+ "<th style=' border-collapse: collapse;border: 1px solid black;'>Mật khẩu mới</th></tr>"
					+ "<tr style=' border-collapse: collapse;border: 1px solid black;'>"
					+ "<th style=' border-collapse: collapse;border: 1px solid black;'>" + randomAlphaNumeric(10)
					+ "</th>" + "</tr>" + "</table>";

			message.setContent(htmlMsg, "text/html; charset=UTF-8");

			helper.setTo(email);
			helper.setSubject("Thông tin mật khẩu mới");

			MailSender.send(message);
			return "redirect:/";
		}
		model.put("error", "Không tìm thấy Email ! Vui lòng kiểm tra lại");
		return "employee/index";
	}

	@GetMapping(value = "/backLogin")
	public String backLogin() {
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

}

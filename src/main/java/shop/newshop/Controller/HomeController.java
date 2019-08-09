package shop.newshop.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shop.newshop.Entity.Account;
import shop.newshop.Service.AccountService;

@Controller
public class HomeController {

	@Autowired
	private AccountService accountService;

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
		modelMap.put("error", "Sai tài khoản hoặc mật khẩu");
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

}

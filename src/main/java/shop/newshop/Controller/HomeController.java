package shop.newshop.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import shop.newshop.DAO.AccountDao;
import shop.newshop.Entity.Account;
import shop.newshop.Service.AccountService;

@Controller
public class HomeController {

	@Autowired
	private AccountService accountService;

	@GetMapping(value = "/")
	public String HomeChomer(HttpSession session) {
		if(session.getAttribute("account") != null) {
			Account account = (Account) session.getAttribute("account");
			if (account.getRole() == 1) {
				return "redirect:/admin/listDepartment";
			}else {
				return "redirect:/employee/inforEmployee";
			}
		}
		return "employee/index";
	}

	@PostMapping(value = "/login")
	public String login(@RequestParam("username") String user, @RequestParam("password") String pass,
			HttpSession session,ModelMap modelMap) {
		Account account = accountService.login(user, pass);
		if (account != null) {
			session.setAttribute("account", account);
			if (account.getRole() == 1) {
				return "redirect:/admin/listDepartment";
			}else {
				return "redirect:/employee/inforEmployee";
			}
		}
		modelMap.put("error", "Sai tài khoản hoặc mật khẩu");
		return "employee/index";

	}
	
	@GetMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("account");
		return "redirect:/";
	}
	

//	@GetMapping(value="index")
//	public String HomeCustomer(ModelMap map) {
//		map.addAttribute("title", "Home");
//		return "employee/index";
//	}

}

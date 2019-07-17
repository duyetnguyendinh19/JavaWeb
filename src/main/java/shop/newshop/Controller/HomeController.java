package shop.newshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


	@GetMapping(value="index")
	public String HomeCustomer(ModelMap map) {
		map.addAttribute("title", "Home");
		return "customer/index";
	}
	
	@GetMapping(value="admin")
	public String HomeAdmin(ModelMap map) {
		map.addAttribute("title", "Home");
		return "admin/index";
	}
}

package shop.newshop.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class HomeController {

	@RequestMapping(value= {"/","trang-chu"})
	public String HomeChomer() {
	System.out.println("HomeChomer.index()");
		return "customer/index";
	}
	

	@GetMapping(value="admin")
	public String HomeAdmin(ModelMap map) {
		map.addAttribute("title", "Home");
		return "admin/index";
	}

}

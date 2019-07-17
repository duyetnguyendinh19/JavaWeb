package shop.newshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import shop.newshop.Service.BonusService;

@Controller
public class BonusController {

	@Autowired
	private BonusService bonusService;
	
	@GetMapping(value = "listBonus")
	public String getBonus(ModelMap map) {
		map.put("lstBonus", bonusService.getAlls());
		return "";
	}
	
}

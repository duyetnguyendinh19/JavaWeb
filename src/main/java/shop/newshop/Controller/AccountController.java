package shop.newshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import shop.newshop.Service.AccountService;

@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;

	private String name;

	@PostMapping(value = "admin/listAccount")
	public String listAccountSearch(ModelMap model, @RequestParam("nameAccount") String nameSearch) {
		name = nameSearch;
		model.put("error", "");
		model.put("listAccount", accountService.getLimit(0, 5, nameSearch));
		long countAll = accountService.countAll(nameSearch);
		long totalPage = 0;

		if (countAll == 0) {
			totalPage = 1;
		} else {
			if (countAll % 5 == 0) {
				totalPage = countAll / 5;
			} else {
				totalPage = countAll / 5 + 1;
			}
		}

		model.put("totalPage", totalPage);
		model.put("totalAccount", countAll);
		model.put("firstAccount", 1);
		if (countAll < 5) {
			model.put("lastAccount", countAll);
		} else {
			model.put("lastAccount", 5);
		}
		model.put("nameSearch", nameSearch);

		return "admin/user";
	}

	@GetMapping(value = "admin/listAccount")
	public String listAccount(ModelMap model) {
		model.put("error", "");
		model.put("listAccount", accountService.getLimit(0, 5, null));
		long countAll = accountService.countAll(null);
		long totalPage = 0;

		if (countAll == 0) {
			totalPage = 1;
		} else {
			if (countAll % 5 == 0) {
				totalPage = countAll / 5;
			} else {
				totalPage = countAll / 5 + 1;
			}
		}

		model.put("totalPage", totalPage);
		model.put("totalAccount", countAll);
		model.put("firstAccount", 1);
		if (countAll < 5) {
			model.put("lastAccount", countAll);
		} else {
			model.put("lastAccount", 5);
		}
		model.put("nameSearch", null);

		return "admin/user";
	}

	@GetMapping(value = "admin/listAccount/{page}")
	public String listAccountPage(ModelMap model, @PathVariable("page") int page) {
		model.put("error", "");
		model.put("listAccount", accountService.getLimit((page - 1) * 5, 5, name));

		long countAll = accountService.countAll(name);
		long totalPage = 0;

		if (countAll == 0) {
			totalPage = 1;
		} else {
			if (countAll % 5 == 0) {
				totalPage = countAll / 5;
			} else {
				totalPage = countAll / 5 + 1;
			}
		}

		model.put("totalPage", totalPage);
		model.put("totalAccount", countAll);
		model.put("firstAccount", (page - 1) * 5 + 1);
		if (page < totalPage) {
			model.put("lastAccount", (page - 1) * 5 + 5);
		} else {
			model.put("lastAccount", countAll);
		}

		model.put("nameSearch", name);
		return "admin/user";
	}
}

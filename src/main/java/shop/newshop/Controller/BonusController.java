package shop.newshop.Controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.newshop.Entity.*;
import shop.newshop.Service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class BonusController {

	@Autowired
	private BonusService bonusService;

	@Autowired
	private EmployeeService employeeService;

	private String name;

	@PostMapping(value = "admin/listBonus")
	public String bonusListSearch(ModelMap model, @RequestParam("nameEmployee") String nameSearch) {
		name = nameSearch;
		model.put("error", "");
		model.put("bonusList", bonusService.getLimit(0, 5, nameSearch));
		long countAll = bonusService.countAll(nameSearch);
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
		model.put("totalBonus", countAll);
		model.put("firstBonus", 1);
		if (countAll < 5) {
			model.put("lastBonus", countAll);
		} else {
			model.put("lastBonus", 5);
		}
		model.put("nameSearch", nameSearch);

		return "admin/Bonus";
	}

	@GetMapping(value = "admin/listBonus")
	public String bonusList(ModelMap model) {
		model.put("error", "");
		model.put("bonusList", bonusService.getLimit(0, 5, null));
		long countAll = bonusService.countAll(null);
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
		model.put("totalBonus", countAll);
		model.put("firstBonus", 1);
		if (countAll < 5) {
			model.put("lastBonus", countAll);
		} else {
			model.put("lastBonus", 5);
		}
		model.put("nameSearch", null);

		return "admin/Bonus";
	}

	@GetMapping(value = "admin/listBonus/{page}")
	public String bonusListPage(ModelMap model, @PathVariable("page") int page) {
		model.put("error", "");
		model.put("bonusList", bonusService.getLimit((page - 1) * 5, 5, name));

		long countAll = bonusService.countAll(name);
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
		model.put("totalBonus", countAll);
		model.put("firstBonus", (page - 1) * 5 + 1);
		if (page < totalPage) {
			model.put("lastBonus", (page - 1) * 5 + 5);
		} else {
			model.put("lastBonus", countAll);
		}

		model.put("nameSearch", name);
		return "admin/Bonus";
	}

	@GetMapping(value = "admin/addBonus")
	public String addContract(ModelMap model) {
		List<Employee> employees = employeeService.getAlls();
		model.addAttribute("employee", employees);
		return "admin/AddBonus";
	}

	@PostMapping(value = "admin/addBonus")
	public String insertContract(RedirectAttributes redirectAttributes, ModelMap model,
			@RequestParam("typeBonus") String typeBonus, @RequestParam("date") String date,
			@RequestParam("descent") String descent, @RequestParam("reason") String reason,
			@RequestParam("employeeId") int idEmployee) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Employee employee = new Employee();
			Bonus bonus = new Bonus();
			if (Strings.isEmpty(typeBonus)) {
				List<Employee> employees = employeeService.getAlls();
				model.addAttribute("idEmployee", idEmployee);
				model.addAttribute("employee", employees);
				model.addAttribute("errorType", "Vui lòng chọn kiểu khen thưởng");
				return "admin/AddBonus";
			}
			if (Strings.isEmpty(date)) {
				List<Employee> employees = employeeService.getAlls();
				model.addAttribute("idEmployee", idEmployee);
				model.addAttribute("typeBonus", typeBonus);
				model.addAttribute("employee", employees);
				model.addAttribute("errorDate", "Ngày khen thưởng không được để trống");
				return "admin/AddBonus";
			}
			if (Strings.isEmpty(reason)) {
				List<Employee> employees = employeeService.getAlls();
				model.addAttribute("idEmployee", idEmployee);
				model.addAttribute("typeBonus", typeBonus);
				model.addAttribute("employee", employees);
				model.addAttribute("errorReason", "Lý do khen thưởng không được để trống");
				return "admin/AddBonus";
			}
			bonus.setDate(sdf.parse(date));
			bonus.setDescent(descent);
			bonus.setReason(reason);
			employee.setId(idEmployee);
			bonus.setEmployee(employee);
			bonus.setType(typeBonus);
			bonusService.insert(bonus);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/listBonus";
	}

	@GetMapping(value = "admin/updateBonus/{id}")
	public String updateBonus(ModelMap model, @PathVariable("id") int id) {
		Bonus bonus = bonusService.getBonusById(id);
		model.addAttribute("bonus", bonus);
		return "admin/EditBonus";
	}

	@PostMapping(value = "admin/updateBonus")
	public String postUpdateContract(RedirectAttributes redirectAttributes, ModelMap model,
			@RequestParam("typeBonus") String typeBonus, @RequestParam("date") String date,
			@RequestParam("descent") String descent, @RequestParam("reason") String reason,
			@RequestParam("employee") int idEmployee, @RequestParam("id") int id) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Employee employee = new Employee();
			Bonus bonus = new Bonus();
			if (Strings.isEmpty(typeBonus)) {
				bonus = bonusService.getBonusById(id);
				model.addAttribute("bonus", bonus);
				model.addAttribute("idEmployee", idEmployee);
				model.addAttribute("errorType", "Vui lòng chọn kiểu khen thưởng");
				return "admin/EditBonus";
			}
			if (Strings.isEmpty(date)) {
				bonus = bonusService.getBonusById(id);
				bonus.setDate(null);
				model.addAttribute("bonus", bonus);
				model.addAttribute("idEmployee", idEmployee);
				model.addAttribute("typeBonus", typeBonus);
				model.addAttribute("errorDate", "Ngày khen thưởng không được để trống");
				return "admin/EditBonus";
			}
			if (Strings.isEmpty(reason)) {
				bonus = bonusService.getBonusById(id);
				bonus.setReason(null);
				model.addAttribute("bonus", bonus);
				model.addAttribute("idEmployee", idEmployee);
				model.addAttribute("typeBonus", typeBonus);
				model.addAttribute("errorReason", "Lý do khen thưởng không được để trống");
				return "admin/EditBonus";
			}
			bonus.setId(id);
			bonus.setDate(sdf.parse(date));
			bonus.setDescent(descent);
			bonus.setReason(reason);
			employee.setId(idEmployee);
			bonus.setEmployee(employee);
			bonus.setType(typeBonus);
			bonusService.update(bonus);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return "redirect:/admin/listBonus";
	}

	@GetMapping(value = "admin/deleteBonus/{id}")
	public String deleteDiscipline(ModelMap model, @PathVariable("id") int id) {
		bonusService.delete(id);
		return "redirect:/admin/listBonus";
	}
}

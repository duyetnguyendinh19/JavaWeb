package shop.newshop.Controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.newshop.Entity.*;
import shop.newshop.Service.*;
import sun.java2d.pipe.SpanShapeRenderer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class DisciplineController {

	@Autowired
	private DisciplineService disciplineService;

	@Autowired
	private EmployeeService employeeService;

	private String name;

	@PostMapping(value = "admin/listDiscipline")
	public String disciplineListSearch(ModelMap model, @RequestParam("nameEmployee") String nameSearch) {
		name = nameSearch;
		model.put("error", "");
		model.put("disciplineList", disciplineService.getLimit(0, 5, nameSearch));
		long countAll = disciplineService.countAll(nameSearch);
		long totalPage = 0;

		if (countAll == 0) {
			model.addAttribute("searchFail","Không tìm thấy dữ liệu");
			totalPage = 1;
		} else {
			if (countAll % 5 == 0) {
				totalPage = countAll / 5;
			} else {
				totalPage = countAll / 5 + 1;
			}
		}

		model.put("totalPage", totalPage);
		model.put("totalDis", countAll);
		model.put("firstDis", 1);
		if (countAll < 5) {
			model.put("lastDis", countAll);
		} else {
			model.put("lastDis", 5);
		}
		model.put("nameSearch", nameSearch);

		return "admin/Discipline";
	}

	@GetMapping(value = "admin/listDiscipline")
	public String disciplineList(ModelMap model) {
		model.put("error", "");
		model.put("disciplineList", disciplineService.getLimit(0, 5, null));
		long countAll = disciplineService.countAll(null);
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
		model.put("totalDis", countAll);
		model.put("firstDis", 1);
		if (countAll < 5) {
			model.put("lastDis", countAll);
		} else {
			model.put("lastDis", 5);
		}
		model.put("nameSearch", null);

		return "admin/Discipline";
	}

	@GetMapping(value = "admin/listDiscipline/{page}")
	public String disciplineListPage(ModelMap model, @PathVariable("page") int page) {
		model.put("error", "");
		model.put("disciplineList", disciplineService.getLimit((page - 1) * 5, 5, name));

		long countAll = disciplineService.countAll(name);
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
		model.put("totalDis", countAll);
		model.put("firstDis", (page - 1) * 5 + 1);
		if (page < totalPage) {
			model.put("lastDis", (page - 1) * 5 + 5);
		} else {
			model.put("lastDis", countAll);
		}

		model.put("nameSearch", name);
		return "admin/Discipline";
	}

	@GetMapping(value = "admin/addDiscipline")
	public String addContract(ModelMap model) {
		List<Employee> employees = employeeService.getAlls();
		model.addAttribute("employee", employees);
		return "admin/AddDiscipline";
	}

	@PostMapping(value = "admin/addDiscipline")
	public String insertContract(RedirectAttributes redirectAttributes, ModelMap model,
			@RequestParam("typeDiscipline") String typeDiscipline, @RequestParam("date") String date,
			@RequestParam("descent") String descent, @RequestParam("reason") String reason,
			@RequestParam("employeeId") int idEmployee) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Employee employee = new Employee();
			Discipline discipline = new Discipline();
			if (Strings.isEmpty(date)) {
				List<Employee> employees = employeeService.getAlls();
				model.addAttribute("idEmployee", idEmployee);
				model.addAttribute("employee", employees);
				model.addAttribute("errorDate", "Ngày kỷ luật không được để trống");
				return "admin/AddDiscipline";
			}
			if (Strings.isEmpty(reason)) {
				List<Employee> employees = employeeService.getAlls();
				model.addAttribute("idEmployee", idEmployee);
				model.addAttribute("employee", employees);
				model.addAttribute("errorReason", "Lý do kỷ luật không được để trống");
				return "admin/AddDiscipline";
			}
			discipline.setDate(sdf.parse(date));
			discipline.setDescent(descent);
			discipline.setReason(reason);
			employee.setId(idEmployee);
			discipline.setEmployee(employee);
			discipline.setType(typeDiscipline);
			disciplineService.insert(discipline);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/listDiscipline";
	}

	@GetMapping(value = "admin/updateDiscipline/{id}")
	public String updateContract(ModelMap model, @PathVariable("id") int id) {
		Discipline discipline = disciplineService.getDisciplineById(id);
		model.addAttribute("discipline", discipline);
		return "admin/EditDiscipline";
	}

	@PostMapping(value = "admin/updateDiscipline")
	public String postUpdateContract(RedirectAttributes redirectAttributes, ModelMap model, @RequestParam("id") int id,
			@RequestParam("typeDiscipline") String typeDiscipline, @RequestParam("date") String date,
			@RequestParam("descent") String descent, @RequestParam("reason") String reason,
			@RequestParam("employee") int idEmployee) throws ParseException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Employee employee = new Employee();
			Discipline discipline = new Discipline();
			if (Strings.isEmpty(date)) {
				List<Employee> employees = employeeService.getAlls();
				Discipline discipline1 = disciplineService.getDisciplineById(id);
				discipline1.setDate(null);
				model.addAttribute("discipline", discipline1);
				model.addAttribute("employee", employees);
				model.addAttribute("errorDate", "Ngày kỷ luật không được để trống");
				return "admin/EditDiscipline";
			}
			if (Strings.isEmpty(reason)) {
				List<Employee> employees = employeeService.getAlls();
				Discipline discipline1 = disciplineService.getDisciplineById(id);
				discipline1.setReason(null);
				model.addAttribute("discipline", discipline1);
				model.addAttribute("employee", employees);
				model.addAttribute("errorReason", "Lý do kỷ luật không được để trống");
				return "admin/EditDiscipline";
			}
			employee.setId(idEmployee);
			discipline.setId(id);
			discipline.setType(typeDiscipline);
			discipline.setEmployee(employee);
			discipline.setReason(reason);
			discipline.setDescent(descent);
			discipline.setDate(sdf.parse(date));
			disciplineService.update(discipline);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return "redirect:/admin/listDiscipline";
	}

	@GetMapping(value = "admin/deleteDiscipline/{id}")
	public String deleteDiscipline(ModelMap model, @PathVariable("id") int id) {
		disciplineService.delete(id);
		return "redirect:/admin/listDiscipline";
	}
}

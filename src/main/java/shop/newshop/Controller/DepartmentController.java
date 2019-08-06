package shop.newshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shop.newshop.Entity.Department;
import shop.newshop.Service.DepartmentService;

@Controller
public class DepartmentController {

	@Autowired
	DepartmentService departService;

	private String name;

	@PostMapping(value = "admin/listDepartment")
	public String listDepartmentSearch(ModelMap model, @RequestParam("nameDepart") String nameDepart) {
		model.put("listDepart", departService.getAlls(0, 5, nameDepart));
		name = nameDepart;
		long countAll = departService.countAll(nameDepart);
		long totalPage = 0;

		if (countAll == 0) {
			totalPage = 1;
			model.addAttribute("searchFail","Không tìm thấy dữ liệu");
		} else {
			if (countAll % 5 == 0) {
				totalPage = countAll / 5;
			} else {
				totalPage = countAll / 5 + 1;
			}
		}

		model.put("totalPage", totalPage);
		model.put("totalDepart", countAll);
		model.put("firstDepart", 1);
		if (countAll < 5) {
			model.put("lastDepart", countAll);
		} else {
			model.put("lastDepart", 5);
		}
		model.put("nameSearch", name);

		return  "admin/Department";
	}

	@GetMapping(value = "admin/listDepartment")
	public String listDepartment(ModelMap model) {
		model.put("listDepart", departService.getAlls(0, 5, null));
		long countAll = departService.countAll(null);
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
		model.put("totalDepart", countAll);
		model.put("firstDepart", 1);
		if(countAll<5) {
			model.put("lastDepart", countAll);
		} else {
			model.put("lastDepart", 5);
		}
		model.put("nameSearch", null);

		return "admin/Department";
	}

	@GetMapping(value = "admin/listDepartment/{page}")
	public String listDepartmentPage(ModelMap model, @PathVariable("page") int page) {
		model.put("listDepart", departService.getAlls((page - 1) * 5, 5, name));

		long countAll = departService.countAll(name);
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
		model.put("totalDepart", countAll);
		model.put("firstDepart", (page - 1) * 5 + 1);
		if (page < totalPage) {
			model.put("lastDepart", (page - 1) * 5 + 5);
		} else {
			model.put("lastDepart", countAll);
		}

		model.put("nameSearch", name);
		return "admin/Department";
	}

	@GetMapping(value = "admin/addDepartment")
	public String addDepartment(ModelMap model) {
		model.put("department", new Department());
		return "admin/AddDepartment";
	}

	@GetMapping(value = "admin/editDepartment/{id}")
	public String editDepartment(ModelMap model, @PathVariable("id") int idDepart) {
		model.put("department", departService.getDepartById(idDepart));
		return "admin/AddDepartment";
	}

	@PostMapping(value = "admin/saveDepartment")
	public String saveDepartment(ModelMap model, @ModelAttribute("department") Department depart) {
		try {
			if (!depart.getName().isEmpty()) {
				if (depart.getId() != 0) {
					if (departService.checkName(depart.getName(), depart.getId()) == 0) {
						departService.update(depart);
						return "redirect:/admin/listDepartment";
					} else {
						model.put("error", "Tên phòng ban đã tồn tại");
					}
				} else {
					if (departService.checkName(depart.getName(), depart.getId()) == 0) {
						departService.insert(depart);
						return "redirect:/admin/listDepartment";
					} else {
						model.put("error", "Tên phòng ban đã tồn tại");
					}
				}
				model.put("department", depart);
				return "admin/AddDepartment";
			} else {
				model.put("error", "Không được để trống tên phòng ban");
				model.put("department", depart);
				return "admin/AddDepartment";
			}
		} catch (Exception e) {
			model.put("error", "Lỗi không xác định");
			model.put("department", depart);
			return "admin/AddDepartment";
		}

	}

	@GetMapping(value = "admin/deleteDepart/{id}")
	public String deleteDepart(ModelMap map, @PathVariable("id") int id) {
		departService.delete(id);
		return "redirect:/admin/listDepartment";
	}
}

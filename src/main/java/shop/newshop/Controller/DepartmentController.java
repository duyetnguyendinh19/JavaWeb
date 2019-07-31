package shop.newshop.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import shop.newshop.Entity.Department;
import shop.newshop.Service.DepartmentService;

@Controller
public class DepartmentController {

	@Autowired
	DepartmentService departService;

	@GetMapping(value = "admin/listDepartment")
	public String listDepartment(ModelMap model) {
		model.put("listDepart", departService.getAlls(0,5));

		long countAll = departService.countAll();
		long totalPage = 0;

		if(countAll%5==0) {
			totalPage = countAll/5;
		}else {
			totalPage = countAll/5 + 1;
		}

		model.put("totalPage", totalPage);
		return  "admin/Department";
	}

	@GetMapping(value = "admin/listDepartment/{page}")
	public String listDepartmentPage(ModelMap model, @PathVariable("page") int page) {
		model.put("listDepart", departService.getAlls((page-1)*5,5));

		long countAll = departService.countAll();
		long totalPage = 0;

		if(countAll%5==0) {
			totalPage = countAll/5;
		}else {
			totalPage = countAll/5 + 1;
		}

		model.put("totalPage", totalPage);
		return  "admin/Department";
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
 			if(!depart.getName().isEmpty()) {
				if(depart.getId()!=0) {
					if(departService.checkName(depart.getName(), depart.getId())==0) {
						departService.update(depart);
						return "redirect:/admin/listDepartment";
					}else {
						model.put("error", "Tên phòng ban đã tồn tại");
					}
				}else {
					if(departService.checkName(depart.getName(), depart.getId())==0) {
						departService.insert(depart);
						return "redirect:/admin/listDepartment";
					}else {
						model.put("error", "Tên phòng ban đã tồn tại");
					}		
				}	
				return "admin/AddDepartment";
			}else {
				model.put("error", "Không được để trống tên phòng ban");
				return "admin/AddDepartment";
			}	
		} catch (Exception e) {
			model.put("error", "Lỗi không xác định");
			return "admin/AddDepartment";
		}

	}

	@GetMapping(value = "admin/deleteDepart/{id}")
	public String deleteDepart(ModelMap map, @PathVariable("id") int id) {
		departService.delete(id);
		return "redirect:/admin/listDepartment";
	}
}

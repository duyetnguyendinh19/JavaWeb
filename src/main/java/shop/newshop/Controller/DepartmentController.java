package shop.newshop.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
	
	@GetMapping(value = "admin/listDepartment")
	public String listDepartment(ModelMap model) {
		model.put("listDepart", departService.getAlls());
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
					departService.update(depart);
				}else {
					departService.insert(depart);
				}
				return "redirect:/admin/listDepartment";
			}else {
				model.put("error", "Không được để trống tên phòng ban");
				return "admin/AddDepartment";
			}	
		} catch (Exception e) {
			model.put("error", "Lỗi không xác định");
			return "admin/AddDepartment";
		}
		
	}
	
	int idDepart;	
 	@GetMapping(value = "admin/deleteDepart")
	public String deleteDepart(ModelMap map,HttpServletRequest request) {
 		int id = Integer.parseInt(request.getParameter("id"));
 		departService.delete(id);
 		return "redirect:/listDepartment"; 
 	}
}

package shop.newshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import shop.newshop.Service.DepartmentService;

@Controller
public class DepartmentController {

	@Autowired
	DepartmentService departService;
	
	@GetMapping(value = "listDepartment")
	public String listDepartment(ModelMap model) {
		return  "admin/Department";
	}
	
}

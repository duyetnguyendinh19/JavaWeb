package shop.newshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import shop.newshop.Entity.Employee;
import shop.newshop.Service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService empService;
	
	@GetMapping(value = "admin/listEmployee")
	public String listEmployee(ModelMap model) {
		
		model.put("listEmployee", empService.getAlls());
		
		return "admin/Employees";
	}
	
	@GetMapping(value = "admin/addEmployee")
	public String addEmployee(ModelMap model) {
		model.put("employee", new Employee());
		return "admin/Addemployees";
	}
	
	
}

package shop.newshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import shop.newshop.Entity.*;
import shop.newshop.Service.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BonusController {

	@Autowired
	private BonusService bonusService;

	@Autowired
	private ContractService contractService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private DisciplineService disciplineService;

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping(value = "listBonus")
	public String getBonus(ModelMap map) {
		List<Employee> listContract = new ArrayList<>();
		listContract =  employeeService.getAlls();
		for(Employee x : listContract){
			System.out.println("Contract: " + x.getPhone());
		}
		map.put("lstBonus", bonusService.getAlls());
		return "admin/index";
	}
	
}

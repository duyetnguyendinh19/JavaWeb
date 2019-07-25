package shop.newshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import shop.newshop.Service.DepartmentService;

@Controller
public class DepartmentController {

	@Autowired
	DepartmentService departService;
	
}

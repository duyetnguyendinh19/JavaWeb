package shop.newshop.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import shop.newshop.Entity.Employee;
import shop.newshop.Service.DepartmentService;
import shop.newshop.Service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService empService;

	@Autowired
	DepartmentService departService;

	private String regexphone = "^0[0-9]+$";
	private String regexname="^[a-zA-Z0-9_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêếìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]+$";
	private String regexemail = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
	
	@GetMapping(value = "admin/listEmployee")
	public String listEmployee(ModelMap model) {
		model.put("error", "");
		model.put("listEmployee", empService.getAlls());
		

		return "admin/Employees";
	}

	@GetMapping(value = "admin/addEmployee")
	public String addEmployee(ModelMap model) {
		model.put("employee", new Employee());
		model.put("error", "");
		model.put("deparment", departService.getAlls());
		return "admin/Addemployees";
	}
	
	@GetMapping(value = "admin/editEmployee/{id}")
	public String editEmployee(ModelMap model, @PathVariable("id") int idEmployee) {
		model.put("employee", empService.getEmployeeById(idEmployee));
		model.put("error", "");
		model.put("deparment", departService.getAlls());
		return "admin/Addemployees";
	}

	@PostMapping(value = "admin/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee, ModelMap model, @RequestParam("image") String file ,
			@RequestParam("idDepartment") String idDepartment) {
		try {
			int idDepart = Integer.parseInt(idDepartment);
			if(employee.getName().isEmpty()) {
				model.put("error", "Không được để trống Tên nhân viên");
			}else if(employee.getBirthday()==null) {
				model.put("error", "Không được để trống Ngày sinh");
			}else if(employee.getPhone().isEmpty()) {
				model.put("error", "Không được để trống Số điện thoại");
			}else if(employee.getIdentitycard().isEmpty()) {
				model.put("error", "Không được để trống Chứng minh nhân dân");
			}else if(employee.getEmail().isEmpty()) {
				model.put("error", "Không được để trống Email");
			}
			else if(employee.getAddress().isEmpty()) {	
				model.put("error", "Không được để trống Địa chỉ");
			}			
			else {
				if(!employee.getName().matches(regexname)) {
					model.put("error", "Tên nhân viên không được chứa ký tự đặc biệt");
				}else if(employee.getBirthday().compareTo(new Date())>=0) {
					model.put("error", "Ngày sinh phải nhỏ hơn ngày hiện tại");
				}
				else if(employee.getPhone().length()!=10) {
					model.put("error", "Số điện thoại phải có 10 số");
				}else if(!employee.getPhone().matches(regexphone)){
					model.put("error", "Sai định dạng Số điện thoại");
				}else if(!employee.getEmail().matches(regexemail)) {
					model.put("error", "Sai định dạng Email");
				}
				else {
					employee.setDepartment(departService.getDepartById(idDepart));
					if(employee.getId() == 0) {	
						file.length();
						employee.setAvatar(file);
						empService.insert(employee);
					}else {

						empService.update(employee);
					}
					return "redirect:/admin/listEmployee";
				}
			}

		} catch (Exception e) {
			model.put("error", "Lỗi không xác định");
		}
		model.put("deparment", departService.getAlls());
		return "admin/Addemployees";
	}

	@GetMapping( value = "admin/deleteEmployee/{id}")
	public String delete(@PathVariable("id") int id, ModelMap model) {
		try {
			empService.delete(id);
		} catch (Exception e) {
			model.put("error", "Lỗi không xác định");
			return "admin/Employees";
		}
		return "redirect:/admin/listEmployee";
	}

}

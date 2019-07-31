package shop.newshop.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
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

import shop.newshop.Entity.Account;
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
	private static final String PATH = "D:\\Git\\JavaWeb\\src\\main\\resources\\static\\images\\";


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
		Employee employee =  empService.getEmployeeById(idEmployee);
		model.put("employee",employee);
		model.put("username", employee.getAccount().getUsername());
		model.put("error", "");
		model.put("deparment", departService.getAlls());
		return "admin/EditEmployees";
	}

	@GetMapping(value = "admin/inforEmployee/{id}")
	public String inforEmployee(ModelMap model, @PathVariable("id") int idEmployee) {
		Employee employee = empService.getEmployeeById(idEmployee);
		if(employee.getAvatar()!=null) {
			File file = new File(PATH + employee.getAvatar());
			model.put("urlimage", ("data:image/jpeg;base64," + encodeFileToBase64Binary(file)) );	
		}	
		model.put("employee", employee);
		
		return "admin/Detailemployees";
	}

	@PostMapping(value = "admin/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee, ModelMap model, @RequestParam("avatar1") MultipartFile file ,
			@RequestParam("idDepartment") String idDepartment, @RequestParam("username") String username) {
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
			}else if(username.isEmpty()) {
				model.put("error", "Không được để trống tài khoản");
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
				}else if(!(employee.getIdentitycard().length() == 10 || employee.getIdentitycard().length() == 12)) {
					model.put("error", "Chứng minh nhân dân phải có 10 số hoặc 12 số");
				}
				else if(username.length() < 6 || username.length()>24) {
					model.put("error", "Tài khoản từ 6 đến 24 ký tự");
				}
				else {
					employee.setDepartment(departService.getDepartById(idDepart));
					if(employee.getId() == 0) {	
						if(file!=null) {
							byte[] bytes = file.getBytes();
							String filename = ( randomAlphaNumeric(10) + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),file.getOriginalFilename().length())).toLowerCase();
							employee.setAvatar(filename);
							BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(PATH+filename));
							stream.write(bytes);
						}
						Account account = new Account();
						account.setUsername(username);
						account.setPassword("1");
						empService.insert(employee,account);
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

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	//ConvertToBase64
	private static String encodeFileToBase64Binary(File file){
		String encodedfile = null;
		try {
			FileInputStream fileInputStreamReader = new FileInputStream(file);
			byte[] bytes = new byte[(int)file.length()];
			fileInputStreamReader.read(bytes);
			encodedfile = Base64.getEncoder().encodeToString(bytes);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return encodedfile;
	}

}

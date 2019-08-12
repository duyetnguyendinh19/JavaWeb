package shop.newshop.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.util.Strings;
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
import shop.newshop.Entity.Department;
import shop.newshop.Entity.Employee;
import shop.newshop.Service.AccountService;
import shop.newshop.Service.AttendanceService;
import shop.newshop.Service.BonusService;
import shop.newshop.Service.ContractService;
import shop.newshop.Service.DepartmentService;
import shop.newshop.Service.DisciplineService;
import shop.newshop.Service.EmployeeService;
import shop.newshop.Service.SalaryService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService empService;

	@Autowired
	DepartmentService departService;

	@Autowired
	ContractService contractService;

	@Autowired
	AccountService accountService;

	@Autowired
	BonusService bonusService;
	
	@Autowired
	DisciplineService disciplineService;
	
	@Autowired
	AttendanceService attendanceService;
	
	@Autowired
	SalaryService salaryService;
	
	private String regexphone = "^0[0-9]+$";
	private String regexname = "^[a-zA-Z0-9_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêếìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]+$";
	private String regexemail = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
	private static final String PATH = Paths.get("").toAbsolutePath().toString()
			+ "\\src\\main\\resources\\static\\images\\";
	private String name;
	private String oldImg = "";
	private Account account;

	@PostMapping(value = "admin/listEmployee")
	public String listEmployeeSearch(ModelMap model, @RequestParam("nameEmployee") String nameSearch) {
		name = nameSearch;
		model.put("error", "");
		model.put("listEmployee", empService.getLimit(0, 5, nameSearch));
		long countAll = empService.countAll(nameSearch);
		long totalPage = 0;

		if (countAll == 0) {
			totalPage = 1;
			model.addAttribute("searchFail", "Không tìm thấy dữ liệu");
		} else {
			if (countAll % 5 == 0) {
				totalPage = countAll / 5;
			} else {
				totalPage = countAll / 5 + 1;
			}
		}
		model.put("totalPage", totalPage);
		model.put("totalEmp", countAll);
		model.put("firstEmp", 1);
		if (countAll < 5) {
			model.put("lastEmp", countAll);
		} else {
			model.put("lastEmp", 5);
		}
		model.put("nameSearch", nameSearch);

		return "admin/Employees";
	}

	@GetMapping(value = "admin/listEmployee")
	public String listEmployee(ModelMap model) {
		model.put("error", "");
		model.put("listEmployee", empService.getLimit(0, 5, null));
		long countAll = empService.countAll(null);
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
		model.put("totalEmp", countAll);
		model.put("firstEmp", 1);
		if (countAll < 5) {
			model.put("lastEmp", countAll);
		} else {
			model.put("lastEmp", 5);
		}
		model.put("nameSearch", null);

		return "admin/Employees";
	}

	@GetMapping(value = "admin/listEmployee/{page}")
	public String listEmployeePage(ModelMap model, @PathVariable("page") int page) {
		model.put("error", "");
		model.put("listEmployee", empService.getLimit((page - 1) * 5, 5, name));

		long countAll = empService.countAll(name);
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
		model.put("totalEmp", countAll);
		model.put("firstEmp", (page - 1) * 5 + 1);
		if (page < totalPage) {
			model.put("lastEmp", (page - 1) * 5 + 5);
		} else {
			model.put("lastEmp", countAll);
		}

		model.put("nameSearch", name);
		return "admin/Employees";
	}

	@GetMapping(value = "admin/addEmployee")
	public String addEmployee(ModelMap model) {
		Employee employee = new Employee();
		model.put("employee", employee);
		model.put("error", "");
		model.put("deparment", departService.getAlls());
		model.put("username", null);
		return "admin/Addemployees";
	}

	@GetMapping(value = "admin/editEmployee/{id}")
	public String editEmployee(ModelMap model, @PathVariable("id") int idEmployee) {
		Employee employee = empService.getEmployeeById(idEmployee);
		model.put("employee", employee);
		if (employee.getAvatar() != null && !employee.getAvatar().isEmpty()) {
			File file = new File(PATH + employee.getAvatar());
			model.put("urlimage", ("data:image/jpeg;base64," + encodeFileToBase64Binary(file)));
			oldImg = ("data:image/jpeg;base64," + encodeFileToBase64Binary(file));
		}
		account = employee.getAccount();
		model.put("username", employee.getAccount().getUsername());
		model.put("error", "");
		model.put("deparment", departService.getAlls());

		SimpleDateFormat sdfFormat = new SimpleDateFormat("MM/dd/yyyy");

		model.put("birthday", sdfFormat.format(employee.getBirthday()));

		return "admin/EditEmployees";
	}

	@GetMapping(value = "admin/inforEmployee/{id}")
	public String inforEmployee(ModelMap model, @PathVariable("id") int idEmployee) {
		Employee employee = empService.getEmployeeById(idEmployee);
		if (employee.getAvatar() != null) {
			File file = new File(PATH + employee.getAvatar());
			model.put("urlimage", ("data:image/jpeg;base64," + encodeFileToBase64Binary(file)));
		}

		SimpleDateFormat sdfFormat = new SimpleDateFormat("dd/MM/yyyy");

		model.put("birthday", sdfFormat.format(employee.getBirthday()));
		model.put("employee", employee);

		return "admin/Detailemployees";
	}

	@PostMapping(value = "admin/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee, ModelMap model,
			@RequestParam("avatar1") MultipartFile file, @RequestParam("idDepartment") String idDepartment,
			@RequestParam("username") String username, @RequestParam("birthday") String birthday) {
		try {
			int idDepart = Integer.parseInt(idDepartment);
			if (employee.getName().isEmpty()) {
				model.put("error", "Không được để trống Tên nhân viên");
			} else if (birthday.isEmpty()) {
				model.put("error", "Không được để trống Ngày sinh");
			} else if (employee.getPhone().isEmpty()) {
				model.put("error", "Không được để trống Số điện thoại");
			} else if (employee.getIdentitycard().isEmpty()) {
				model.put("error", "Không được để trống Chứng minh nhân dân");
			} else if (employee.getEmail().isEmpty()) {
				model.put("error", "Không được để trống Email");
			} else if (employee.getAddress().isEmpty()) {
				model.put("error", "Không được để trống Địa chỉ");
			} else if (username.isEmpty()) {
				model.put("error", "Không được để trống tài khoản");
			} else {

				SimpleDateFormat sdfParse = new SimpleDateFormat("dd/MM/yyyy");
				employee.setBirthday(sdfParse.parse(birthday));

				if (!employee.getName().matches(regexname)) {
					model.put("error", "Tên nhân viên không được chứa ký tự đặc biệt");
				} else if (getAge(employee.getBirthday()) < 18) {
					model.put("error", "Nhân viên phải đủ 18 tuổi");
				} else if (employee.getPhone().length() != 10) {
					model.put("error", "Số điện thoại phải có 10 số");
				} else if (!employee.getPhone().matches(regexphone)) {
					model.put("error", "Sai định dạng Số điện thoại");
				} else if (!employee.getEmail().matches(regexemail)) {
					model.put("error", "Sai định dạng Email");
				} else if (!(employee.getIdentitycard().length() == 10 || employee.getIdentitycard().length() == 12)) {
					model.put("error", "Chứng minh nhân dân phải có 10 số hoặc 12 số");
				} else if (username.length() < 6 || username.length() > 24) {
					model.put("error", "Tài khoản từ 6 đến 24 ký tự");
				} else if (empService.check(employee.getEmail(), employee.getPhone(), username,
						employee.getIdentitycard(), employee.getId()) == 1) {
					model.put("error", "Email đã tồn tại");
				} else if (empService.check(employee.getEmail(), employee.getPhone(), username,
						employee.getIdentitycard(), employee.getId()) == 2) {
					model.put("error", "Số điện thoại đã tồn tại");
				} else if (empService.check(employee.getEmail(), employee.getPhone(), username,
						employee.getIdentitycard(), employee.getId()) == 3) {
					model.put("error", "Chứng minh nhân dân đã tồn tại");
				} else if (empService.check(employee.getEmail(), employee.getPhone(), username,
						employee.getIdentitycard(), employee.getId()) == 4) {
					model.put("error", "Tài khoản đã tồn tại");
				} else {
					Department department = departService.getDepartById(idDepart);
					employee.setDepartment(department);
					if (employee.getId() == 0) {
						if (!file.isEmpty()) {
							byte[] bytes = file.getBytes();
							String filename = (randomAlphaNumeric(10) + file.getOriginalFilename().substring(
									file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length()))
											.toLowerCase();
							employee.setAvatar(filename);
							BufferedOutputStream stream = new BufferedOutputStream(
									new FileOutputStream(PATH + filename));
							stream.write(bytes);
						}
						Account account = new Account();
						account.setUsername(username);
						account.setPassword(encryptThisString("123456"));
						if (department.getName().toLowerCase().contains("nhân sự")) {
							account.setRole(1);
						}
						empService.insert(employee, account);
					} else {
						if (!file.isEmpty()) {
							if (oldImg != file.getOriginalFilename()) {
								byte[] bytes = file.getBytes();
								String filename = (randomAlphaNumeric(10) + file.getOriginalFilename().substring(
										file.getOriginalFilename().lastIndexOf("."),
										file.getOriginalFilename().length())).toLowerCase();
								employee.setAvatar(filename);
								BufferedOutputStream stream = new BufferedOutputStream(
										new FileOutputStream(PATH + filename));
								stream.write(bytes);
							}
						}

						if (!department.getName().toLowerCase().contains("nhân sự")) {
							account.setRole(0);
							accountService.update(account);
						}

						employee.setAccount(account);

						empService.update(employee);
					}
					return "redirect:/admin/listEmployee";
				}
			}

		} catch (Exception e) {
			model.put("error", "Lỗi không xác định");
		}
		model.put("deparment", departService.getAlls());
		model.put("username", username);

		if (employee.getBirthday() != null) {
			SimpleDateFormat sdfFormat = new SimpleDateFormat("MM/dd/yyyy");

			model.put("birthday", sdfFormat.format(employee.getBirthday()));

		}

		if (employee.getId() == 0) {
			return "admin/Addemployees";
		} else {
			if (employee.getAvatar() != null && !employee.getAvatar().isEmpty()) {
				File fileEdit = new File(PATH + employee.getAvatar());
				model.put("urlimage", ("data:image/jpeg;base64," + encodeFileToBase64Binary(fileEdit)));
				oldImg = ("data:image/jpeg;base64," + encodeFileToBase64Binary(fileEdit));
			}
			return "admin/EditEmployees";
		}
	}

	@GetMapping(value = "admin/deleteEmployee/{id}")
	public String delete(@PathVariable("id") int id, ModelMap model) {
		try {
			empService.delete(id);
		} catch (Exception e) {
			model.put("error", "Lỗi không xác định");
			return "admin/Employees";
		}
		return "redirect:/admin/listEmployee";
	}

	@GetMapping(value = "employee/inforEmployee")
	public String inforEmployee(ModelMap model, HttpSession session) {
		Employee employee = empService
				.getEmployeeById(((Account) session.getAttribute("account")).getEmployee().getId());
		if (employee.getAvatar() != null) {
			File file = new File(PATH + employee.getAvatar());
			model.put("urlimage", ("data:image/jpeg;base64," + encodeFileToBase64Binary(file)));
		}

		SimpleDateFormat sdfFormat = new SimpleDateFormat("dd/MM/yyyy");

		model.put("birthday", sdfFormat.format(employee.getBirthday()));
		model.put("employee", employee);

		if (employee != null) {
			model.put("listContract", contractService.getContractByEmployee(employee));
			model.put("bonusList", bonusService.getBonusByEmployee(employee));
			model.put("disciplineList", disciplineService.getDisciplinesByEmployee(employee));
			model.put("listAttendance", attendanceService.getAttendanceByEmployee(employee));
			model.put("listSalary", salaryService.getSalaryByEmployee(employee.getId()));
		}

		return "employee/inforEmployee";
	}
	
	@GetMapping("employee/changePassword")
	public String reset() {
		return "employee/ChangePassword";
	}

	@PostMapping("employee/resetPassword")
	public String resetPass(ModelMap model, HttpSession session, @RequestParam("oldPass") String oldPass,
			@RequestParam("newPass") String newPass, @RequestParam("confirmPass") String confirmPass) {

		Account account = accountService.getAccountById(((Account) session.getAttribute("account")).getId());
		if (Strings.isEmpty(oldPass)) {
			model.addAttribute("errorOldPass", "Mật khẩu cũ không được để trống");
			return "employee/ChangePassword";
		} else if (Strings.isEmpty(newPass)) {
			model.addAttribute("errorNewPass", "Mật khẩu mới không được để trống");
			model.addAttribute("oldPass", oldPass);
			return "employee/ChangePassword";
		} else if (Strings.isEmpty(confirmPass)) {
			model.addAttribute("errorComfirmPass", "Nhập lại mật khẩu không được để trống");
			model.addAttribute("newPass", newPass);
			model.addAttribute("oldPass", oldPass);
			return "employee/ChangePassword";
		} else {
			if (!encryptThisString(oldPass).equals(account.getPassword())) {
				model.addAttribute("errorOldPass", "Nhập sai mật khẩu cũ");
				model.addAttribute("newPass", newPass);
				model.addAttribute("confirmPass", confirmPass);
				return "employee/ChangePassword";
			} else if (newPass.length() > 32) {
				model.addAttribute("errorNewPass", "Mật khẩu mới không quá 32 kí tự");
				model.addAttribute("oldPass", oldPass);
				return "employee/ChangePassword";
			} else if (encryptThisString(oldPass).equals(encryptThisString(newPass))) {
				model.addAttribute("errorNewPass", "Mật khẩu mới trùng với mật khẩu cũ");
				model.addAttribute("oldPass", oldPass);
				return "employee/ChangePassword";
			} else if (!confirmPass.equals(newPass)) {
				model.addAttribute("errorComfirmPass", "Nhập lại mật khẩu không trùng mật khẩu mới");
				model.addAttribute("oldPass", oldPass);
				return "employee/ChangePassword";
			} else {
				account.setId(account.getId());
				account.setPassword(encryptThisString(newPass));
				accountService.update(account);
				model.addAttribute("success", "Đổi mật khẩu thành công");
			}
		}
		return "employee/ChangePassword";
	}

	private int getAge(Date date) {
		int age = 0;

		Date nowDate = new Date();

		age = nowDate.getYear() - date.getYear();

		if (nowDate.getMonth() < date.getMonth()) {
			age = age - 1;
		}
		if (nowDate.getMonth() == date.getMonth() && nowDate.getDate() < date.getDate()) {
			age = age - 1;
		}

		return age;
	}

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	// ConvertToBase64
	private static String encodeFileToBase64Binary(File file) {
		String encodedfile = null;
		try {
			FileInputStream fileInputStreamReader = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
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

	public static String encryptThisString(String input) {
		try {
			// getInstance() method is called with algorithm SHA-512
			MessageDigest md = MessageDigest.getInstance("SHA-512");

			// digest() method is called
			// to calculate message digest of the input string
			// returned as array of byte
			byte[] messageDigest = md.digest(input.getBytes());

			// Convert byte array into signum representation
			BigInteger no = new BigInteger(1, messageDigest);

			// Convert message digest into hex value
			String hashtext = no.toString(16);

			// Add preceding 0s to make it 32 bit
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			// return the HashText
			return hashtext;
		}

		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}

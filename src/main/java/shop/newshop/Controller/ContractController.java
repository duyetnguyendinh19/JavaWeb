package shop.newshop.Controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.newshop.Entity.*;
import shop.newshop.Service.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ContractController {

	@Autowired
	private ContractService contractService;

	@Autowired
	private EmployeeService employeeService;

	//
	// private static final String URL_IMG_PATH = "D:/image_qlnv";
	// private static final String PATH = "/avatar/";
	private String name;

	// @RequestMapping(value = "upload", method = RequestMethod.POST)
	// public String multiPartFile(RedirectAttributes map,
	// @RequestParam("startDate") String startDate) {
	// try {
	// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	// Calendar c = Calendar.getInstance();
	// Date date = sdf.parse(startDate);
	// c.setTime(date);
	// Calendar.DAY_OF_WEEK = 7 => Thứ 7
	// Calendar.DAY_OF_WEEK = 1 => Chủ nhật
	// System.out.println("Get Sunday: " + c.get(Calendar.DAY_OF_WEEK));
	// Lấy time hiện tại
	// LocalTime time = LocalTime.now();
	// System.out.println("Time: " + time.getHour() + ":" + time.getMinute() + ":" +
	// time.getSecond());
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	// return "redirect:/uploadStatus";
	// }

	// @GetMapping(value = "test")
	// public String test(){
	// return "admin/testImage";
	// }
	// @GetMapping(value = {"/", "index"})
	// public String index(){
	// return "employee/index";
	// }

	// @PostMapping(value = "upload")
	// public String PostUpLoad(@RequestParam("avatar") MultipartFile avatar){
	// System.out.println(avatar);
	// Employee employee = new Employee();
	// String filePath = URL_IMG_PATH + PATH;
	// String host = "localhost:9999";
	// String sourcePath = host + "/image_qlnv/avatar/";
	// FileManagerService.Result result = FileManagerService.StorageFile(filePath,
	// avatar, false, false,null);
	// if(result.isSuccess()){
	// System.out.println(result.getResult());
	// System.out.println(FileManagerService.encodeImgFileToBase64BinarySrc(result.getResult()));
	// }
	// return "admin/testImage";
	// }

	@PostMapping(value = "admin/listContract")
	public String listContractSearch(ModelMap model, @RequestParam("nameEmployee") String nameSearch) {
		name = nameSearch;
		model.put("error", "");
		model.put("listContract", contractService.getLimit(0, 5, nameSearch));
		long countAll = contractService.countAll(nameSearch);
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
		model.put("totalContract", countAll);
		model.put("firstContract", 1);
		if (countAll < 5) {
			model.put("lastContract", countAll);
		} else {
			model.put("lastContract", 5);
		}
		model.put("nameSearch", nameSearch);

		return "admin/Contract";
	}

	@GetMapping(value = "admin/listContract")
	public String listContract(ModelMap model) {
		
		if(employeeService.getAlls() == null || employeeService.getAlls().isEmpty()) {
			return "redirect:/admin/listEmployee";
		}
		
		model.put("error", "");
		model.put("listContract", contractService.getLimit(0, 5, null));
		long countAll = contractService.countAll(null);
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
		model.put("totalContract", countAll);
		model.put("firstContract", 1);
		if (countAll < 5) {
			model.put("lastContract", countAll);
		} else {
			model.put("lastContract", 5);
		}
		model.put("nameSearch", null);

		return "admin/Contract";
	}

	@GetMapping(value = "admin/listContract/{page}")
	public String listContractPage(ModelMap model, @PathVariable("page") int page) {
		model.put("error", "");
		model.put("listContract", contractService.getLimit((page - 1) * 5, 5, name));

		long countAll = contractService.countAll(name);
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
		model.put("totalContract", countAll);
		model.put("firstContract", (page - 1) * 5 + 1);
		if (page < totalPage) {
			model.put("lastContract", (page - 1) * 5 + 5);
		} else {
			model.put("lastContract", countAll);
		}

		model.put("nameSearch", name);
		return "admin/Contract";
	}

	@GetMapping(value = "admin/addContract")
	public String addContract(ModelMap model) {
		List<Employee> employees = new ArrayList<>();
		employees = employeeService.getAlls();
		model.addAttribute("employee", employees);

		return "admin/Addcontract";
	}

	@PostMapping(value = "admin/addContract")
	public String insertContract(RedirectAttributes redirectAttributes, ModelMap model,
			@RequestParam("salary") String salary, @RequestParam("startday") String startday,
			@RequestParam("expirationday") String expirationday, @RequestParam("employee") int idEmployee) {
		try {
			List<Employee> employees = new ArrayList<>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Employee employee = new Employee();
			Contract contract = new Contract();
			Contract contractgetByIdEmployee = contractService.getContractByEmployeeId(idEmployee);
			if (Strings.isEmpty(salary)) {
				model.put("errorSalary", "Lương không được để trống");
				employees = employeeService.getAlls();
				model.addAttribute("idEmployee", idEmployee);
				model.addAttribute("employee", employees);
				return "admin/Addcontract";
			}
			if (Strings.isEmpty(startday)) {
				System.out.println("abcbc");
				model.put("errorstartday", "Ngày bắt đầu không được để trống");
				employees = employeeService.getAlls();
				model.addAttribute("employee", employees);
				model.addAttribute("salary", salary);
				return "admin/Addcontract";
			}
			if (contractgetByIdEmployee != null) {
				System.out.println(sdf.parse(startday));
				System.out.println(contractgetByIdEmployee.getExpirationday());
				if (sdf.parse(startday).compareTo(contractgetByIdEmployee.getExpirationday()) < 0) {
					model.put("errorstartday", "Ngày bắt đầu hợp đồng mới phải lớn hơn hợp đồng cũ");
					employees = employeeService.getAlls();
					model.addAttribute("idEmployee", idEmployee);
					model.addAttribute("employee", employees);
					return "admin/Addcontract";
				}
			}
			if (Strings.isEmpty(expirationday)) {
				model.put("errorexpirationday", "Ngày kết thúc không được để trống");
				employees = employeeService.getAlls();
				model.addAttribute("salary", salary);
				model.addAttribute("employee", employees);
				return "admin/Addcontract";
			}
			contract.setExpirationday(sdf.parse(expirationday));
			contract.setStartday(sdf.parse(startday));
			contract.setSalary(Float.parseFloat(salary.replaceAll(",", "")));
			employee.setId(idEmployee);
			contract.setEmployee(employee);
			contractService.insert(contract);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/listContract";
	}

	@GetMapping(value = "admin/updateContract/{id}")
	public String updateContract(ModelMap model, @PathVariable("id") int id) {
		Contract contract = contractService.getContractById(id);
		model.addAttribute("contract", contract);

		return "admin/Editcontract";
	}

	@PostMapping(value = "admin/updateContract")
	public String postUpdateContract(RedirectAttributes redirectAttributes, ModelMap model, @RequestParam("id") int id,
			@RequestParam("salary") String salary, @RequestParam("startday") String startday,
			@RequestParam("expirationday") String expirationday, @RequestParam("employee") int idEmployee)
			throws ParseException {
		Contract contract = new Contract();
		Employee employee = new Employee();
		Contract contractgetByIdEmployee = contractService.getContractByEmployeeId(idEmployee);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (salary.isEmpty()) {
			contract = contractService.getContractById(id);
			model.put("contract", contract);
			contract.setSalary(Float.parseFloat(""));
			model.put("errorSalary", "Lương không được để trống");
			return "admin/Editcontract";
		}
		// if(contractgetByIdEmployee != null) {
		// if
		// (startday.compareTo(sdf.format(contractgetByIdEmployee.getExpirationday())) <
		// 0) {
		// contract = contractService.getContractById(id);
		// model.put("contract", contract);
		// model.put("errorstartDay", "Ngày bắt đầu hợp đồng mới phải lớn hơn hợp đồng
		// cũ");
		// return "admin/Editcontract";
		// }
		// }
		if (Strings.isEmpty(startday)) {
			contract = contractService.getContractById(id);
			contract.setStartday(null);
			model.put("contract", contract);
			model.put("errorstartDay", "Ngày bắt đầu không được để trống");

			return "admin/Editcontract";
		}
		if (Strings.isEmpty(expirationday)) {
			contract = contractService.getContractById(id);
			contract.setExpirationday(null);
			model.put("contract", contract);
			model.put("errorexpirationday", "Ngày kết thúc không được để trống");
			return "admin/Editcontract";
		}
		contract.setId(id);
		String formatSalary = salary.replaceAll(",", "");
		contract.setSalary(Float.parseFloat(formatSalary));
		contract.setStartday(sdf.parse(startday));
		contract.setExpirationday(sdf.parse(expirationday));
		employee.setId(idEmployee);
		contract.setEmployee(employee);
		contractService.update(contract);
		return "redirect:/admin/listContract";
	}

	@GetMapping(value = "admin/deleteContract/{id}")
	public String deleteContract(ModelMap model, @PathVariable("id") int id) {
		contractService.delete(id);
		return "redirect:/admin/listContract";
	}
}

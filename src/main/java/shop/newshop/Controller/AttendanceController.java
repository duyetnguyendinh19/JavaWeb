package shop.newshop.Controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.newshop.Entity.Attendance;
import shop.newshop.Entity.Employee;
import shop.newshop.Entity.TotalAttendance;
import shop.newshop.Service.AttendanceService;
import shop.newshop.Service.EmployeeService;
import shop.newshop.Service.TotalAttendanceService;

import java.sql.Time;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "admin")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private TotalAttendanceService totalAttendanceService;

	String nameSearch;

	String dateSearch;

	@PostMapping(value = "listAttendance")
	public String getAttendanceSearch(ModelMap model, @RequestParam("name") String name,
			@RequestParam("date") String dateS) {
		Date date = new Date();
		nameSearch = name;
		dateSearch = dateS;
		model.put("name", name);

		model.put("date", dateS);

		List<Attendance> attendanceList = attendanceService.getLimit(0, 10, name, dateS);
		model.addAttribute("listAttendance", attendanceList);
		List<Employee> employeeList = employeeService.getAlls();
		List<Object[]> object = totalAttendanceService.listTotal(date.getMonth() + 1, 1900 + date.getYear());
		List<TotalAttendance> listToTal = new ArrayList<>();
		for (Object[] x : object) {
			TotalAttendance totalAttendance = new TotalAttendance();
			totalAttendance.setIdEmployee(Integer.parseInt(String.valueOf(x[0])));
			totalAttendance.setNameEmployee(String.valueOf(x[1]));
			totalAttendance.setSalary(Float.parseFloat(String.valueOf(x[2])));
			totalAttendance.setMonth(Integer.parseInt(String.valueOf(x[3])));
			totalAttendance.setCount(Double.parseDouble(String.valueOf(x[4])));
			totalAttendance.setDay_off(Double.parseDouble(String.valueOf(x[5])));
			totalAttendance.setTotalSalary(Double.parseDouble((String.valueOf(x[6]))));
			listToTal.add(totalAttendance);
		}
		model.addAttribute("listTotal", listToTal);
		long countAll = attendanceService.countAll(name, dateS);
		long totalPage = 0;

		if (countAll == 0) {
			totalPage = 1;
			model.addAttribute("searchFail", "Không tìm thấy dữ liệu");
		} else {
			if (countAll % 10 == 0) {
				totalPage = countAll / 10;
			} else {
				totalPage = countAll / 10 + 1;
			}
		}

		model.put("totalPage", totalPage);
		model.put("totalAtten", countAll);
		model.put("firstAtten", 1);
		if (countAll < 5) {
			model.put("lastAtten", countAll);
		} else {
			model.put("lastAtten", 10);
		}
		return "admin/Attendance";
	}

	@GetMapping(value = "listAttendance")
	public String getAttendance(ModelMap model) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		model.put("date", sdf.format(date));
		SimpleDateFormat sdff = new SimpleDateFormat("yyyy/MM/dd");
		List<Attendance> attendanceList = attendanceService.getLimit(0, 10, null, sdff.format(date));

		model.addAttribute("listAttendance", attendanceList);
		List<Employee> employeeList = employeeService.getAlls();
		List<Object[]> object = totalAttendanceService.listTotal(date.getMonth() + 1, 1900 + date.getYear());
		List<TotalAttendance> listToTal = new ArrayList<>();
		for (Object[] x : object) {
			TotalAttendance totalAttendance = new TotalAttendance();
			totalAttendance.setIdEmployee(Integer.parseInt(String.valueOf(x[0])));
			totalAttendance.setNameEmployee(String.valueOf(x[1]));
			totalAttendance.setSalary(Float.parseFloat(String.valueOf(x[2])));
			totalAttendance.setMonth(Integer.parseInt(String.valueOf(x[3])));
			totalAttendance.setCount(Double.parseDouble(String.valueOf(x[4])));
			totalAttendance.setDay_off(Double.parseDouble(String.valueOf(x[5])));
			totalAttendance.setTotalSalary(Double.parseDouble((String.valueOf(x[6]))));
			listToTal.add(totalAttendance);
		}
		model.addAttribute("listTotal", listToTal);
		long countAll = attendanceService.countAll(null, sdff.format(date));
		long totalPage = 0;

		if (countAll == 0) {
			totalPage = 1;
			model.addAttribute("searchFail", "Không tìm thấy dữ liệu");
		} else {
			if (countAll % 10 == 0) {
				totalPage = countAll / 10;
			} else {
				totalPage = countAll / 10 + 1;
			}
		}

		model.put("totalPage", totalPage);
		model.put("totalAtten", countAll);
		model.put("firstAtten", 1);
		if (countAll < 5) {
			model.put("lastAtten", countAll);
		} else {
			model.put("lastAtten", 10);
		}
		return "admin/Attendance";
	}

	@GetMapping(value = "listAttendance/{page}")
	public String getAttendancePage(ModelMap model, @PathVariable("page") int page) {
		Date date = new Date();
		List<Attendance> attendanceList = attendanceService.getLimit((page - 1) * 10, 10, nameSearch, dateSearch);
		model.addAttribute("listAttendance", attendanceList);
		List<Employee> employeeList = employeeService.getAlls();
		List<Object[]> object = totalAttendanceService.listTotal(date.getMonth() + 1, 1900 + date.getYear());
		List<TotalAttendance> listToTal = new ArrayList<>();
		for (Object[] x : object) {
			TotalAttendance totalAttendance = new TotalAttendance();
			totalAttendance.setIdEmployee(Integer.parseInt(String.valueOf(x[0])));
			totalAttendance.setNameEmployee(String.valueOf(x[1]));
			totalAttendance.setSalary(Float.parseFloat(String.valueOf(x[2])));
			totalAttendance.setMonth(Integer.parseInt(String.valueOf(x[3])));
			totalAttendance.setCount(Double.parseDouble(String.valueOf(x[4])));
			totalAttendance.setDay_off(Double.parseDouble(String.valueOf(x[5])));
			totalAttendance.setTotalSalary(Double.parseDouble((String.valueOf(x[6]))));
			listToTal.add(totalAttendance);
		}
		model.addAttribute("listTotal", listToTal);
		long countAll = attendanceService.countAll(nameSearch, dateSearch);
		long totalPage = 0;

		if (countAll == 0) {
			totalPage = 1;
			model.addAttribute("searchFail", "Không tìm thấy dữ liệu");
		} else {
			if (countAll % 10 == 0) {
				totalPage = countAll / 10;
			} else {
				totalPage = countAll / 10 + 1;
			}
		}

		model.put("totalPage", totalPage);
		model.put("totalAtten", countAll);
		model.put("firstAtten", (page - 1) * 10 + 1);
		if (countAll < totalPage) {
			model.put("lastAtten", (page - 1) * 10 + 10);
		} else {
			model.put("lastAtten", countAll);
		}
		return "admin/Attendance";
	}

	@GetMapping(value = "addAttendance")
	public String addAttendance(ModelMap model) {
		List<Employee> employees = employeeService.getAlls();
		model.addAttribute("employee", employees);

		return "admin/AddAttendance";
	}

	@PostMapping(value = "addAttendance")
	public String postAttendance(ModelMap model, @RequestParam("date") String dateInput,
			@RequestParam("employee") int employeeId, @RequestParam("timestart") String timestart,
			@RequestParam("timeend") String timeend) {
		try {
//            Calendar c = Calendar.getInstance();
//            Date date = sdf.parse(startDate);
//            c.setTime(date);
//            Calendar.DAY_OF_WEEK = 7 =>Thứ 7
//            Calendar.DAY_OF_WEEK = 1 =>Chủ nhật
//            System.out.println("Get Sunday: " + c.get(Calendar.DAY_OF_WEEK));
//            Lấy time hiện tại
//            LocalTime time = LocalTime.now();
//            System.out.println("Time: " + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond());
			if (timestart.compareTo(timeend) > 0) {
				List<Employee> employees = employeeService.getAlls();
				model.addAttribute("employee", employees);
				model.addAttribute("idEmployee", employeeId);
				model.addAttribute("startTime", " Thời gian kết thúc phải lớn hơn thời gian bắt đầu");
				return "admin/AddAttendance";
			}
			if (Strings.isEmpty(dateInput)) {
				List<Employee> employees = employeeService.getAlls();
				model.addAttribute("employee", employees);
				model.addAttribute("idEmployee", employeeId);
				model.addAttribute("errorDate", " Ngày không được để trống");
				return "admin/AddAttendance";
			}
			Format sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date date = (Date) sdf.parseObject(dateInput);
			Attendance attendance = new Attendance();
			Employee employee = new Employee();
			employee.setId(employeeId);
			System.out.println(employeeId);
			System.out.println(dateInput);
			List<Attendance> list = attendanceService.getAttenDanceByDateAndId(employeeId, date);
			if (list.size() > 0) {
				if (list.get(0).getEndtime().compareTo(Time.valueOf(timeend)) > 0) {
					List<Employee> employees = employeeService.getAlls();
					model.addAttribute("employee", employees);
					model.addAttribute("idEmployee", employeeId);
					model.addAttribute("startTime", "Thời gian kết thúc phải lớn hơn thời gian kết thức lần trước");
					return "admin/AddAttendance";
				} else {
					attendance.setId(list.get(0).getId());
					attendance.setMonth(date.getMonth() + 1);
					attendance.setEmployee(employee);
					attendance.setStarttime(list.get(0).getStarttime());
					attendance.setDate(date);
					attendance.setEndtime(Time.valueOf(timeend));
					attendanceService.update(attendance);
				}
			} else {
				attendance.setDate(date);
				attendance.setEndtime(Time.valueOf(timeend));
				attendance.setStarttime(Time.valueOf(timestart));
				attendance.setEmployee(employee);
				attendance.setMonth(date.getMonth() + 1);
				attendanceService.insert(attendance);
			}
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return "redirect:/admin/listAttendance";
	}
}

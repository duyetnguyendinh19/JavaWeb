package shop.newshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.newshop.Entity.Attendance;
import shop.newshop.Entity.Employee;
import shop.newshop.Service.AttendanceService;
import shop.newshop.Service.EmployeeService;

import java.util.List;

@Controller
@RequestMapping(value = "admin")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "listAttendance")
    public String getAttendance(ModelMap model){
        List<Attendance> attendanceList = attendanceService.getAll();
        model.addAttribute("listAttendance", attendanceList);
        
        return "admin/Attendance";
    }

    @GetMapping(value = "addAttendance")
    public String addAttendance(ModelMap model){
        List<Employee> employees = employeeService.getAlls();
        model.addAttribute("employee", employees);
        
        return "admin/AddAttendance";
    }
}

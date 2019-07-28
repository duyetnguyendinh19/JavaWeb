package shop.newshop.Controller;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.util.Strings;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.newshop.Entity.*;
import shop.newshop.Service.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Controller
@RequestMapping(value = "admin")
public class ContractController {

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

    //    @RequestMapping(value = "upload", method = RequestMethod.POST)
//    public String multiPartFile(RedirectAttributes map, @RequestParam("startDate") String startDate) {
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            Calendar c = Calendar.getInstance();
//            Date date = sdf.parse(startDate);
//            c.setTime(date);
//          Calendar.DAY_OF_WEEK = 7 => Thứ 7
//	        Calendar.DAY_OF_WEEK = 1 => Chủ nhật
//            System.out.println("Get Sunday: " + c.get(Calendar.DAY_OF_WEEK));
//          Lấy time hiện tại
//            LocalTime time = LocalTime.now();
//            System.out.println("Time: " + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return "redirect:/uploadStatus";
//    }

    @GetMapping(value = "test")
    public String test(){
        return "admin/testImage";
    }

    @RequestMapping(value = "listContract", method = RequestMethod.GET)
    public String getBonus(ModelMap map) {
        List<Contract> listContract = new ArrayList<>();
        listContract = contractService.getAlls();
        List<Employee> listEmployee = new ArrayList<>();
        listContract = contractService.getAlls();
        map.put("listContract", listContract);
        return "admin/Contract";
    }

    @RequestMapping(value = "listContract", method = RequestMethod.POST)
    public String getBonusPost(ModelMap map , @RequestParam("tennv") String nameEmployee) {
        List<Contract> listContract = new ArrayList<>();
        listContract = contractService.searchNameEmployee(nameEmployee);
        if(listContract.size() == 0){
            map.addAttribute("searchFail", "Không tìm thấy nhân viên là: " + nameEmployee);
        }
        map.put("listContract", listContract);
        map.addAttribute("nameSearch" ,nameEmployee);
        return "admin/Contract";
    }

    @GetMapping(value = "addContract")
    public String addContract(ModelMap model) {
        List<Employee> employees = new ArrayList<>();
        employees = employeeService.getAlls();
        model.addAttribute("employee", employees);
        return "admin/Addcontract";
    }

    @PostMapping(value = "addContract")
    public String insertContract(RedirectAttributes redirectAttributes, ModelMap model, @RequestParam("salary") String salary,
                                 @RequestParam("startday") String startday, @RequestParam("expirationday") String expirationday,
                                 @RequestParam("employee") int idEmployee) {
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
            if(contractgetByIdEmployee != null) {
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
            if (Strings.isEmpty(startday)) {
                model.put("errorstartday", "Ngày bắt đầu không được để trống");
                employees = employeeService.getAlls();
                model.addAttribute("employee", employees);
                model.addAttribute("salary", salary);
                return "admin/Addcontract";
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

    @GetMapping(value = "updateContract/{id}")
    public String updateContract(ModelMap model, @PathVariable("id") int id) {
        Contract contract = contractService.getContractById(id);
        model.addAttribute("contract", contract);
        return "admin/Editcontract";
    }

    @PostMapping(value = "updateContract")
    public String postUpdateContract(RedirectAttributes redirectAttributes, ModelMap model, @RequestParam("id") int id,
                                     @RequestParam("salary") String salary, @RequestParam("startday") String startday,
                                     @RequestParam("expirationday") String expirationday, @RequestParam("employee") int idEmployee) throws ParseException {
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
//        if(contractgetByIdEmployee != null) {
//            if (startday.compareTo(sdf.format(contractgetByIdEmployee.getExpirationday())) < 0) {
//                contract = contractService.getContractById(id);
//                model.put("contract", contract);
//                model.put("errorstartDay", "Ngày bắt đầu hợp đồng mới phải lớn hơn hợp đồng cũ");
//                return "admin/Editcontract";
//            }
//        }
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

    @GetMapping(value = "deleteContract/{id}")
    public String deleteContract(ModelMap model, @PathVariable("id") int id) {
        contractService.delete(id);
        return "redirect:/admin/listContract";
    }
}

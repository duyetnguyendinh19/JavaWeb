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

    private static String UPLOAD_FOLDER = "C:/Users/Bui Van Tan/Desktop/JavaWeb/src/main/resources/static/img/";

    @RequestMapping(value = "listContract", method = RequestMethod.GET)
    public String getBonus(ModelMap map) {
        List<Contract> listContract = new ArrayList<>();
        listContract = contractService.getAlls();
        map.put("listContract", listContract);
        return "admin/Contract";
    }

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
            Contract contract;
//            Contract contract = contractService.getContractById();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            if(Strings.isEmpty(salary)){
                model.put("errorSalary","Lương không được để trống");
                employees = employeeService.getAlls();
                model.addAttribute("employee", employees);
                return "admin/Addcontract";
//            }else if(sdf.parse(salary) == contract.getExpirationday()){
//                model.put("errorSalary","Ngày bắt đầu hợp đồng mới không hợp lệ");
//                employees = employeeService.getAlls();
//                model.addAttribute("employee", employees);
//                return "admin/Addcontract";
            }else if(Strings.isEmpty(startday)){
                model.put("errorstartday","Ngày bắt đầu không được để trống");
                employees = employeeService.getAlls();
                model.addAttribute("employee", employees);
                model.addAttribute("salary", salary);
                return "admin/Addcontract";
            }else if(Strings.isEmpty(expirationday)){
                model.put("errorexpirationday","Ngày kết thúc không được để trống");
                employees = employeeService.getAlls();
                model.addAttribute("salary", salary);
                model.addAttribute("employee", employees);
                return "admin/Addcontract";
            }else {
                contract = new Contract();
                Employee employee = new Employee();
                contract.setExpirationday(sdf.parse(expirationday));
                contract.setStartday(sdf.parse(startday));
                contract.setSalary(Float.parseFloat(salary.replaceAll(",", "")));
                employee.setId(idEmployee);
                contract.setEmployee(employee);
                contractService.insert(contract);
            }
        }catch (ParseException e){
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (salary.isEmpty()) {
            contract = contractService.getContractById(id);
            model.put("contract", contract);
            contract.setSalary(Float.parseFloat(""));
            model.put("errorSalary", "Lương không được để trống");
            return "admin/Editcontract";
        } else if (Strings.isEmpty(startday)) {
            contract = contractService.getContractById(id);
            contract.setStartday(null);
            model.put("contract", contract);
            model.put("errorstartDay", "Ngày bắt đầu không được để trống");
            return "admin/Editcontract";
        } else if (Strings.isEmpty(expirationday)) {
            contract = contractService.getContractById(id);
            contract.setExpirationday(null);
            model.put("contract", contract);
            model.put("errorexpirationday", "Ngày kết thúc không được để trống");
            return "admin/Editcontract";
        } else {
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
    }

    @GetMapping(value = "deleteContract/{id}")
    public String deleteContract(ModelMap model,@PathVariable("id") int id){
        contractService.delete(id);
        return "redirect:/admin/listContract";
    }
}

package shop.newshop.Controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.newshop.Entity.*;
import shop.newshop.Service.*;
import sun.java2d.pipe.SpanShapeRenderer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "admin")
public class DisciplineController {

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

    @RequestMapping(value = "listDiscipline", method = RequestMethod.GET)
    public String getDiscipline(ModelMap map) {
        List<Discipline> disciplineList = new ArrayList<>();
        disciplineList = disciplineService.getAlls();
        map.put("disciplineList", disciplineList);
        return "admin/Discipline";
    }

    @RequestMapping(value = "listDiscipline", method = RequestMethod.POST)
    public String getBonusPost(ModelMap map , @RequestParam("tennv") String nameEmployee) {
        List<Discipline> disciplineList = new ArrayList<>();
        disciplineList = disciplineService.searchNameEmployee(nameEmployee);
        if(disciplineList.size() == 0){
            map.addAttribute("searchFail", "Không tìm thấy nhân viên là: " + nameEmployee);
        }
        map.addAttribute("nameSearch" ,nameEmployee);
        map.put("disciplineList", disciplineList);
        return "admin/Discipline";
    }

    @GetMapping(value = "addDiscipline")
    public String addContract(ModelMap model) {
        List<Employee> employees = employeeService.getAlls();
        model.addAttribute("employee", employees);
        return "admin/AddDiscipline";
    }

    @PostMapping(value = "addDiscipline")
    public String insertContract(RedirectAttributes redirectAttributes, ModelMap model, @RequestParam("typeDiscipline") String typeDiscipline,
                                 @RequestParam("date") String date, @RequestParam("descent") String descent, @RequestParam("reason") String reason,
                                 @RequestParam("employeeId") int idEmployee) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Employee employee = new Employee();
            Discipline discipline = new Discipline();
            if (Strings.isEmpty(date)) {
                List<Employee> employees = employeeService.getAlls();
                model.addAttribute("idEmployee", idEmployee);
                model.addAttribute("employee", employees);
                model.addAttribute("errorDate", "Ngày kỷ luật không được để trống");
                return "admin/AddDiscipline";
            }
            if (Strings.isEmpty(reason)) {
                List<Employee> employees = employeeService.getAlls();
                model.addAttribute("idEmployee", idEmployee);
                model.addAttribute("employee", employees);
                model.addAttribute("errorReason", "Lý do kỷ luật không được để trống");
                return "admin/AddDiscipline";
            }
            discipline.setDate(sdf.parse(date));
            discipline.setDescent(descent);
            discipline.setReason(reason);
            employee.setId(idEmployee);
            discipline.setEmployee(employee);
            discipline.setType(typeDiscipline);
            disciplineService.insert(discipline);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/listDiscipline";
    }

    @GetMapping(value = "updateDiscipline/{id}")
    public String updateContract(ModelMap model, @PathVariable("id") int id) {
        Discipline discipline = disciplineService.getDisciplineById(id);
        model.addAttribute("discipline", discipline);
        return "admin/EditDiscipline";
    }

    @PostMapping(value = "updateDiscipline")
    public String postUpdateContract(RedirectAttributes redirectAttributes, ModelMap model, @RequestParam("id") int id,
                                     @RequestParam("typeDiscipline") String typeDiscipline, @RequestParam("date") String date,
                                     @RequestParam("descent") String descent, @RequestParam("reason") String reason,
                                     @RequestParam("employee") int idEmployee) throws ParseException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Employee employee = new Employee();
            Discipline discipline = new Discipline();
            if (Strings.isEmpty(date)) {
                List<Employee> employees = employeeService.getAlls();
                Discipline discipline1 = disciplineService.getDisciplineById(id);
                discipline1.setDate(null);
                model.addAttribute("discipline", discipline1);
                model.addAttribute("employee", employees);
                model.addAttribute("errorDate", "Ngày kỷ luật không được để trống");
                return "admin/EditDiscipline";
            }
            if (Strings.isEmpty(reason)) {
                List<Employee> employees = employeeService.getAlls();
                Discipline discipline1 = disciplineService.getDisciplineById(id);
                discipline1.setReason(null);
                model.addAttribute("discipline", discipline1);
                model.addAttribute("employee", employees);
                model.addAttribute("errorReason", "Lý do kỷ luật không được để trống");
                return "admin/EditDiscipline";
            }
            employee.setId(idEmployee);
            discipline.setId(id);
            discipline.setType(typeDiscipline);
            discipline.setEmployee(employee);
            discipline.setReason(reason);
            discipline.setDescent(descent);
            discipline.setDate(sdf.parse(date));
            disciplineService.update(discipline);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return "redirect:/admin/listDiscipline";
    }

    @GetMapping(value = "deleteDiscipline/{id}")
    public String deleteDiscipline(ModelMap model, @PathVariable("id") int id) {
        disciplineService.delete(id);
        return "redirect:/admin/listDiscipline";
    }
}

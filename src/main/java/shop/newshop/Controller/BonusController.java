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
public class BonusController {

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

    @RequestMapping(value = "listBonus", method = RequestMethod.GET)
    public String getBonus(ModelMap map) {
        List<Bonus> bonusList = bonusService.getAlls();
        map.put("bonusList", bonusList);
        return "admin/Bonus";
    }

    @RequestMapping(value = "listBonus", method = RequestMethod.POST)
    public String getBonusPost(ModelMap map , @RequestParam("tennv") String nameEmployee) {
        List<Bonus> bonusList = new ArrayList<>();
        bonusList = bonusService.searchNameEmployee(nameEmployee);
        if(bonusList.size() == 0){
            map.addAttribute("searchFail", "Không tìm thấy nhân viên là: " + nameEmployee);
        }
        map.addAttribute("nameSearch" ,nameEmployee);
        map.put("bonusList", bonusList);
        return "admin/Bonus";
    }

    @GetMapping(value = "addBonus")
    public String addContract(ModelMap model) {
        List<Employee> employees = employeeService.getAlls();
        model.addAttribute("employee", employees);
        return "admin/AddBonus";
    }

    @PostMapping(value = "addBonus")
    public String insertContract(RedirectAttributes redirectAttributes, ModelMap model, @RequestParam("typeBonus") String typeBonus,
                                 @RequestParam("date") String date, @RequestParam("descent") String descent,@RequestParam("reason") String reason,
                                 @RequestParam("employeeId") int idEmployee) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Employee employee = new Employee();
            Bonus bonus = new Bonus();
            if(Strings.isEmpty(typeBonus)){
                List<Employee> employees = employeeService.getAlls();
                model.addAttribute("idEmployee", idEmployee);
                model.addAttribute("employee", employees);
                model.addAttribute("errorType", "Vui lòng chọn kiểu khen thưởng");
                return "admin/AddBonus";
            }
            if(Strings.isEmpty(date)){
                List<Employee> employees = employeeService.getAlls();
                model.addAttribute("idEmployee", idEmployee);
                model.addAttribute("typeBonus", typeBonus);
                model.addAttribute("employee", employees);
                model.addAttribute("errorDate", "Ngày khen thưởng không được để trống");
                return "admin/AddBonus";
            }
            if(Strings.isEmpty(reason)){
                List<Employee> employees = employeeService.getAlls();
                model.addAttribute("idEmployee", idEmployee);
                model.addAttribute("typeBonus", typeBonus);
                model.addAttribute("employee", employees);
                model.addAttribute("errorReason", "Lý do khen thưởng không được để trống");
                return "admin/AddBonus";
            }
            bonus.setDate(sdf.parse(date));
            bonus.setDescent(descent);
            bonus.setReason(reason);
            employee.setId(idEmployee);
            bonus.setEmployee(employee);
            bonus.setType(typeBonus);
            bonusService.insert(bonus);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/listBonus";
    }

    @GetMapping(value = "updateBonus/{id}")
    public String updateBonus(ModelMap model, @PathVariable("id") int id) {
        Bonus bonus = bonusService.getBonusById(id);
        model.addAttribute("bonus", bonus);
        return "admin/EditBonus";
    }

    @PostMapping(value = "updateBonus")
    public String postUpdateContract(RedirectAttributes redirectAttributes, ModelMap model, @RequestParam("typeBonus") String typeBonus,
                                     @RequestParam("date") String date, @RequestParam("descent") String descent,@RequestParam("reason") String reason,
                                     @RequestParam("employee") int idEmployee, @RequestParam("id") int id) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Employee employee = new Employee();
            Bonus bonus = new Bonus();
            if(Strings.isEmpty(typeBonus)){
                bonus = bonusService.getBonusById(id);
                model.addAttribute("bonus", bonus);
                model.addAttribute("idEmployee", idEmployee);
                model.addAttribute("errorType", "Vui lòng chọn kiểu khen thưởng");
                return "admin/EditBonus";
            }
            if(Strings.isEmpty(date)){
                bonus = bonusService.getBonusById(id);
                bonus.setDate(null);
                model.addAttribute("bonus", bonus);
                model.addAttribute("idEmployee", idEmployee);
                model.addAttribute("typeBonus", typeBonus);
                model.addAttribute("errorDate", "Ngày khen thưởng không được để trống");
                return "admin/EditBonus";
            }
            if(Strings.isEmpty(reason)){
                bonus = bonusService.getBonusById(id);
                bonus.setReason(null);
                model.addAttribute("bonus", bonus);
                model.addAttribute("idEmployee", idEmployee);
                model.addAttribute("typeBonus", typeBonus);
                model.addAttribute("errorReason", "Lý do khen thưởng không được để trống");
                return "admin/EditBonus";
            }
            bonus.setId(id);
            bonus.setDate(sdf.parse(date));
            bonus.setDescent(descent);
            bonus.setReason(reason);
            employee.setId(idEmployee);
            bonus.setEmployee(employee);
            bonus.setType(typeBonus);
            bonusService.update(bonus);
        }catch (ParseException pe){
            pe.printStackTrace();
        }
        return "redirect:/admin/listBonus";
    }

    @GetMapping(value = "deleteBonus/{id}")
    public String deleteDiscipline(ModelMap model, @PathVariable("id") int id) {
        bonusService.delete(id);
        return "redirect:/admin/listBonus";
    }
}

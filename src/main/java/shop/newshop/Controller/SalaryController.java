package shop.newshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import shop.newshop.Entity.Salary;
import shop.newshop.Service.SalaryService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "admin")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @GetMapping(value = "listSalary")
    public String getSalary(ModelMap model){
        List<Salary> salaryList = salaryService.getAlls();
        model.addAttribute("listSalary", salaryList);
        return "admin/Salary";
    }

//    @RequestMapping(value = "listSalary", method = RequestMethod.POST)
//    public String getBonusPost(ModelMap map , @RequestParam("tennv") String nameEmployee) {
//        List<Salary> salaryList = new ArrayList<>();
//        salaryList = salaryService.searchByName(nameEmployee);
//        if(salaryList.size() == 0){
//            map.addAttribute("searchFail", "Không tìm thấy lương nhân viên là: " + nameEmployee);
//        }
//        map.addAttribute("nameSearch" ,nameEmployee);
//        map.put("bonusList", salaryList);
//        return "admin/Salary";
//    }

}

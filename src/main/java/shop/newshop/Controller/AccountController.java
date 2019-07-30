package shop.newshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import shop.newshop.Entity.Account;
import shop.newshop.Service.AccountService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "admin")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("listAccount")
    public String listAccount(ModelMap model){
        List<Account> accountList = accountService.getAlls();
        model.addAttribute("listAccount",accountList);
        return "admin/user";
    }

    @RequestMapping(value = "listAccount", method = RequestMethod.POST)
    public String getBonusPost(ModelMap map , @RequestParam("user") String user) {
        List<Account> accountList = new ArrayList<>();
        accountList = accountService.searchByUser(user);
        if(accountList.size() == 0){
            map.addAttribute("searchFail", "Không tìm thấy tài khoản là: " + user);
        }
        map.addAttribute("nameSearch" ,user);
        map.put("listAccount", accountList);
        return "admin/user";
    }
}

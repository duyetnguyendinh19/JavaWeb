package shop.newshop.Controller;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
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

    private static String UPLOAD_FOLDER = "C:/Users/Bui Van Tan/Desktop/JavaWeb/src/main/resources/static/img/";

    @RequestMapping(value = "listBonus", method = RequestMethod.GET)
    public String getBonus(ModelMap map) {
//		List<Employee> listContract = new ArrayList<>();
//		listContract =  employeeService.getAlls();
//		for(Employee x : listContract){
//			System.out.println("Contract: " + x.getPhone());
//		}
//		map.put("lstBonus", bonusService.getAlls());
        return "admin/index";
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String multiPartFile(RedirectAttributes map, @RequestParam("startDate") String startDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c = Calendar.getInstance();
            Date date = sdf.parse(startDate);
            c.setTime(date);
//          Calendar.DAY_OF_WEEK = 7 => Thứ 7
//	        Calendar.DAY_OF_WEEK = 1 => Chủ nhật
            System.out.println("Get Sunday: " + c.get(Calendar.DAY_OF_WEEK));
//          Lấy time hiện tại
            LocalTime time = LocalTime.now();
            System.out.println("Time: " + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "redirect:/uploadStatus";
    }

    @GetMapping(value = "uploadStatus")
    public String updateStatus() {
        return "admin/testResultUpload";
    }

}

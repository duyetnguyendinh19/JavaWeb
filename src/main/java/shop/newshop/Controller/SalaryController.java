package shop.newshop.Controller;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.newshop.Entity.Salary;
import shop.newshop.Service.SalaryService;
import shop.newshop.Service.TotalAttendanceService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "admin")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @Autowired
    private TotalAttendanceService totalAttendanceService;

    @GetMapping(value = "listSalary")
    public String getSalary(ModelMap model) {
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

    @PostMapping("addSalary")
    public String addSalary(ModelMap model) {
        try {
            Date date = new Date();
            List<Object[]> object = totalAttendanceService.listTotal(date.getMonth() + 1, 1900 + date.getYear());
            List<Salary> list = new ArrayList<>();
            for (Object[] x : object) {
                Salary salary = new Salary();
                salary.setIdEmployee(Integer.parseInt(String.valueOf(x[0])));
                salary.setNameEmployee(String.valueOf(x[1]));
                salary.setMonth(Integer.parseInt(String.valueOf(x[3])));
                salary.setCount(Double.parseDouble(String.valueOf(x[4])));
                salary.setDayoff(Double.parseDouble(String.valueOf(x[5])));
                salary.setTotal(Double.parseDouble((String.valueOf(x[6]))));
                salaryService.insert(salary);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:/admin/listSalary";
    }

    @GetMapping("writeExcel")
    public String writeExcel(RedirectAttributes redirectAttributes)  {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            Date date = new Date();
            HSSFSheet sheet = workbook.createSheet("Tháng " + String.valueOf(date.getMonth() + 1));

            List<Salary> list = salaryService.getAlls();

            int rownum = 0;
            Cell cell;
            Row row;

            HSSFCellStyle style = createStyleForTitle(workbook);

            row = sheet.createRow(rownum);


            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Mã NV");
            cell.setCellStyle(style);

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Tên NV");
            cell.setCellStyle(style);

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tháng");
            cell.setCellStyle(style);

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Số công");
            cell.setCellStyle(style);

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Số ngày nghỉ");
            cell.setCellStyle(style);

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Lương");
            cell.setCellStyle(style);

//            cell = row.createCell(6, CellType.STRING);
//            cell.setCellValue("Thống kê lương tháng");
//            cell.setCellStyle(style);

            for (Salary salary : list) {
                rownum++;
                row = sheet.createRow(rownum);


                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(salary.getIdEmployee());

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(salary.getNameEmployee());

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(salary.getMonth());

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(salary.getCount());

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(salary.getDayoff());

                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(salary.getTotal());

//            String formula = "0.1*C" + (rownum + 1) + "*D" + (rownum + 1);
//            cell = row.createCell(5, CellType.FORMULA);
//            cell.setCellFormula(formula);
            }
            File file = new File("C:/qlnv_excel/salary.xls");
            file.getParentFile().mkdirs();

            FileOutputStream outFile = new FileOutputStream(file);
            workbook.write(outFile);
            redirectAttributes.addFlashAttribute("alertWriteExcel", "Xuất excel thành công");
        }catch (IOException io){
            redirectAttributes.addFlashAttribute("alertWriteExcel", "Xuất excel không thành công");
            io.printStackTrace();
        }
        return "redirect:/admin/listSalary";
    }
    private HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

}

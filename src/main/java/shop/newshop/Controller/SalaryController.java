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

	private String name;
	private int month;
	private int year;

	@PostMapping(value = "listSalary")
	public String listSalaryearch(ModelMap model, @RequestParam("nameEmployee") String nameSearch,
			@RequestParam("month") int monthSearch, @RequestParam("year") String yearSearch) {
		name = nameSearch;
		month = monthSearch;
		if(yearSearch != "" && !yearSearch.isEmpty()){
            year = Integer.parseInt((yearSearch));
        }else{
		    year = 0;
        }
		model.put("error", "");
		if(yearSearch != "" && !yearSearch.isEmpty()){
            model.put("listSalary", salaryService.getLimit(0, 5, nameSearch, monthSearch, year));
        }else{
            model.put("listSalary", salaryService.getLimit(0, 5, nameSearch, monthSearch, year));
        }
		long countAll = salaryService.countAll(nameSearch, monthSearch, year);
		long totalPage = 0;

		if (countAll == 0) {
			totalPage = 1;
			model.addAttribute("searchFail", "Không tìm thấy dữ liệu");
		} else {
			if (countAll % 5 == 0) {
				totalPage = countAll / 5;
			} else {
				totalPage = countAll / 5 + 1;
			}
		}
		model.put("totalPage", totalPage);
		model.put("totalSalary", countAll);
		model.put("firstSalary", 1);
		if (countAll < 5) {
			model.put("lastSalary", countAll);
		} else {
			model.put("lastSalary", 5);
		}
		model.put("nameSearch", nameSearch);
		model.put("month", monthSearch);
        model.put("year", yearSearch);
		return "admin/Salary";
	}

	@GetMapping(value = "listSalary")
	public String listEmployee(ModelMap model) {
	    Date date = new Date();
		model.put("error", "");
		model.put("listSalary", salaryService.getLimit(0, 5, null, 0, 0));
		long countAll = salaryService.countAll(null, 0,0);
		long totalPage = 0;

		if (countAll == 0) {
			totalPage = 1;
		} else {
			if (countAll % 5 == 0) {
				totalPage = countAll / 5;
			} else {
				totalPage = countAll / 5 + 1;
			}
		}

		model.put("totalPage", totalPage);
		model.put("totalSalary", countAll);
		model.put("firstSalary", 1);
		if (countAll < 5) {
			model.put("lastSalary", countAll);
		} else {
			model.put("lastSalary", 5);
		}
		model.put("nameSearch", null);
		model.put("month", 0);
		return "admin/Salary";
	}

	@GetMapping(value = "listSalary/{page}")
	public String listEmployeePage(ModelMap model, @PathVariable("page") int page) {
		model.put("error", "");
		model.put("listSalary", salaryService.getLimit((page - 1) * 5, 5, name, month, year));

		long countAll = salaryService.countAll(name, month, year);
		long totalPage = 0;

		if (countAll == 0) {
			totalPage = 1;
		} else {
			if (countAll % 5 == 0) {
				totalPage = countAll / 5;
			} else {
				totalPage = countAll / 5 + 1;
			}
		}

		model.put("totalPage", totalPage);
		model.put("totalSalary", countAll);
		model.put("firstSalary", (page - 1) * 5 + 1);
		if (page < totalPage) {
			model.put("lastSalary", (page - 1) * 5 + 5);
		} else {
			model.put("lastSalary", countAll);
		}

		return "admin/Salary";
	}

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
				salary.setYear(date.getYear() + 1900);
				salary.setCreateDate(date);
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
            cell.setCellValue("Năm");
            cell.setCellStyle(style);

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Số công");
            cell.setCellStyle(style);

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Số ngày nghỉ");
            cell.setCellStyle(style);

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Lương");
            cell.setCellStyle(style);

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Ngày tạo");
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

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(salary.getYear());

                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(salary.getCount());

                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue(salary.getDayoff());

                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue(salary.getTotal());

                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue(salary.getCreateDate());

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

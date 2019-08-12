package shop.newshop.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.newshop.DAO.AttendanceDao;
import shop.newshop.Entity.Attendance;
import shop.newshop.Entity.Employee;
import shop.newshop.Service.AttendanceService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceDao attendanceDao;

	@Override
	public List<Attendance> getAll() {
		return attendanceDao.getAll();
	}

	@Override
	public boolean insert(Attendance attendance) {
		return attendanceDao.insert(attendance);
	}

	@Override
	public boolean update(Attendance attendance) {
		return attendanceDao.update(attendance);
	}

	@Override
	public boolean delete(int idAttendance) {
		return false;
	}

	@Override
	public Attendance getBonusById(int idAttendance) {
		return attendanceDao.getBonusById(idAttendance);
	}

	@Override
	public List<Attendance> searchNameEmployee(String nameEmployee) {
		return attendanceDao.searchNameEmployee(nameEmployee);
	}

	@Override
	public long countAttenByEmployeId(int id, int month) {
		return attendanceDao.countAttenByEmployeId(id, month);
	}

	@Override
	public List<Attendance> getAttenDanceByDateAndId(int id, Date date) {
		return attendanceDao.getAttenDanceByDateAndId(id, date);
	}

	@Override
	public List<Attendance> getLimit(int num, int row, String name, String date) {
		SimpleDateFormat sdfParse = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date dateParse = null;
		String dateFormat = null;
		try {
			if (date != null && !date.isEmpty()) {
				dateParse = sdfParse.parse(date);
				dateFormat = sdfFormat.format(dateParse);
			} 
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return attendanceDao.getLimit(num, row, name, dateFormat);
	}

	@Override
	public long countAll(String name, String date) {
		SimpleDateFormat sdfParse = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date dateParse = null;
		String dateFormat = null;
		try {
			if (date != null && !date.isEmpty() ) {
				dateParse = sdfParse.parse(date);
				dateFormat = sdfFormat.format(dateParse);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return attendanceDao.countAll(name, dateFormat);
	}

	@Override
	public List<Attendance> getAttendanceByEmployee(Employee employee) {
		return attendanceDao.getAttendanceByEmployee(employee);
	}

}

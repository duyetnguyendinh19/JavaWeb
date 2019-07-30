package shop.newshop.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.newshop.DAO.AttendanceDao;
import shop.newshop.Entity.Attendance;
import shop.newshop.Service.AttendanceService;

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
}

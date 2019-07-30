package shop.newshop.DAO;

import shop.newshop.Entity.Attendance;

import java.util.List;

public interface AttendanceDao {

    public List<Attendance> getAll();

    public boolean insert(Attendance attendance);

    public boolean update(Attendance attendance);

    public boolean delete(int idAttendance);

    public Attendance getBonusById(int idAttendance);

    public List<Attendance> searchNameEmployee(String nameEmployee);
}

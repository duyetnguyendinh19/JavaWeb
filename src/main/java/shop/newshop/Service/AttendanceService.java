package shop.newshop.Service;

import shop.newshop.Entity.Attendance;

import java.util.Date;
import java.util.List;

public interface AttendanceService {

    public List<Attendance> getAll();

    public boolean insert(Attendance attendance);

    public boolean update(Attendance attendance);

    public boolean delete(int idAttendance);

    public Attendance getBonusById(int idAttendance);

    public List<Attendance> searchNameEmployee(String nameEmployee);

    public long countAttenByEmployeId(int id, int month);

    public List<Attendance> getAttenDanceByDateAndId(int id, Date date);

}

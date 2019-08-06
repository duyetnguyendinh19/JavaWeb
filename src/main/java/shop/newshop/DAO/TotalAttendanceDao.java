package shop.newshop.DAO;

import shop.newshop.Entity.TotalAttendance;

import java.util.List;

public interface TotalAttendanceDao {

    public List<Object[]> listTotal(int month, int year);

}

package shop.newshop.DAO;


import java.util.List;

public interface TotalAttendanceDao {

    public List<Object[]> listTotal(int month, int year);

}

package shop.newshop.Service;

import shop.newshop.Entity.TotalAttendance;

import java.util.List;

public interface TotalAttendanceService {

    public List<Object[]> listTotal(int month, int year);
}

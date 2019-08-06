package shop.newshop.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.newshop.DAO.TotalAttendanceDao;
import shop.newshop.Entity.TotalAttendance;
import shop.newshop.Service.TotalAttendanceService;

import java.util.List;

@Service
public class TotalAttendanceServiceImpl implements TotalAttendanceService {

    @Autowired
    private TotalAttendanceDao totalAttendanceDao;

    @Override
    public List<Object[]> listTotal(int month, int year) {
        return totalAttendanceDao.listTotal(month, year);
    }
}

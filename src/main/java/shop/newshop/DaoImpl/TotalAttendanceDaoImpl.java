package shop.newshop.DaoImpl;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import shop.newshop.DAO.TotalAttendanceDao;
import shop.newshop.Entity.Account;
import shop.newshop.Entity.TotalAttendance;
import shop.newshop.util.HibernateUtils;

import javax.persistence.Query;
import java.util.List;

@Repository
public class TotalAttendanceDaoImpl implements TotalAttendanceDao {

    @Override
    public List<Object[]> listTotal(int month, int year) {
        List<Object[]> list = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        try {
            String sql = "select em.id idEmployee, em.name nameEmployee, ct.salary, :month month,\n" +
                    "\t tb.dw_count count, 26 - tb.dw_count day_off, ROUND(ct.salary/26 * tb.dw_count, 0) totalSalary\n" +
                    "from (   \n" +
                    "\tselect day_work.id id,    \n" +
                    "           sum(day_work.count_in_day) dw_count\n" +
                    "\tfrom (   \n" +
                    "        select atten.employee_id as id,   \n" +
                    "\t\tcase when DAYOFWEEK(atten.date) = '1' then 2\n" +
                    "        when atten.starttime < Time('12:00:00') and      \n" +
                    "\t\tatten.endtime > Time('12:00:00') then 1     \n" +
                    "\t\twhen atten.starttime < Time('12:00:00') and      \n" +
                    "\t\tatten.endtime < Time('12:00:00') then 0.5     \n" +
                    "\t\twhen atten.starttime > Time('12:00:00') and     \n" +
                    "\t\tatten.endtime > Time('12:00:00') then 0.5     \n" +
                    "\t\telse 0 end as count_in_day\n" +
                    "\t   from Attendance atten   \n" +
                    "\t   where  month(atten.date) = :month and  year(atten.date) = :year  \n" +
                    "\t   ) day_work \n" +
                    "\t\tgroup by day_work.id     \n" +
                    "\t\t\t) tb join  Employee em on tb.id = em.id\n" +
                    "             join Contract ct on em.id = ct.idEmployee\n";
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("month", month);
            query.setParameter("year", year);
            list = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
}

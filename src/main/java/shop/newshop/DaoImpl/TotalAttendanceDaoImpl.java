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
            String sql = "select au.id idEmployee, au.name nameEmployee, maxSalary.salary , :month month,\n" +
                    "\t tb.dw_count count, 26 - tb.dw_count day_off, ROUND(maxSalary.salary/26 * tb.dw_count, 0) totalSalary\n" +
                    "from (\n" +
                    "\tselect day_work.id id,    \n" +
                    "           sum(day_work.count_in_day) dw_count\n" +
                    "\tfrom (   \n" +
                    "        select tk.employee_id as id,   \n" +
                    "\t\tcase when DAYOFWEEK(tk.date) = '1' then 2\n" +
                    "        when tk.starttime < Time('8:30:00') and      \n" +
                    "\t\ttk.endtime > Time('17:00:00') then 1     \n" +
                    "\t\twhen tk.starttime < Time('8:30:00') and      \n" +
                    "\t\ttk.endtime > Time('12:00:00') then 0.5     \n" +
                    "\t\twhen tk.starttime > Time('13:00:00') and     \n" +
                    "\t\ttk.endtime > Time('17:00:00') then 0.5     \n" +
                    "\t\telse 0 end as count_in_day\n" +
                    "\t   from `qlnv`.`attendance` tk   \n" +
                    "\t   where  month(tk.date) = :month and  year(tk.date) = :year  \n" +
                    "\t   ) day_work \n" +
                    "\t\tgroup by day_work.id   \n" +
                    "\t\t\t) tb join  `qlnv`.`employee` au on tb.id = au.id\n" +
                    "             join (\n" +
                    "\t\t\t\tselect max(salary) salary,idEmployee idEmployee from contract ct\n" +
                    "\t\t\t\t\tgroup by ct.idEmployee \n" +
                    "             ) maxSalary on au.id = maxSalary.idEmployee\n";
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

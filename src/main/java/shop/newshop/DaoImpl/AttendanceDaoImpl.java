package shop.newshop.DaoImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import shop.newshop.DAO.AttendanceDao;
import shop.newshop.Entity.Account;
import shop.newshop.Entity.Attendance;
import shop.newshop.util.HibernateUtils;

import java.util.List;

@Repository
public class AttendanceDaoImpl implements AttendanceDao {
    @Override
    public List<Attendance> getAll() {
        List<Attendance> list = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        try {
            String sql = "FROM Attendance";
            session.beginTransaction();
            Query query = session.createQuery(sql);
            list = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public boolean insert(Attendance attendance) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.save(attendance);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Attendance attendance) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.update(attendance);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public boolean delete(int idAttendance) {
        Attendance attendance = getBonusById(idAttendance);
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.delete(attendance);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Attendance getBonusById(int idAttendance) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Attendance attendance = (Attendance) session.get(Attendance.class, idAttendance);
        session.close();
        return attendance;
    }

    @Override
    public List<Attendance> searchNameEmployee(String nameEmployee) {
        List<Attendance> list = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        try {
            String sql = "FROM Attendance c WHERE c.employee.name LIKE '%" + nameEmployee + "%'";
            session.beginTransaction();
            Query query = session.createQuery(sql);
            list = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
}

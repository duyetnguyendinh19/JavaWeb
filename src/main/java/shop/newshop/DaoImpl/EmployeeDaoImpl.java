package shop.newshop.DaoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import shop.newshop.DAO.EmployeeDao;
import shop.newshop.Entity.Contract;
import shop.newshop.Entity.Employee;
import shop.newshop.ServiceImpl.EmployeeServiceImpl;
import shop.newshop.util.HibernateUtils;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public List<Employee> getAlls() {
        List<Employee> list = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        try {
            String sql = "FROM Employee";
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
    public boolean insert(Employee emp) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.save(emp);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Employee emp) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.update(emp);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean delete(int idEmployee) {
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        Employee employee = employeeService.getEmployeeById(idEmployee);
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.delete(employee);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Employee getEmployeeById(int idEmployee) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Employee employee = (Employee) session.get(Employee.class, idEmployee);
        session.close();
        return employee;
    }

}

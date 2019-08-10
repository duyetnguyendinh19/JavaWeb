package shop.newshop.DaoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import shop.newshop.DAO.EmployeeDao;
import shop.newshop.Entity.Account;
import shop.newshop.Entity.Employee;
import shop.newshop.util.HibernateUtils;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@SuppressWarnings("unchecked")
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
	public boolean insert(Employee emp, Account account) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(account);
			session.flush();
			emp.setAccount(account);
			;
			session.save(emp);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
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
		}
		return false;
	}

	@Override
	public boolean delete(int idEmployee) {
		Employee employee = getEmployeeById(idEmployee);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getLimit(int num, int row, String name) {
		List<Employee> list = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			String sql = "FROM Employee Where 1=1";
			String hqlWhere = " ";
			if (name != null && !name.isEmpty()) {
				hqlWhere += "AND LOWER(name) LIKE '%" + name + "%'";
			}
			Query query = session.createQuery(sql + hqlWhere);
			query.setFirstResult(num);
			query.setMaxResults(row);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public long countAll(String name) {
		long result = 0;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			String hql = "SELECT COUNT(*) FROM Employee Where 1=1";
			String hqlWhere = " ";
			if (name != null && !name.isEmpty()) {
				hqlWhere += " AND LOWER(name) LIKE '%" + name + "%'";
			}
			Query query = session.createQuery(hql + hqlWhere);
			result = (long) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public boolean checkEmail(String email) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			String Hql = "FROM Employee WHERE email=:email";
			Query query = session.createQuery(Hql);
			query.setParameter("email", email);
			if (query.list() != null && !query.list().isEmpty()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public long check(String email, String phone, String username, String card, int id) {
		long result = 0;
		Session session = HibernateUtils.getSessionFactory().openSession();

		try {

			if ((long) (session.createQuery("Select Count(*) From Employee Where email=:email AND id!=:id")
					.setParameter("email", email).setParameter("id", id).uniqueResult()) > 0) {
				result = 1;
			} else if ((long) (session.createQuery("Select Count(*) From Employee Where phone=:phone AND id!=:id")
					.setParameter("phone", phone).setParameter("id", id).uniqueResult()) > 0) {
				result = 2;
			} else if ((long) (session
					.createQuery("Select Count(*) From Employee Where identitycard=:identitycard AND id!=:id")
					.setParameter("identitycard", card).setParameter("id", id).uniqueResult()) > 0) {
				result = 3;
			} else if ((long) (session
					.createQuery("Select Count(*) From Employee e Join e.account ac Where ac.username=:username AND e.id!=:id")
					.setParameter("username", username).setParameter("id", id).uniqueResult()) > 0) {
				result = 4;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}

}

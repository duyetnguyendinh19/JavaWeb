package shop.newshop.DaoImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import shop.newshop.DAO.SalaryDao;
import shop.newshop.Entity.Salary;
import shop.newshop.util.HibernateUtils;

import java.util.List;

@Repository
public class SalaryDaoImpl implements SalaryDao {
	@Override
	public List<Salary> getAlls() {
		List<Salary> list = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			String sql = "FROM Salary";
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
	public boolean insert(Salary salary) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(salary);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Salary salary) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(salary);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		}
		return false;
	}

	@Override
	public boolean delete(int idSalary) {
		Salary salary = getSalaryById(idSalary);
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(salary);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Salary getSalaryById(int idSalary) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Salary salary = (Salary) session.get(Salary.class, idSalary);
		session.close();
		return salary;
	}

	@Override
	public List<Salary> searchByName(String name) {
		List<Salary> list = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			String sql = "FROM Salary s WHERE s.nameEmployee LIKE '%" + name + "%'";
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
	public List<Salary> getLimit(int num, int row, String name, int month, int year) {
		List<Salary> list = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {

			session.beginTransaction();
			String sql = "FROM Salary WHERE 1=1";
			String hqlWhere = " ";
			String hqlYear = " ";
			if (name != null) {
				hqlWhere += " AND LOWER(nameEmployee) LIKE '%" + name + "%'";
			}
			if (month > 0) {
				hqlWhere += " AND month = " + month;
			}
			if(year > 0){
			    hqlYear += "AND year = " + year;
            }
			Query query = session.createQuery(sql + hqlWhere + hqlYear);

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
	public long countAll(String name, int month, int year) {
		long result = 0;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			String hql = "SELECT COUNT(*) FROM Salary Where 1=1";
			String hqlWhere = " ";
			String hqlYear = " ";
			if (name != null && !name.isEmpty()) {
				hqlWhere += " AND LOWER(nameEmployee) LIKE '%" + name + "%'";
			}
			if (month > 0) {
				hqlWhere += " AND month = " + month;
			}
            if(year > 0){
                hqlYear += "AND year = " + year;
            }
			Query query = session.createQuery(hql + hqlWhere + hqlYear);
			result = (long) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public List<Salary> getSalaryByEmployee(int idEmployee) {
		List<Salary> list = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			String sql = "FROM Salary Where idEmployee = :idEmployee";
			session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("idEmployee", idEmployee);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
}

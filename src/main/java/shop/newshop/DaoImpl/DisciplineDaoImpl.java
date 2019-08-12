package shop.newshop.DaoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import shop.newshop.DAO.DisciplineDao;
import shop.newshop.Entity.Discipline;
import shop.newshop.Entity.Employee;
import shop.newshop.util.HibernateUtils;

@Repository
public class DisciplineDaoImpl implements DisciplineDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Discipline> getAlls() {
		List<Discipline> list = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			String sql = "FROM Discipline";
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
	public boolean insert(Discipline di) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(di);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Discipline di) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(di);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println(e);
		}
		return false;
	}

	@Override
	public boolean delete(int idDiscipline) {
		Discipline discipline = getDisciplineById(idDiscipline);
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(discipline);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Discipline getDisciplineById(int idDiscipline) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Discipline discipline = (Discipline) session.get(Discipline.class, idDiscipline);
		session.close();
		return discipline;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Discipline> getLimit(int num, int row, String name) {
		List<Discipline> list = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			String sql = "FROM Discipline Where 1=1";
			String hqlWhere = " ";
			if (name != null && !name.isEmpty()) {
				hqlWhere += "AND LOWER(employee.name) LIKE '%" + name + "%'";
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
			String hql = "SELECT COUNT(*) FROM Discipline Where 1=1";
			String hqlWhere = " ";
			if (name != null && !name.isEmpty()) {
				hqlWhere += " AND LOWER(employee.name) LIKE '%" + name + "%'";
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Discipline> getDisciplinesByEmployee(Employee employee) {
		List<Discipline> list = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			String sql = "FROM Discipline Where employee = :employee";
			session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("employee", employee);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

}

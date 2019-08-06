package shop.newshop.DaoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import shop.newshop.DAO.DepartmentDao;
import shop.newshop.Entity.Department;
import shop.newshop.util.HibernateUtils;


@Repository
public class DepartmentDaoImpl implements DepartmentDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getAlls(int startnum, int rownum, String nameDepart) {
		List<Department> list = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			String sql = "FROM Department Where 1=1";
			String hqlWhere = " ";
			if(nameDepart!=null && !nameDepart.isEmpty()) {
				hqlWhere += "AND LOWER(name) LIKE '%"+nameDepart+"%'";
			}
			Query query = session.createQuery(sql+hqlWhere);
			query.setFirstResult(startnum);
			query.setMaxResults(rownum);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}

	@Override
	public boolean insert(Department de) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(de);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Department de) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(de);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println(e);
		}
		return false;
	}

	@Override
	public boolean delete(int idDepart) {
		Department department = getDepartById(idDepart);
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(department);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Department getDepartById(int idDepart) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Department department = (Department) session.get(Department.class, idDepart);
		session.close();
		return department;
	}

	@Override
	public long countAll(String nameDepart) {
		long result = 0;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			String hql = "SELECT COUNT(*) FROM Department Where 1=1";
			String hqlWhere = " ";
			if(nameDepart!=null && !nameDepart.isEmpty()) {
				hqlWhere += " AND LOWER(name) LIKE '%"+nameDepart+"%'";
			}
			Query query = session.createQuery(hql+hqlWhere);
			result = (long) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getAlls() {
		List<Department> list = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			String sql = "FROM Department";
			Query query = session.createQuery(sql);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}

	@Override
	public long checkName(String nameDepart, int idDepart) {
		long result = 0 ;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			String hql = "Select Count(*) From Department Where LOWER(name) = '" + nameDepart + "' ";
			String hqlWhere = " ";

			if(idDepart!=0) {
				hqlWhere += " AND id != " + idDepart;
			}

			Query query = session.createQuery(hql+hqlWhere);

			result = (long) query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return result;
	}

}

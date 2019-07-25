package shop.newshop.DaoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import shop.newshop.DAO.DepartmentDao;
import shop.newshop.Entity.Department;
import shop.newshop.ServiceImpl.DepartmentServiceImpl;
import shop.newshop.util.HibernateUtils;

@Repository
public class DepartmentDaoImpl implements DepartmentDao{

	@Override
	public List<Department> getAlls() {
		List<Department> list = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			String sql = "FROM Department";
			session.beginTransaction();
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

}

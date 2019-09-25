package shop.newshop.DaoImpl;

import org.hibernate.Session;

import shop.newshop.DAO.BaseDao;
import shop.newshop.util.HibernateUtils;

public class BaseDaoImpl implements BaseDao{

	@Override
	public boolean insert(Object object) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(object);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Object object) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(object);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean delete(Class clas,int id) {
		Object object = getById(clas, id);
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(object);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getById(Class clas,int id) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Object object = session.get(clas, id);
		session.close();
		return object;
	}

}

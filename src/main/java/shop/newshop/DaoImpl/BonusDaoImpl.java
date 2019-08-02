package shop.newshop.DaoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import shop.newshop.DAO.BonusDao;
import shop.newshop.Entity.Bonus;
import shop.newshop.util.HibernateUtils;

@Repository
public class BonusDaoImpl implements BonusDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Bonus> getAlls() {
		List<Bonus> list = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			String sql = "FROM Bonus";
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
	public boolean insert(Bonus bn) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(bn);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Bonus bn) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(bn);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} 
		return false;
	}

	@Override
	public boolean delete(int idBonus) {
		Bonus bonus = getBonusById(idBonus);
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(bonus);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} 
		return false;
	}

	@Override
	public Bonus getBonusById(int idBonus) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Bonus bonus = (Bonus) session.get(Bonus.class, idBonus);
		session.close();
		return bonus;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bonus> getLimit(int num, int row, String name) {
		List<Bonus> list = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			String sql = "FROM Bonus Where 1=1";
			String hqlWhere = " ";
			if(name!=null && !name.isEmpty()) {
				hqlWhere += "AND LOWER(employee.name) LIKE '%"+name+"%'";
			}
			Query query = session.createQuery(sql+hqlWhere);
			query.setFirstResult(num);
			query.setMaxResults(row);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
			String hql = "SELECT COUNT(*) FROM Bonus Where 1=1";
			String hqlWhere = " ";
			if(name!=null && !name.isEmpty()) {
				hqlWhere += " AND LOWER(employee.name) LIKE '%"+name+"%'";
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

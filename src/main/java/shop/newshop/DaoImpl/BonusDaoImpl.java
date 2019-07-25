package shop.newshop.DaoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import shop.newshop.DAO.BonusDao;
import shop.newshop.Entity.Bonus;
import shop.newshop.ServiceImpl.BonusServiceImpl;
import shop.newshop.util.HibernateUtils;

@Repository
public class BonusDaoImpl implements BonusDao{

	@Override
	public List<Bonus> getAlls() {
		List<Bonus> list = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			String sql = "FROM Bonus";
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
		} finally {
			session.close();
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
			System.out.println(e);
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean delete(int idBonus) {
		BonusServiceImpl bonusService = new BonusServiceImpl();
		Bonus bonus = bonusService.getBonusById(idBonus);
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(bonus);
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
	public Bonus getBonusById(int idBonus) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Bonus bonus = (Bonus) session.get(Bonus.class, idBonus);
		session.close();
		return bonus;
	}

}

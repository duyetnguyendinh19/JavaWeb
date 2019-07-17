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

	@Override
	public List<Bonus> getAlls() {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		List<Bonus> lstBonus = null;
//		try {
//			session.beginTransaction();
//			String hql = "FROM Bonus";
//			Query query = session.createQuery(hql);
//			lstBonus = query.list();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			session.close();
//		}
		return lstBonus;
	}

	@Override
	public boolean insert(Bonus bn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Bonus bn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idBonus) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Bonus getBonusById(int idBonus) {
		// TODO Auto-generated method stub
		return null;
	}

}

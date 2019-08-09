package shop.newshop.DaoImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import shop.newshop.DAO.AccountDao;
import shop.newshop.Entity.Account;
import shop.newshop.util.HibernateUtils;

import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getAlls() {
		List<Account> list = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			String sql = "FROM Account";
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
	public boolean insert(Account account) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(account);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Account account) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(account);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		}
		return false;
	}

	@Override
	public boolean delete(int idAccount) {
		Account account = getAccountById(idAccount);
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(account);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Account getAccountById(int idAccount) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Account account = (Account) session.get(Account.class, idAccount);
		session.close();
		return account;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getLimit(int num, int row, String name) {
		List<Account> list = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			String sql = "FROM Account Where 1=1";
			String hqlWhere = " ";
			if (name != null && !name.isEmpty()) {
				hqlWhere += "AND LOWER(username) LIKE '%" + name + "%'";
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
			String hql = "SELECT COUNT(*) FROM Account Where 1=1";
			String hqlWhere = " ";
			if (name != null && !name.isEmpty()) {
				hqlWhere += " AND LOWER(username) LIKE '%" + name + "%'";
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
	public Account login(String username, String password) {
		Account account = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			String sql = "FROM Account WHERE username=:user AND password=:pass";
			session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("user", username);
			query.setParameter("pass", password);
			if (query.list() != null && !query.list().isEmpty()) {
				account = (Account) query.list().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return account;
	}

}

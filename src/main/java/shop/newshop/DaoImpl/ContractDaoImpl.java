package shop.newshop.DaoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import shop.newshop.DAO.ContractDao;
import shop.newshop.Entity.Contract;
import shop.newshop.util.HibernateUtils;

@Repository
public class ContractDaoImpl implements ContractDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Contract> getAlls() {
		List<Contract> list = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			String sql = "FROM Contract";
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
	public Contract getContractByEmployeeId(int id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("FROM Contract c WHERE c.employee.id = :id order by id desc").setMaxResults(1);
			query.setInteger("id", id);
			Contract contract = (Contract) query.uniqueResult();
			transaction.commit();
			return contract;
		}catch (Exception e){
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insert(Contract ct) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(ct);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			//            session.close();
		}
		return false;
	}

	@Override
	public boolean update(Contract ct) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(ct);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} 
		return false;
	}

	@Override
	public boolean delete(int idContract) {
		Contract contract = getContractById(idContract);
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(contract);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Contract getContractById(int idContract) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Contract bonus = (Contract) session.get(Contract.class, idContract);
		session.close();
		return bonus;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Contract> getLimit(int num, int row, String name) {
		List<Contract> list = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			String sql = "FROM Contract Where 1=1";
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
			String hql = "SELECT COUNT(*) FROM Contract Where 1=1";
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

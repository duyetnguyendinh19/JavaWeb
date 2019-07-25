package shop.newshop.DaoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import shop.newshop.DAO.ContractDao;
import shop.newshop.Entity.Contract;
import shop.newshop.ServiceImpl.ContractServiceImpl;
import shop.newshop.util.HibernateUtils;

@Repository
public class ContractDaoImpl implements ContractDao {

    @Override
    public List<Contract> getAlls() {
        List<Contract> list = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            String sql = "FROM Contract";
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
            System.out.println(e);
        } finally {
//            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(int idContract) {
        ContractServiceImpl contractService = new ContractServiceImpl();
        Contract contract = contractService.getContractById(idContract);
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.delete(contract);
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
    public Contract getContractById(int idContract) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Contract bonus = (Contract) session.get(Contract.class, idContract);
        session.close();
        return bonus;
    }

}

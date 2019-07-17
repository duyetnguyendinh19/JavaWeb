package shop.newshop.DaoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import shop.newshop.DAO.DisciplineDao;
import shop.newshop.Entity.Discipline;
import shop.newshop.ServiceImpl.DisciplineServiceImpl;
import shop.newshop.util.HibernateUtils;

@Repository
public class DisciplineDaoImpl implements DisciplineDao {

    @Override
    public List<Discipline> getAlls() {
        List<Discipline> list = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            String sql = "FROM Discipline";
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
        } finally {
            session.close();
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
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(int idDiscipline) {
        DisciplineServiceImpl diserviceImple = new DisciplineServiceImpl();
        Discipline discipline = diserviceImple.getDisciplineById(idDiscipline);
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.delete(discipline);
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
    public Discipline getDisciplineById(int idDiscipline) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Discipline discipline = (Discipline) session.get(Discipline.class, idDiscipline);
        session.close();
        return discipline;
    }

}

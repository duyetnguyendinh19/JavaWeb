package shop.newshop.DaoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import shop.newshop.DAO.DisciplineDao;
import shop.newshop.Entity.Discipline;

@Repository
public class DisciplineDaoImpl implements DisciplineDao{

	@Override
	public List<Discipline> getAlls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Discipline di) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Discipline di) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idDiscipline) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Discipline getDisciplineById(int idDiscipline) {
		// TODO Auto-generated method stub
		return null;
	}

}

package shop.newshop.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.newshop.DAO.DisciplineDao;
import shop.newshop.Entity.Discipline;
import shop.newshop.Service.DisciplineService;

@Service
public class DisciplineServiceImpl implements DisciplineService{
	
	@Autowired
	private DisciplineDao disDao;
	
	@Override
	public List<Discipline> getAlls() {
		
		return disDao.getAlls();
	}

	@Override
	public boolean insert(Discipline di) {
		return disDao.insert(di);
	}

	@Override
	public boolean update(Discipline di) {
		
		return disDao.update(di);
	}

	@Override
	public boolean delete(int idDiscipline) {
		
		return disDao.delete(idDiscipline);
	}

	@Override
	public Discipline getDisciplineById(int idDiscipline) {
		
		return disDao.getDisciplineById(idDiscipline);
	}

}

package shop.newshop.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.newshop.DAO.DisciplineDao;
import shop.newshop.Entity.Discipline;
import shop.newshop.Entity.Employee;
import shop.newshop.Service.DisciplineService;

@Service
public class DisciplineServiceImpl implements DisciplineService {

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

	@Override
	public List<Discipline> getLimit(int num, int row, String name) {
		if (name != null) {
			return disDao.getLimit(num, row, name.toLowerCase());
		} else {
			return disDao.getLimit(num, row, name);
		}
	}

	@Override
	public long countAll(String name) {
		if (name != null) {
			return disDao.countAll(name.toLowerCase());
		} else {
			return disDao.countAll(name);
		}
	}

	@Override
	public List<Discipline> getDisciplinesByEmployee(Employee employee) {
		return disDao.getDisciplinesByEmployee(employee);
	}

}

package shop.newshop.DAO;

import java.util.List;

import shop.newshop.Entity.Discipline;

public interface DisciplineDao {
	public List<Discipline> getAlls();

	public boolean insert(Discipline di);

	public boolean update(Discipline di);

	public boolean delete(int idDiscipline);

	public Discipline getDisciplineById(int idDiscipline);

	public List<Discipline> searchNameEmployee(String nameEmployee);
}

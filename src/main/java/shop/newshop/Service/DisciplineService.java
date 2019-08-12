package shop.newshop.Service;

import java.util.List;

import shop.newshop.Entity.Discipline;
import shop.newshop.Entity.Employee;

public interface DisciplineService {

	public List<Discipline> getAlls();

	public List<Discipline> getLimit(int num, int row, String name);
	
	public List<Discipline> getDisciplinesByEmployee(Employee employee);

	public long countAll(String name);

	public boolean insert(Discipline di);

	public boolean update(Discipline di);

	public boolean delete(int idDiscipline);

	public Discipline getDisciplineById(int idDiscipline);

}

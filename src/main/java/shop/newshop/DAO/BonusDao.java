package shop.newshop.DAO;

import java.util.List;

import shop.newshop.Entity.Bonus;
import shop.newshop.Entity.Employee;

public interface BonusDao {
	
	public List<Bonus> getAlls();

	public List<Bonus> getLimit(int num, int row, String name);
	
	public List<Bonus> getBonusByEmployee(Employee employee);

	public long countAll(String name);

	public boolean insert(Bonus bn);

	public boolean update(Bonus bn);

	public boolean delete(int idBonus);

	public Bonus getBonusById(int idBonus);

}

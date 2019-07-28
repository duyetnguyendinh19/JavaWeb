package shop.newshop.DAO;

import java.util.List;

import shop.newshop.Entity.Bonus;

public interface BonusDao {
	public List<Bonus> getAlls();

	public boolean insert(Bonus bn);

	public boolean update(Bonus bn);

	public boolean delete(int idBonus);

	public Bonus getBonusById(int idBonus);

	public List<Bonus> searchNameEmployee(String nameEmployee);
}

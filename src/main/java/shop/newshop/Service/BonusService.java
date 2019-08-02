package shop.newshop.Service;

import java.util.List;

import shop.newshop.Entity.Bonus;

public interface BonusService {

	public List<Bonus> getAlls();
	
	public List<Bonus> getLimit(int num,int row,String name);
	
	public long countAll(String name);

	public boolean insert(Bonus bn);

	public boolean update(Bonus bn);

	public boolean delete(int idBonus);

	public Bonus getBonusById(int idBonus);


}

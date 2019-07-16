package shop.newshop.DaoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import shop.newshop.DAO.BonusDao;
import shop.newshop.Entity.Bonus;

@Repository
public class BonusDaoImpl implements BonusDao{

	@Override
	public List<Bonus> getAlls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Bonus bn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Bonus bn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idBonus) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Bonus getBonusById(int idBonus) {
		// TODO Auto-generated method stub
		return null;
	}

}

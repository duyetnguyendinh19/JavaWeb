package shop.newshop.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.newshop.DAO.BonusDao;
import shop.newshop.Entity.Bonus;
import shop.newshop.Service.BonusService;

@Service
public class BonusServiceImpl implements BonusService{

	@Autowired
	private BonusDao bonusDao;
	
	@Override
	public List<Bonus> getAlls() {
		return bonusDao.getAlls();
	}

	@Override
	public boolean insert(Bonus bn) {
		return bonusDao.insert(bn);
	}

	@Override
	public boolean update(Bonus bn) {
		return bonusDao.update(bn);
	}

	@Override
	public boolean delete(int idBonus) {
		return bonusDao.delete(idBonus);
	}

	@Override
	public Bonus getBonusById(int idBonus) {
		return bonusDao.getBonusById(idBonus);
	}

	@Override
	public List<Bonus> searchNameEmployee(String nameEmployee) {
		return bonusDao.searchNameEmployee(nameEmployee);
	}

}

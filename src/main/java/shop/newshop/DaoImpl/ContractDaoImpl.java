package shop.newshop.DaoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import shop.newshop.DAO.ContractDao;
import shop.newshop.Entity.Contract;

@Repository
public class ContractDaoImpl implements ContractDao{

	@Override
	public List<Contract> getAlls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Contract ct) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Contract ct) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idContract) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Contract getContractById(int idContract) {
		// TODO Auto-generated method stub
		return null;
	}

}

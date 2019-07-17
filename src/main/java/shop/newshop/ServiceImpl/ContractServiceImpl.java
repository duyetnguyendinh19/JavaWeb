package shop.newshop.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.newshop.DAO.ContractDao;
import shop.newshop.Entity.Contract;
import shop.newshop.Service.ContractService;

@Service
public class ContractServiceImpl implements ContractService{

	@Autowired
	private ContractDao contractDao;

	@Override
	public List<Contract> getAlls() {
		return contractDao.getAlls();
	}

	@Override
	public boolean insert(Contract ct) {		
		return contractDao.insert(ct);
	}

	@Override
	public boolean update(Contract ct) {		
		return contractDao.update(ct);
	}

	@Override
	public boolean delete(int idContract) {		
		return contractDao.delete(idContract);
	}

	@Override
	public Contract getContractById(int idContract) {		
		return contractDao.getContractById(idContract);
	}



}
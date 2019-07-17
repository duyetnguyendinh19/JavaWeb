package shop.newshop.Service;

import java.util.List;

import shop.newshop.Entity.Contract;

public interface  ContractService {

	public List<Contract> getAlls();

	public boolean insert(Contract ct);

	public boolean update(Contract ct);

	public boolean delete(int idContract);

	public Contract getContractById(int idContract);

}

package shop.newshop.DAO;

import java.util.List;

import shop.newshop.Entity.Contract;
import shop.newshop.Entity.Employee;


public interface ContractDao {
	public List<Contract> getAlls();

	public boolean insert(Contract ct);

	public boolean update(Contract ct);

	public boolean delete(int idContract);

	public Contract getContractById(int idContract);

	public Contract getContractByEmployeeId(int id);
}

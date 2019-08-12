package shop.newshop.Service;

import java.util.List;

import shop.newshop.Entity.Contract;
import shop.newshop.Entity.Employee;

public interface  ContractService {

	public List<Contract> getAlls();
	
	public List<Contract> getLimit(int num,int row,String name);
	
	public long countAll(String name);

	public boolean insert(Contract ct);

	public boolean update(Contract ct);

	public boolean delete(int idContract);
	
	public List<Contract> getContractByEmployee(Employee employee);

	public Contract getContractById(int idContract);

	public Contract getContractByEmployeeId(int id);


}

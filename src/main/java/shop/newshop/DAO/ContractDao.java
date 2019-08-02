package shop.newshop.DAO;

import java.util.List;

import shop.newshop.Entity.Contract;


public interface ContractDao {

	public List<Contract> getAlls();

	public List<Contract> getLimit(int num,int row,String name);

	public boolean insert(Contract ct);

	public boolean update(Contract ct);

	public boolean delete(int idContract);

	public Contract getContractById(int idContract);

	public Contract getContractByEmployeeId(int id);

	public long countAll(String name);


}

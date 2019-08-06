package shop.newshop.DAO;

import java.util.List;

import shop.newshop.Entity.Account;
import shop.newshop.Entity.Employee;

public interface EmployeeDao {
	public List<Employee> getAlls();

	public List<Employee> getLimit(int num,int row,String name);
	
	public boolean insert(Employee emp,Account account);

	public boolean update(Employee emp);

	public boolean delete(int idEmployee);

	public Employee getEmployeeById(int idEmployee);
	
	public long countAll(String name);
}

package shop.newshop.Service;

import java.util.List;

import shop.newshop.Entity.Employee;

public interface EmployeeService {
	public List<Employee> getAlls();

	public boolean insert(Employee emp);

	public boolean update(Employee emp);

	public boolean delete(int idEmployee);

	public Employee getEmployeeById(int idEmployee);
}

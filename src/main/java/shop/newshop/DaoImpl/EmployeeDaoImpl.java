package shop.newshop.DaoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import shop.newshop.DAO.EmployeeDao;
import shop.newshop.Entity.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

	@Override
	public List<Employee> getAlls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Employee emp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Employee emp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idEmployee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Employee getEmployeeById(int idEmployee) {
		// TODO Auto-generated method stub
		return null;
	}

}

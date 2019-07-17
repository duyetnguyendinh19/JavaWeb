package shop.newshop.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.newshop.DAO.EmployeeDao;
import shop.newshop.Entity.Employee;
import shop.newshop.Service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeDao empDao;
	
	@Override
	public List<Employee> getAlls() {
		
		return empDao.getAlls();
	}

	@Override
	public boolean insert(Employee emp) {
		
		return empDao.insert(emp);
	}

	@Override
	public boolean update(Employee emp) {
		
		return empDao.update(emp);
	}

	@Override
	public boolean delete(int idEmployee) {
		
		return empDao.delete(idEmployee);
	}

	@Override
	public Employee getEmployeeById(int idEmployee) {
		
		return empDao.getEmployeeById(idEmployee);
	}

}

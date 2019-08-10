package shop.newshop.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.newshop.DAO.EmployeeDao;
import shop.newshop.Entity.Account;
import shop.newshop.Entity.Employee;
import shop.newshop.Service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao empDao;

	@Override
	public List<Employee> getAlls() {
		return empDao.getAlls();
	}

	@Override
	public boolean insert(Employee emp, Account account) {
		return empDao.insert(emp, account);
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

	@Override
	public List<Employee> getLimit(int num, int row, String name) {
		if (name != null) {
			return empDao.getLimit(num, row, name.toLowerCase());
		}
		return empDao.getLimit(num, row, name);
	}

	@Override
	public long countAll(String name) {
		if (name != null) {
			return empDao.countAll(name.toLowerCase());
		}
		return empDao.countAll(name);
	}

	@Override
	public boolean checkEmail(String email) {
		return empDao.checkEmail(email);
	}

	@Override
	public long check(String email, String phone, String username, String card, int id) {
		return empDao.check(email, phone, username, card, id);
	}

}

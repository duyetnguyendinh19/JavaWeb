package shop.newshop.Service;

import java.util.List;

import shop.newshop.Entity.Department;

public interface DepartmentService {
	public List<Department> getAlls();

	public boolean insert(Department de);

	public boolean update(Department de);

	public boolean delete(int idDepart);

	public Department getDepartById(int idDepart);
}

package shop.newshop.DAO;

import java.util.List;

import shop.newshop.Entity.Department;

public interface DepartmentDao {

	public List<Department> getAlls(int startnum, int rownum, String nameDepart);

	public boolean insert(Department de);

	public boolean update(Department de);

	public boolean delete(int idDepart);

	public Department getDepartById(int idDepart);

	public long checkName(String nameDepart, int idDepart);

	public long countAll(String nameDepart);

	public List<Department> getAlls();

}

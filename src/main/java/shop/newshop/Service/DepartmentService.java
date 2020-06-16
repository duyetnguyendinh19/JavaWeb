package shop.newshop.Service;

import java.util.List;

import org.springframework.stereotype.Component;

import shop.newshop.Entity.Department;

@Component
public interface DepartmentService {
	
	public List<Department> getAlls();
	
	public List<Department> getAlls(int startnum, int rownum,String nameDepart);

	public boolean insert(Department de);

	public boolean update(Department de);

	public boolean delete(int idDepart);

	public Department getDepartById(int idDepart);
	
	public long checkName(String nameDepart,int idDepart);
	
	public long countAll(String nameDepart);
}

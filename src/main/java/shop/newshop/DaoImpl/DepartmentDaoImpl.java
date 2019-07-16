package shop.newshop.DaoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import shop.newshop.DAO.DepartmentDao;
import shop.newshop.Entity.Department;

@Repository
public class DepartmentDaoImpl implements DepartmentDao{

	@Override
	public List<Department> getAlls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Department de) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Department de) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idDepart) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Department getDepartById(int idDepart) {
		// TODO Auto-generated method stub
		return null;
	}

}

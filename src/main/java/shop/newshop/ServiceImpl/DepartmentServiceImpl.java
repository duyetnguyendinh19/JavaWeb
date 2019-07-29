package shop.newshop.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.newshop.DAO.DepartmentDao;
import shop.newshop.Entity.Department;
import shop.newshop.Service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentDao departDao;
	
	@Override
	public List<Department> getAlls(int startnum, int rownum) {
		
		return departDao.getAlls( startnum,  rownum);
	}

	@Override
	public boolean insert(Department de) {
		
		return departDao.insert(de);
	}

	@Override
	public boolean update(Department de) {
		
		return departDao.update(de);
	}

	@Override
	public boolean delete(int idDepart) {
		
		return departDao.delete(idDepart);
	}

	@Override
	public Department getDepartById(int idDepart) {
		
		return departDao.getDepartById(idDepart);
	}

	@Override
	public long countAll() {
		return departDao.countAll();
	}

	@Override
	public List<Department> getAlls() {
		return departDao.getAlls();
	}

}

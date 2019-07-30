package shop.newshop.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.newshop.DAO.SalaryDao;
import shop.newshop.Entity.Salary;
import shop.newshop.Service.SalaryService;

import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryDao salaryDao;

    @Override
    public List<Salary> getAlls() {
        return salaryDao.getAlls();
    }

    @Override
    public boolean insert(Salary salary) {
        return salaryDao.insert(salary);
    }

    @Override
    public boolean update(Salary salary) {
        return salaryDao.update(salary);
    }

    @Override
    public boolean delete(int idSalary) {
        return salaryDao.delete(idSalary);
    }

    @Override
    public Salary getSalaryById(int idSalary) {
        return salaryDao.getSalaryById(idSalary);
    }

    @Override
    public List<Salary> searchByName(String name) {
        return salaryDao.searchByName(name);
    }
}

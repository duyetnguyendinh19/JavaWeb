package shop.newshop.DAO;

import shop.newshop.Entity.Salary;

import java.util.List;

public interface SalaryDao {

    public List<Salary> getAlls();

    public boolean insert(Salary salary);

    public boolean update(Salary salary);

    public boolean delete(int idSalary);

    public Salary getSalaryById(int idSalary);

    public List<Salary> searchByName(String name);

}

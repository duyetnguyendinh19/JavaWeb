package shop.newshop.Service;

import shop.newshop.Entity.Salary;

import java.util.List;

public interface SalaryService {

    public List<Salary> getAlls();
    
    public List<Salary> getLimit(int num,int row,String name,int month, int year);
    
    public List<Salary> getSalaryByEmployee(int idEmployee);
    
	public long countAll(String name, int month, int year);

    public boolean insert(Salary salary);

    public boolean update(Salary salary);

    public boolean delete(int idSalary);

    public Salary getSalaryById(int idSalary);

    public List<Salary> searchByName(String name);

}

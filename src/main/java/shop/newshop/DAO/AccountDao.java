package shop.newshop.DAO;

import shop.newshop.Entity.Account;


import java.util.List;

public interface AccountDao {

    public List<Account> getAlls();
    
    public List<Account> getLimit(int num,int row,String name);
    
    public Account login(String username, String password);

    public boolean insert(Account account);

    public boolean update(Account account);

    public boolean delete(int idAccount);

    public Account getAccountById(int idAccount);
    
    public long countAll(String name);

}

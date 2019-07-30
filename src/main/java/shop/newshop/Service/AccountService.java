package shop.newshop.Service;

import shop.newshop.Entity.Account;

import java.util.List;

public interface AccountService {

    public List<Account> getAlls();

    public boolean insert(Account account);

    public boolean update(Account account);

    public boolean delete(int idAccount);

    public Account getAccountById(int idAccount);

    public List<Account> searchByUser(String user);

}

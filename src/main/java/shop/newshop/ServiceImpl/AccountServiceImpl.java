package shop.newshop.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.newshop.DAO.AccountDao;
import shop.newshop.Entity.Account;
import shop.newshop.Service.AccountService;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public List<Account> getAlls() {
        return accountDao.getAlls();
    }

    @Override
    public boolean insert(Account account) {
        return accountDao.insert(account);
    }

    @Override
    public boolean update(Account account) {
        return accountDao.update(account);
    }

    @Override
    public boolean delete(int idAccount) {
        return accountDao.delete(idAccount);
    }

    @Override
    public Account getAccountById(int idAccount) {
        return accountDao.getAccountById(idAccount);
    }

    @Override
    public List<Account> searchByUser(String user) {
        return accountDao.searchByUser(user);
    }
}

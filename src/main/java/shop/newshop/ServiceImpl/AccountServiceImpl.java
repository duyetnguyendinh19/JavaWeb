package shop.newshop.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.newshop.DAO.AccountDao;
import shop.newshop.Entity.Account;
import shop.newshop.Service.AccountService;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	public List<Account> getLimit(int num, int row, String name) {
		if(name!=null) {
			return accountDao.getLimit(num, row, name.toLowerCase());
		}
		
		return accountDao.getLimit(num, row, name);
	}

	@Override
	public long countAll(String name) {
		if(name!=null) {
			return accountDao.countAll(name.toLowerCase());
		}
		return accountDao.countAll(name);
	}

	@Override
	public Account login(String username, String password) {
		return accountDao.login(username, encryptThisString(password));
	}
	
	public static String encryptThisString(String input) {
		try {
			// getInstance() method is called with algorithm SHA-512
			MessageDigest md = MessageDigest.getInstance("SHA-512");

			// digest() method is called
			// to calculate message digest of the input string
			// returned as array of byte
			byte[] messageDigest = md.digest(input.getBytes());

			// Convert byte array into signum representation
			BigInteger no = new BigInteger(1, messageDigest);

			// Convert message digest into hex value
			String hashtext = no.toString(16);

			// Add preceding 0s to make it 32 bit
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			// return the HashText
			return hashtext;
		}

		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}

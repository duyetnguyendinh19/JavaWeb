import org.hibernate.Session;

import shop.newshop.util.HibernateUtils;

public class checkDB {
	public static void main(String[] args) {
		Session sesion = HibernateUtils.getSessionFactory().openSession();
	}
}

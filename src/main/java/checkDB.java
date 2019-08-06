import org.hibernate.Session;

import shop.newshop.util.HibernateUtils;

public class checkDB {
	public static void main(String[] args) {
//		Session sesion = HibernateUtils.getSessionFactory().openSession();
		String a = "a b c";
		String b = "c";
		if(a.toLowerCase().trim().contains(b.toLowerCase().trim())) {
			System.out.println("hi");
		}
	}
}

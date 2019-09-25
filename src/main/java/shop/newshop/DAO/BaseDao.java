package shop.newshop.DAO;

public interface BaseDao {
	
	public boolean insert(Object object);
	
	public boolean update(Object object);
	
	public boolean delete(Class clas,int id);

	public Object getById(Class clas,int id);
	
}

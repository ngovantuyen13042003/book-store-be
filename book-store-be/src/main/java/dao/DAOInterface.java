package dao;

import java.util.List;

public interface DAOInterface<T> {
	public boolean insert(T t);
	
	public List<T> selectAll();
	
	public T selectById(int t);
	
	public boolean delete(T t);
	
	public boolean Update(T t);
	
	public boolean SaveOrUpdate(T t);
}

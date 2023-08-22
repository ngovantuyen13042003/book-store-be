package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Category;
import model.Customer;
import util.HibernateUtill;

public class CatogoryDAO implements DAOInterface<Category>{

	@Override
	public boolean insert(Category t) {
		return SaveOrUpdate(t);
	}

	@Override
	public List<Category> selectAll() {
		List<Category> list = new ArrayList<Category>();
		SessionFactory sf = HibernateUtill.getSessionFactory();
		try {
			Session session = sf.openSession();
			Transaction tr = session.beginTransaction();
			String hql = "from Category";
			Query query = session.createQuery(hql);
			list = query.getResultList();
			tr.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static String getNameById(int id) {
		String name = null;
		SessionFactory sf = HibernateUtill.getSessionFactory();
		try {
			Session session = sf.openSession();
			Transaction tr = session.beginTransaction();
			Query query = session.createQuery("from Category where id = :id");
		    query.setParameter("id", id);
		    List<Category>list = query.getResultList();
		    if(!list.isEmpty()) {
		    	name =  list.get(0).getName();
		    }
		    
		    session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
		
	}
	
	
	

	@Override
	public Category selectById(int t) {
		Category category = new Category();
		SessionFactory sf = HibernateUtill.getSessionFactory();
		try {
			Session session = sf.openSession();
			Transaction tr = session.beginTransaction();
			category = session.get(Category.class, t);
			tr.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return category;
		

	}

	@Override
	public boolean delete(Category t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Update(Category t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean SaveOrUpdate(Category t) {
		SessionFactory sf = HibernateUtill.getSessionFactory();
		try {
			Session session = sf.openSession();
			Transaction tr = session.beginTransaction();
			
			session.saveOrUpdate(t);
			
			tr.commit();
			
			
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}














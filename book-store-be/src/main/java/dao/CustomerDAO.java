package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Customer;
import util.HibernateUtill;

public class CustomerDAO implements DAOInterface<Customer>{

	@Override
	public boolean insert(Customer t) {
		return SaveOrUpdate(t);
	}

	@Override
	public List<Customer> selectAll() {
		List<Customer> list = new ArrayList<Customer>();
		SessionFactory sf = HibernateUtill.getSessionFactory();
		try {
			Session session = sf.openSession();
			Transaction tr = session.beginTransaction();
			String hql = "from Customer";
			Query query = session.createQuery(hql);
			list = query.getResultList();
			tr.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Customer selectById(int t) {
		Customer customer = new Customer();
		SessionFactory sf = HibernateUtill.getSessionFactory();
		try {
			Session session = sf.openSession();
			Transaction tr = session.beginTransaction();
			customer = session.get(Customer.class, t);
			
			tr.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public boolean delete(Customer t) {
		SessionFactory sf = HibernateUtill.getSessionFactory();
		try {
			Session session = sf.openSession();
			Transaction tr = session.beginTransaction();
			
			session.delete(t);
			
			tr.commit();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean Update(Customer t) {
		return SaveOrUpdate(t);
	}

	@Override
	public boolean SaveOrUpdate(Customer t) {
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
	
	public Customer checkAccount(Customer customer) {
		SessionFactory sf = HibernateUtill.getSessionFactory();
		try {
			Session session = sf.openSession();
			Transaction tr = session.beginTransaction();
			
			Query query = session.createQuery("from Customer where email = :email and password = :password");
		    query.setParameter("email", customer.getEmail());
		    query.setParameter("password", customer.getPassword());

		    List<Customer>list = query.getResultList();
		    if(!list.isEmpty()) {
		    	return list.get(0);
		    }
		    session.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	

}














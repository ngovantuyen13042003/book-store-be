package dao;

import java.util.List;


import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Cart;
import util.HibernateUtill;

public class CartDAO implements DAOInterface<Cart> {

	@Override
	public boolean insert(Cart t) {
		return SaveOrUpdate(t);
	}

	@Override
	public List<Cart> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart selectById(int t) {

		return null;
	}

	public List<Cart> getCartByCustomer(int id) {

		SessionFactory sf = HibernateUtill.getSessionFactory();
		try {
			Session session = sf.openSession();
			Transaction tr = session.beginTransaction();

			Query query = session.createQuery("FROM Cart WHERE customerId = :id" );
			query.setParameter("id", id);
			List<Cart> list = query.getResultList();
			tr.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(Cart t) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeCart(int customerId, int bookId) {
		SessionFactory sf = HibernateUtill.getSessionFactory();
		try {
			Session session = sf.openSession();
			Transaction tr = session.beginTransaction();

			Query query = session.createQuery("delete from Cart where customerId = :cusId and id_book = :bookId");
			query.setParameter("cusId", customerId);
			query.setParameter("bookId", bookId);
			query.executeUpdate();

			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean Update(Cart t) {
		// TODO Auto-generated method stub
		return SaveOrUpdate(t);
	}

	@Override
	public boolean SaveOrUpdate(Cart t) {
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

	public boolean updateCartByBookId(Cart cart) {
		SessionFactory sf = HibernateUtill.getSessionFactory();
		try {
			Session session = sf.openSession();
			Transaction tr = session.beginTransaction();

			Query query = session
					.createQuery("Update Cart set amount= :amount where customerId = :cusId and id_book = :bookId");
			query.setParameter("amount", cart.getAmount());
			query.setParameter("cusId", cart.getCustomerId());
			query.setParameter("bookId", cart.getId_book());

			query.executeUpdate();

			tr.commit();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}

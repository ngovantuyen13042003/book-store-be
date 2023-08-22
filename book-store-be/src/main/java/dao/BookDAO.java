package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;

import model.Book;
import model.Category;
import util.HibernateUtill;

public class BookDAO implements DAOInterface<Book> {

	@Override
	public boolean insert(Book t) {
		return SaveOrUpdate(t);
	}

	@Override
	public List<Book> selectAll() {
		SessionFactory sf = HibernateUtill.getSessionFactory();
		try {
			Session session = sf.openSession();
			String hql = "SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.images ";
			List<Book> books = session.createQuery(hql, Book.class).list();
			session.close();
			return books;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Book getCart(int bookId) {
		SessionFactory sf = HibernateUtill.getSessionFactory();
		try {
			Session session = sf.openSession();

			Query query = session.createQuery("SELECT b FROM Book b LEFT JOIN FETCH b.images WHERE b.id = :id");
			query.setParameter("id", bookId);

			return (Book) query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Category getCategory(int id) {
		Category category = new Category();
		SessionFactory sf = HibernateUtill.getSessionFactory();
		try {
			Session session = sf.openSession();

			Query query = session.createQuery(
					"SELECT c.id,c.name FROM Category c, Book b WHERE b.id = :bookId and b.id_category = c.id");
			query.setParameter("bookId", id);

			category = (Category) query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return category;
	}

	@Override
	public Book selectById(int t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Book t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Update(Book t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean SaveOrUpdate(Book t) {
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

	public static List<Book> getItemsByPage(int pageNumber, int pageSize) {
		SessionFactory sf = HibernateUtill.getSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = null;
		List<Book> books = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.images");
			query.setFirstResult((pageNumber - 1) * pageSize);
			query.setMaxResults(pageSize);

			books = query.list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return books;
	}

	public static List<Book> search(String bookName, int pageNumber, int pageSize) {
		SessionFactory sf = HibernateUtill.getSessionFactory();
		try {
			Session session = sf.openSession();
			Transaction tr = session.beginTransaction();
			Query query = session.createQuery(
					"SELECT b FROM Book b JOIN Category c ON b.category.id = c.id WHERE b.name LIKE :searchContent OR c.name LIKE :searchContent");
			query.setParameter("searchContent", "%" + bookName + "%");
			query.setFirstResult((pageNumber - 1) * pageSize);
			query.setMaxResults(pageSize);
			List<Book> list = query.getResultList();
			tr.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static int getTotalCount() {
		SessionFactory sf = HibernateUtill.getSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = null;
		int totalCount = 0;
		try {
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Book.class);
			criteria.setProjection(Projections.rowCount());
			totalCount = ((Number) criteria.uniqueResult()).intValue();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return totalCount;
	}

	public static int getTotalBook() {
		int total = 0;
		SessionFactory sf = HibernateUtill.getSessionFactory();
		try {
			Session session = sf.openSession();

			// Truy vấn đếm số bản ghi trong bảng Book
			Query<Integer> query = session.createQuery("select count(*) from Book");
			int count = ((Number) query.uniqueResult()).intValue();

			session.close();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	public static List<Book> search(String bookName) {
		SessionFactory sf = HibernateUtill.getSessionFactory();
		try {
			Session session = sf.openSession();
			Transaction tr = session.beginTransaction();
			Query query = session.createQuery(
					"SELECT b FROM Book b JOIN Category c ON b.category.id = c.id WHERE b.name LIKE :searchContent OR c.name LIKE :searchContent");
			query.setParameter("searchContent", "%" + bookName + "%");
			List<Book> list = query.getResultList();
			tr.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

//	public static List<Book> search(String bookName) {
//		SessionFactory sf = HibernateUtill.getSessionFactory();
//		try {
//			Session session = sf.openSession();
//			Transaction tr = session.beginTransaction();
//			 Query query = session.createQuery(
//			            "SELECT b FROM Book b JOIN Category c ON b.category.id = c.id WHERE b.name LIKE :searchContent OR c.name LIKE :searchContent");
//			        query.setParameter("searchContent", "%" + bookName + "%");
//			List<Book> list = query.getResultList();
//			tr.commit();
//			return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}

}

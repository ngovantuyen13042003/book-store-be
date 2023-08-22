package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Image;
import util.HibernateUtill;

public class ImageDAO implements DAOInterface<Image>{

	@Override
	public boolean insert(Image t) {
		return SaveOrUpdate(t);
	}

	@Override
	public List<Image> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image selectById(int t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Image t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Update(Image t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean SaveOrUpdate(Image t) {
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

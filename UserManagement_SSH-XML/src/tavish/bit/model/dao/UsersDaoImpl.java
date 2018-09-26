package tavish.bit.model.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import tavish.bit.beans.Users;

public class UsersDaoImpl implements UsersDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insertUser(Users user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void deleteUser(Users user) {
		sessionFactory.getCurrentSession().delete(user);
	}

	@Override
	public void updateUser(Users user) {
		sessionFactory.getCurrentSession().update(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> selectAllUsers() {
		String hql = "from Users";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> selectUsersByPage(int pageNumber, int pageSize) {
		String hql = "from Users";
		int startIndex = (pageNumber - 1) * pageSize;
		return sessionFactory.getCurrentSession().createQuery(hql).setFirstResult(startIndex).setMaxResults(pageSize).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> selectUsersByNameFuz(String name) {
		String hql = "from Users where username like :username";
		return sessionFactory.getCurrentSession().createQuery(hql).setString("username", "%" + name + "%").list();
	}

	@Override
	public Users selectUserByNamePre(String name) {
		String hql = "from Users where username = :username";
		return (Users) sessionFactory.getCurrentSession().createQuery(hql).setString("username", name).uniqueResult();
	}

	@Override
	public Users selectUserById(int id) {
		return sessionFactory.getCurrentSession().get(Users.class, id);
	}

	@Override
	public int getPageCount(int pageSize) {
		String hql = "select count(userId) from Users";
		int rowCount = Integer
				.parseInt(((Long) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult()).toString());
		int pageCount = (rowCount % pageSize == 0) ? rowCount / pageSize : rowCount / pageSize + 1;
		return pageCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> selectUsersByPageFuz(String name, int pageNumber, int pageSize) {
		String hql = "from Users where username like :username";
		int startIndex = (pageNumber - 1) * pageSize;
		return sessionFactory.getCurrentSession().createQuery(hql).setString("username", "%" + name + "%").setFirstResult(startIndex).setMaxResults(pageSize).list();
	}

	@Override
	public int getPageCountFuz(String name, int pageSize) {
		String hql = "select count(userId) from Users where username like :username";
		int rowCount = Integer
				.parseInt(((Long) sessionFactory.getCurrentSession().createQuery(hql).setString("username", "%" + name + "%").uniqueResult()).toString());
		int pageCount = (rowCount % pageSize == 0) ? rowCount / pageSize : rowCount / pageSize + 1;
		return pageCount;
	}

}

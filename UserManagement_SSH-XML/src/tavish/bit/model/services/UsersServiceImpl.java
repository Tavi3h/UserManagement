package tavish.bit.model.services;

import java.util.List;

import tavish.bit.beans.Users;
import tavish.bit.model.dao.UsersDao;

public class UsersServiceImpl implements UsersService {

	private UsersDao dao;

	public void setDao(UsersDao dao) {
		this.dao = dao;
	}

	@Override
	public void addUser(Users user) {
		dao.insertUser(user);
	}

	@Override
	public void removeUser(Users user) {
		dao.deleteUser(user);
	}

	@Override
	public void modifyUser(Users user) {
		dao.updateUser(user);
	}

	@Override
	public List<Users> getAllUsers() {
		return dao.selectAllUsers();
	}

	@Override
	public List<Users> getUsersByPage(int pageNumber, int pageSize) {
		
		return dao.selectUsersByPage(pageNumber, pageSize);
	}
	
	@Override
	public int getPageCount(int pageSize) {
		return dao.getPageCount(pageSize);
	}

	@Override
	public List<Users> getUsersByNameFuz(String name) {
		return dao.selectUsersByNameFuz(name);
	}

	@Override
	public Users getUserByNamePre(String name) {
		return dao.selectUserByNamePre(name);
	}

	@Override
	public Users getUserById(int id) {
		return dao.selectUserById(id);
	}

	@Override
	public List<Users> getUsersByPageFuz(String name, int pageNumber, int pageSize) {
		
		return dao.selectUsersByPageFuz(name, pageNumber, pageSize);
	}

	@Override
	public int getPageCountFuz(String name, int pageSize) {
		return dao.getPageCountFuz(name, pageSize);
	}
}

package tavish.bit.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tavish.bit.beans.Users;
import tavish.bit.model.dao.UsersDao;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersDao dao;

	public void setDao(UsersDao dao) {
		this.dao = dao;
	}

	@Override
	@Transactional
	public void addUser(Users user) {
		dao.insertUser(user);
	}

	@Override
	@Transactional
	public void removeUser(Users user) {
		dao.deleteUser(user);
	}

	@Override
	@Transactional
	public void modifyUser(Users user) {
		dao.updateUser(user);
	}

	@Override
	@Transactional
	public List<Users> getAllUsers() {
		return dao.selectAllUsers();
	}

	@Override
	@Transactional
	public List<Users> getUsersByPage(int pageNumber, int pageSize) {
		
		return dao.selectUsersByPage(pageNumber, pageSize);
	}
	
	@Override
	@Transactional
	public int getPageCount(int pageSize) {
		return dao.getPageCount(pageSize);
	}

	@Override
	@Transactional
	public List<Users> getUsersByNameFuz(String name) {
		return dao.selectUsersByNameFuz(name);
	}

	@Override
	@Transactional
	public Users getUserByNamePre(String name) {
		return dao.selectUserByNamePre(name);
	}

	@Override
	@Transactional
	public Users getUserById(int id) {
		return dao.selectUserById(id);
	}

	@Override
	@Transactional
	public List<Users> getUsersByPageFuz(String name, int pageNumber, int pageSize) {
		return dao.selectUsersByPageFuz(name, pageNumber, pageSize);
	}

	@Override
	@Transactional
	public int getPageCountFuz(String name, int pageSize) {
		return dao.getPageCountFuz(name, pageSize);
	}
}

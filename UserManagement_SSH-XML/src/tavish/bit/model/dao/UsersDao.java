package tavish.bit.model.dao;

import java.util.List;

import tavish.bit.beans.Users;

public interface UsersDao {

	void insertUser(Users user);

	void deleteUser(Users user);

	void updateUser(Users user);

	List<Users> selectAllUsers();
	
	List<Users> selectUsersByPage(int pageNumber, int pageSize);

	List<Users> selectUsersByNameFuz(String name);

	Users selectUserByNamePre(String name);
	
	Users selectUserById(int id);

	int getPageCount(int pageSize);

	List<Users> selectUsersByPageFuz(String name, int pageNumber, int pageSize);

	int getPageCountFuz(String name, int pageSize);
}

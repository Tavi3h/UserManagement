package tavish.bit.model.services;

import java.util.List;

import tavish.bit.beans.Users;

public interface UsersService {
	
	// 添加用户
	void addUser(Users user);
	
	// 删除用户
	void removeUser(Users user);
	
	// 修改用户
	void modifyUser(Users user);
	
	// 查询所有用户
	List<Users> getAllUsers();
	
	// 分页查询
	List<Users> getUsersByPage(int pageNumber, int pageSize);
	
	// 获取查询总页数
	int getPageCount(int pageSize);
	
	// 根据用户名模糊查询
	List<Users> getUsersByNameFuz(String name);
	
	// 根据用户名模糊查询并分页
	List<Users> getUsersByPageFuz(String name, int pageNumber, int pageSize);
	
	// 获取模糊查询总页数
	int getPageCountFuz(String name, int pageSize);
	
	// 根据用户名精确查询
	Users getUserByNamePre(String name);
	
	// 根据用户id进行查询
	Users getUserById(int id);
}

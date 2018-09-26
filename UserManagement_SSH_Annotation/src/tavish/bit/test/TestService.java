package tavish.bit.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import tavish.bit.beans.Users;
import tavish.bit.model.services.UsersService;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring.xml")
public class TestService {

	@Autowired
	private UsersService service;

	// 添加用户
	@Test
	public void testaddUser() {
		Users user = new Users("TestUser", "123456", "test@test.com", 1);
		service.addUser(user);
	}

	// 删除用户
	@Test
	public void testremoveUser() {
		Users user = new Users();
		user.setUserId(511);
		service.removeUser(user);
	}

	// 修改用户
	@Test
	public void testmodifyUser() {
		Users user = new Users("TestUser", "123456", "test@test.com", 1);
		user.setUserId(510);
		service.modifyUser(user);
	}

	// 查询所有用户
	@Test
	public void testgetUsers() {
		List<Users> users = service.getAllUsers();
		for (Users user : users) {
			System.out.println(user);
		}
	}

	// 根据userId查询用户
	@Test
	public void testgetUserById() {
		Users user = service.getUserById(1);
		System.out.println(user);
	}

	// 根据用户名和查询类型查询用户 --- 模糊查询
	@Test
	public void testgetUserByName_1() {
		String name = "tester1";
		List<Users> users = service.getUsersByNameFuz(name);
		for (Users user : users) {
			System.out.println(user);
		}
	}

	// 根据用户名和查询类型查询用户 --- 精确查询
	@Test
	public void testgetUserByName_2() {
		String name = "admin";
		Users user = service.getUserByNamePre(name);
		System.out.println(user);
	}

	// 分页查询
	@Test
	public void testselectUsersByPage() {
		List<Users> users = service.getUsersByPage(2, 4);
		for (Users user : users) {
			System.out.println(user);
		}
	}

	// 获取分页查询中的总页数
	@Test
	public void testgetPageCount() {
		int pageCount = service.getPageCount(10);
		System.out.println(pageCount);
	}

	// 精确查询包装为list
	@Test
	public void testgetUserByName_3() {
		Users user = service.getUserByNamePre("admin");
		List<Users> list = Arrays.asList(new Users[] { user });
		for (Users users : list) {
			System.out.println(users);
		}
	}
	
	// 模糊查询分页
	@Test
	public void testgetUserByPageFuz() {
		String name = "tester1";
		List<Users> list = service.getUsersByPageFuz(name, 2, 4);
		for (Users users : list) {
			System.out.println(users);
		}
	}

}

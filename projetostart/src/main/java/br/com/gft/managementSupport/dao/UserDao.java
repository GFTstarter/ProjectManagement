package br.com.gft.managementSupport.dao;

import java.util.List;

import br.com.gft.managementSupport.entity.User;

public interface UserDao {

	List<User> findAll();
	User find(Long id);
	User findByUsername(String username);
	User save(User obj);
	void delete(Long id);

}
package br.com.gft.managementSupport.dao;

import java.util.List;

import br.com.gft.managementSupport.entity.Role;

public interface RoleDao {

	List<Role> findAll();
	Role find(Long id);
	Role save(Role obj);
	void delete(Long id);

}
package br.com.gft.managementSupport.dao;

import java.util.List;

import br.com.gft.managementSupport.entity.Expenses;
import br.com.gft.managementSupport.entity.Resource;

public interface ExpensesDao {

	List<Resource> findAll();
	Expenses find(Long id);
	Resource findByResourceCode(String resourceCode);
	List<Resource> findByUserId(int idUser);	
	Expenses save(Expenses obj);
	void delete(Long id);

}

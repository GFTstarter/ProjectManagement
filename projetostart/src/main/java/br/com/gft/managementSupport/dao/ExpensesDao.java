package br.com.gft.managementSupport.dao;

import java.util.List;

import br.com.gft.managementSupport.entity.Expenses;

public interface ExpensesDao {
	
	List<Expenses> findAll();
	Expenses find(Long id);
	Expenses findByExpensesCode(String ExpensesCode);
	Expenses save(Expenses obj);
	void delete(Long id);

}



package br.com.gft.managementSupport.dao;

import java.util.List;

import br.com.gft.managementSupport.entity.Project;

public interface ProjectDao {

	List<Project> findAll();
	Project find(Long id);
	Project findByProjectCode(String projectCode);
	List<Project> findByUserId(int idUser);	
	Project save(Project obj);
	void delete(Long id);

}

package br.com.gft.managementSupport.rowMapper;

import java.util.List;

import br.com.gft.managementSupport.gridViews.PeopleView;

public interface PeopleDao {

	PeopleView find(Long id);
	PeopleView save (PeopleView obj);
	void delete(Long id);
	public List<PeopleView> findAll();
	public List<PeopleView> findPeopleByProject(Long idProject);
	public int findTotalPeople();

}

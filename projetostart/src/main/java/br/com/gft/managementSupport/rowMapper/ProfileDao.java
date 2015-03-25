package br.com.gft.managementSupport.rowMapper;

import java.util.List;

import br.com.gft.managementSupport.entity.ConceptByLegalEntity;
import br.com.gft.managementSupport.gridViews.ProfileView;

public interface ProfileDao {
	
	ProfileView find(Long id);
	ConceptByLegalEntity save (ConceptByLegalEntity newsEntry);
	void delete(Long id);
	public List<ProfileView> findAll();
	public int findTotalProfile();
}

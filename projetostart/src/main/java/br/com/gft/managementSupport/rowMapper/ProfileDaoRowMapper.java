package br.com.gft.managementSupport.rowMapper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import br.com.gft.managementSupport.entity.ConceptByLegalEntity;
import br.com.gft.managementSupport.gridViews.ProfileParameterizedRowMapper;
import br.com.gft.managementSupport.gridViews.ProfileView;

public class ProfileDaoRowMapper  extends JdbcDaoSupport implements ProfileDao {

	private EntityManager entityManager;

	public EntityManager getEntityManager() {

		return this.entityManager;
	}


	@PersistenceContext
	public void setEntityManager(final EntityManager entityManager) {

		this.entityManager = entityManager;
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public ProfileView find(Long idConcept) {

		return this.getEntityManager().find(ProfileView.class, idConcept);
	}
	
	
	@Override
	@Transactional
	public ConceptByLegalEntity save(ConceptByLegalEntity obj) {
		
		return this.getEntityManager().merge(obj);
	}
	
	
	@Override
	@Transactional
	public void delete(Long id) {

		if (id == null) {
			return;
		}

		ProfileView obj = this.find(id);
		if (obj == null) {
			return;
		}

		this.getEntityManager().remove(obj);
	}
	
	
	public List<ProfileView> findAll(){
		
		String sql = "SELECT * FROM VW_PROFILE";
		
		List<ProfileView> profiles = getJdbcTemplate().query(sql, new ProfileParameterizedRowMapper());
		
		return profiles;
	}
	
	
	public int findTotalProfile(){
		
		String sql = "SELECT COUNT(*) FROM VW_PROFILE";
		 
		int total = getJdbcTemplate().queryForInt(sql);
				
		return total;
	}
	
}
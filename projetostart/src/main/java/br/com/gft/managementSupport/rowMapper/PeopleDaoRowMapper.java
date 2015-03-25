package br.com.gft.managementSupport.rowMapper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import br.com.gft.managementSupport.gridViews.PeopleParameterizedRowMapper;
import br.com.gft.managementSupport.gridViews.PeopleView;

public class PeopleDaoRowMapper extends JdbcDaoSupport implements PeopleDao {

	
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
	public PeopleView find(Long id) {

		return this.getEntityManager().find(PeopleView.class, id);
	}
	
	
	@Override
	@Transactional
	public PeopleView save(PeopleView obj) {
		
		return this.getEntityManager().merge(obj);
	}
	
	
	@Override
	@Transactional
	public void delete(Long id) {

		if (id == null) {
			return;
		}

		PeopleView obj = this.find(id);
		if (obj == null) {
			return;
		}

		this.getEntityManager().remove(obj);
	}
		
	
	public List<PeopleView> findAll(){
		
		String sql = "SELECT * FROM VW_PEOPLE";
		 
		List<PeopleView> peoples = getJdbcTemplate().query(sql, new PeopleParameterizedRowMapper());
		
		return peoples;
	}
	
	public List<PeopleView> findPeopleByProject (Long idProject){
		
		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<PeopleView> criteriaQuery = builder.createQuery(
														PeopleView.class   );
		Root<PeopleView> root = criteriaQuery.from(PeopleView.class);
		criteriaQuery.where(builder.equal(root.get("idProject"), builder.parameter
																 (Integer.class, 
																  "idProject")));
		TypedQuery<PeopleView> q = entityManager.createQuery(criteriaQuery);
		q.setParameter("idProject", idProject);
		criteriaQuery.orderBy(builder.asc(root.get("id")));
		
		return q.getResultList();
	}
	
	
	public int findTotalPeople(){
		
		String sql = "SELECT COUNT(*) FROM VW_PEOPLE";
		 
		int total = getJdbcTemplate().queryForInt(sql);
				
		return total;
	}
	
}
package org.motive.BSHistory.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.motive.BSHistory.domain.BaseStation;
import org.springframework.stereotype.Repository;


@Repository("BSService")
public class BSService implements IBSService {

	private static final String LIST_SELECT_QUERY = "SELECT DISTINCT c FROM BaseStation c";
	private static final String SELECT_BY_ID_QUERY = "SELECT DISTINCT c FROM BaseStation c WHERE c.id=:id";
	private static final String SELECT_BY_TITLE_QUERY = "SELECT DISTINCT c FROM BaseStation c WHERE c.title=:title";
	private static final String SELECT_BY_ID_AND_TITLE_QUERY = "SELECT DISTINCT c FROM BaseStation c WHERE c.id=:id and c.title=:title";	

	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<BaseStation> findAll() {
		Query query = entityManager.createQuery(LIST_SELECT_QUERY);
		@SuppressWarnings("unchecked")
		List<BaseStation> baseStations = (List<BaseStation>) query.getResultList();
		return baseStations;
	}


	@Override
	public BaseStation findById(Long id) {
		Query query = entityManager.createQuery(SELECT_BY_ID_QUERY);
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<BaseStation> result = query.getResultList();
		if (result.size()==1)
		{
			return result.get(0);
		}
		return null;
	}

	@Override
	public BaseStation findByTitle(String title) {
		Query query = entityManager.createQuery(SELECT_BY_TITLE_QUERY);
		query.setParameter("title", title);	
		@SuppressWarnings("unchecked")
		List<BaseStation> result = query.getResultList();
		if (result.size()==1)
		{
			return result.get(0);
		}
		return null;
	}

	@Override
	public BaseStation findByIdAndTitle(Long id, String title) {
		Query query = entityManager.createQuery(SELECT_BY_ID_AND_TITLE_QUERY);
		query.setParameter("title", title);	
		query.setParameter("id", id);	
		@SuppressWarnings("unchecked")
		List<BaseStation> result = query.getResultList();
		if (result.size()==1)
		{
			return result.get(0);
		}
		return null;
	}
	
	
}

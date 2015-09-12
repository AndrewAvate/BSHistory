package org.motive.BSHistory.core.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.motive.BSHistory.core.models.entities.BaseStation;
import org.springframework.stereotype.Repository;

@Repository("BSService")
public class BSService implements IBSService {

	private static final String LIST_SELECT_QUERY = "SELECT DISTINCT c FROM BaseStation c";

	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public BaseStation find(Long id) {
		return entityManager.find(BaseStation.class, id);
	}

	@Override
	public BaseStation delete(Long id) {
		BaseStation baseStation = find(id);
		if (baseStation != null) {
			entityManager.remove(baseStation);
			return baseStation;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BaseStation> findAllBaseStations() {
		Query query = entityManager.createQuery(LIST_SELECT_QUERY);
		return query.getResultList();
	}

	@Override
	public BaseStation update(Long id, BaseStation baseStation) {
		baseStation.setId(id);
		entityManager.merge(baseStation);
		return baseStation;
	}

	@Override
	public BaseStation create(BaseStation baseStation) {
		entityManager.persist(baseStation);
		return baseStation;
	}

}

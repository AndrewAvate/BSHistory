package org.motive.BSHistory.core.services;

import java.util.List;

import org.motive.BSHistory.core.models.entities.BaseStation;


public interface IBSService {

	List<BaseStation> findAllBaseStations();
	BaseStation create(BaseStation baseStation);
	BaseStation find(Long id);
	BaseStation update(Long id, BaseStation baseStation);
	BaseStation delete(Long id);
}

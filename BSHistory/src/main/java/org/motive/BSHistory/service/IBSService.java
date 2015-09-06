package org.motive.BSHistory.service;

import java.util.List;

import org.motive.BSHistory.domain.BaseStation;


public interface IBSService {
	public List<BaseStation> findAll();
	public BaseStation findByIdAndTitle(Long id, String title);
	public BaseStation findById(Long id);
	public BaseStation findByTitle(String title);
}

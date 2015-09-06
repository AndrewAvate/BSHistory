package org.motive.BSHistory.rest;

import java.util.List;

import org.motive.BSHistory.domain.BaseStation;
import org.motive.BSHistory.service.IBSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rs")
public class BaseStationRestController {

	@Autowired
	private IBSService service;
	
	@RequestMapping(value = "/bs", method = RequestMethod.GET)
	public @ResponseBody BaseStation getBS(String title, Long id) {

		BaseStation baseStation = null;
		if (id != null && title != null) {
			baseStation = service.findByIdAndTitle(id, title);
		}
		else if (null != id) {
			baseStation = service.findById(id);
		} else if (null != title) {
			baseStation = service.findByTitle(title);
		}
		return baseStation;
	}
	
	@RequestMapping(value = "/bsList", method = RequestMethod.GET)
	public @ResponseBody List<BaseStation> getAllBS() {
		List<BaseStation> baseStationList = service.findAll(); 
		return baseStationList;
	}
	
	@RequestMapping(value = "/createBS", method = RequestMethod.POST)
	public @ResponseBody BaseStation createBS(@RequestBody BaseStation baseStation) {
		
		return baseStation;
	}
	
	

	
	
	// gettser & setters
	public IBSService geService() {
		return service;
	}

	public void setService(IBSService service) {
		this.service = service;
	}
	
}

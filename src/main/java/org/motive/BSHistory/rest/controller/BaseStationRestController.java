package org.motive.BSHistory.rest.controller;

import java.util.List;

import org.motive.BSHistory.domain.BaseStation;
import org.motive.BSHistory.rest.resources.BaseStationResource;
import org.motive.BSHistory.rest.resources.asm.BaseStationResourceAsm;
import org.motive.BSHistory.service.IBSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rs")
public class BaseStationRestController {

	@Autowired
	private IBSService service;

	@RequestMapping(value = "/baseStation/{baseStationId}", method = RequestMethod.GET)
	public ResponseEntity<BaseStationResource> getBaseStation(
			@PathVariable Long baseStationId) {
		BaseStation baseStation = service.findById(baseStationId);
		if (baseStation != null) {
			BaseStationResource res = new BaseStationResourceAsm()
					.toResource(baseStation);
			return new ResponseEntity<BaseStationResource>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<BaseStationResource>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/bsList", method = RequestMethod.GET)
	public @ResponseBody List<BaseStation> getAllBS() {
		List<BaseStation> baseStationList = service.findAll();
		return baseStationList;
	}

	@RequestMapping(value = "/createBS", method = RequestMethod.POST)
	public @ResponseBody BaseStation createBS(
			@RequestBody BaseStation baseStation) {

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

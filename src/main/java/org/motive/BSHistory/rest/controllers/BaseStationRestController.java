package org.motive.BSHistory.rest.controllers;

import java.net.URI;
import java.util.List;

import org.motive.BSHistory.core.models.entities.BaseStation;
import org.motive.BSHistory.core.services.IBSService;
import org.motive.BSHistory.core.services.exceptions.BaseStationExistsException;
import org.motive.BSHistory.rest.exceptions.ConflictException;
import org.motive.BSHistory.rest.resources.BaseStationResource;
import org.motive.BSHistory.rest.resources.asm.BaseStationResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rs/baseStation")
public class BaseStationRestController {

	@Autowired
	private IBSService service;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<BaseStationResource> createBaseStation(@RequestBody BaseStationResource baseStationRes) {
		try {
			BaseStation createdBaseStation = service.create(baseStationRes.toBaseStation());
			BaseStationResource res = new BaseStationResourceAsm().toResource(createdBaseStation);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create(res.getLink("self").getHref()));
			return new ResponseEntity<BaseStationResource>(res, headers, HttpStatus.CREATED);
		} catch (BaseStationExistsException exception) {
			throw new ConflictException(exception);
		}
	}

	@RequestMapping(value = "/{baseStationId}", method = RequestMethod.GET)
	public ResponseEntity<BaseStationResource> getBaseStation(
			@PathVariable Long baseStationId) {
		BaseStation baseStation = service.find(baseStationId);
		if (baseStation != null) {
			BaseStationResource res = new BaseStationResourceAsm()
					.toResource(baseStation);
			return new ResponseEntity<BaseStationResource>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<BaseStationResource>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{baseStationId}", method = RequestMethod.PUT)
	public ResponseEntity<BaseStationResource> updateBaseStation(
			@PathVariable Long baseStationId,
			@RequestBody BaseStationResource baseStationRes) {
		BaseStation updatedBaseStation = service.update(baseStationId,
				baseStationRes.toBaseStation());
		if (updatedBaseStation != null) {
			BaseStationResource res = new BaseStationResourceAsm()
					.toResource(updatedBaseStation);
			return new ResponseEntity<BaseStationResource>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<BaseStationResource>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{baseStationId}", method = RequestMethod.DELETE)
	public ResponseEntity<BaseStationResource> deleteBaseStation(
			@PathVariable Long baseStationId) {
		BaseStation baseStation = service.delete(baseStationId);
		if (baseStation != null) {
			BaseStationResource res = new BaseStationResourceAsm()
					.toResource(baseStation);
			return new ResponseEntity<BaseStationResource>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<BaseStationResource>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<BaseStation> getAllBS() {
		List<BaseStation> baseStationList = service.findAllBaseStations();
		return baseStationList;
	}

	// gettser & setters
	public IBSService geService() {
		return service;
	}

	public void setService(IBSService service) {
		this.service = service;
	}

}

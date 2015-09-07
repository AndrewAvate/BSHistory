package org.motive.BSHistory.rest.resources.asm;

import org.motive.BSHistory.domain.BaseStation;
import org.motive.BSHistory.rest.controller.BaseStationRestController;
import org.motive.BSHistory.rest.resources.BaseStationResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

public class BaseStationResourceAsm extends ResourceAssemblerSupport<BaseStation, BaseStationResource>{

	public BaseStationResourceAsm() {
		super(BaseStationRestController.class, BaseStationResource.class);

	}

	@Override
	public BaseStationResource toResource(BaseStation baseStation) { 
		BaseStationResource res = new BaseStationResource();
		res.setTitle(baseStation.getTitle());
		res.setApplication(baseStation.getApplication());
		res.setDescription(baseStation.getDescription());
		res.setCreationDate(baseStation.getCreationDate());
		Link link = linkTo(methodOn(BaseStationRestController.class).getBaseStation(baseStation.getId())).withSelfRel(); 
		res.add(link.withSelfRel());
		return res;
	}

}

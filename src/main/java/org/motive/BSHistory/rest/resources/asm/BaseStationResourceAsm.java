package org.motive.BSHistory.rest.resources.asm;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.motive.BSHistory.core.models.entities.BaseStation;
import org.motive.BSHistory.rest.controllers.BaseStationRestController;
import org.motive.BSHistory.rest.resources.BaseStationResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

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
